package dev.ripiters.rusted.common.event;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.common.RustedBlocks;
import dev.ripiters.rusted.common.network.packets.RustScrapePayload;
import dev.ripiters.rusted.common.network.packets.WaxOffPayload;
import dev.ripiters.rusted.common.network.packets.WaxOnPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Optional;

@EventBusSubscriber(modid = Rusted.MOD_ID)
public class RustedEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        Block currentBlock = state.getBlock();
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();

        Optional<Block> waxedBlock = RustedBlocks.getWaxedState(currentBlock);
        Optional<Block> unwaxedBlock = RustedBlocks.getUnwaxedState(currentBlock);

        // Handle Honeycomb waxing
        if (stack.is(Items.HONEYCOMB) && waxedBlock.isPresent()) {
            player.swing(hand);
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            event.setCanceled(true);

            if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                BlockState newState = copyProperties(state, waxedBlock.get());
                serverLevel.setBlock(pos, newState, 3);
                serverLevel.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);

                PacketDistributor.sendToPlayersTrackingChunk(
                        serverLevel,
                        serverLevel.getChunkAt(pos).getPos(),
                        new WaxOnPayload(pos)
                );

                if (!player.isCreative()) {
                    stack.shrink(1);
                }
            }
            return;
        }

        // Handle Axe scraping and unwaxing
        if (stack.getItem() instanceof AxeItem && unwaxedBlock.isPresent()) {
            boolean isWaxed = RustedBlocks.WAXING_MAP.containsValue(currentBlock);

            player.swing(hand);
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            event.setCanceled(true);

            if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                BlockState newState = copyProperties(state, unwaxedBlock.get());
                serverLevel.setBlock(pos, newState, 3);

                if (isWaxed) {
                    serverLevel.playSound(null, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                    PacketDistributor.sendToPlayersTrackingChunk(
                            serverLevel,
                            serverLevel.getChunkAt(pos).getPos(),
                            new WaxOffPayload(pos)
                    );
                } else {
                    serverLevel.playSound(null, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    PacketDistributor.sendToPlayersTrackingChunk(
                            serverLevel,
                            serverLevel.getChunkAt(pos).getPos(),
                            new RustScrapePayload(pos)
                    );
                }

                if (!player.isCreative()) {
                    EquipmentSlot slot = LivingEntity.getSlotForHand(hand);
                    stack.hurtAndBreak(1, player, slot);
                }
            }
        }
    }

    private static BlockState copyProperties(BlockState source, Block targetBlock) {
        BlockState targetState = targetBlock.defaultBlockState();
        for (Property<?> property : source.getProperties()) {
            if (targetState.hasProperty(property)) {
                targetState = copyPropertyValue(source, targetState, property);
            }
        }
        return targetState;
    }

    private static <T extends Comparable<T>> BlockState copyPropertyValue(BlockState source, BlockState target, Property<T> property) {
        return target.setValue(property, source.getValue(property));
    }
}