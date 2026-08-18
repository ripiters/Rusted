package dev.ripiters.rusted.mixin;

import dev.ripiters.rusted.common.RustHelper;
import dev.ripiters.rusted.common.RustedBlocks;
import dev.ripiters.rusted.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

    @Inject(method = "isRandomlyTicking", at = @At("HEAD"), cancellable = true, remap = false)
    private void rusted$isRandomlyTicking(CallbackInfoReturnable<Boolean> cir) {
        BlockState state = (BlockState) (Object) this;

        if (Config.ENABLE_RUSTING.get()) {
            if (state.is(Blocks.IRON_BLOCK) && Config.VANILLA_IRON_RUSTS.get()) {
                cir.setReturnValue(true);
                return;
            }

            if (Config.isCreateCompatEnabled()) {
                Block block = state.getBlock();
                if (block == RustedBlocks.getBlock("create", "industrial_iron_block") ||
                        block == RustedBlocks.getBlock("create", "industrial_iron_window") ||
                        block == RustedBlocks.getBlock("create", "industrial_iron_window_pane")) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(method = "randomTick", at = @At("HEAD"), remap = false)
    private void rusted$randomTick(ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        BlockState state = (BlockState) (Object) this;
        Block block = state.getBlock();

        if (state.is(Blocks.IRON_BLOCK)) {
            RustHelper.tryRust(state, level, pos, random);
        } else if (Config.isCreateCompatEnabled()) {
            if (block == RustedBlocks.getBlock("create", "industrial_iron_block") ||
                    block == RustedBlocks.getBlock("create", "industrial_iron_window") ||
                    block == RustedBlocks.getBlock("create", "industrial_iron_window_pane")) {
                RustHelper.tryRust(state, level, pos, random);
            }
        }
    }
}