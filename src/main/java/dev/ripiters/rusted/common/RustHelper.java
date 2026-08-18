package dev.ripiters.rusted.common;

import dev.ripiters.rusted.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;

import java.util.Optional;

public class RustHelper {

    public static void tryRust(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!Config.ENABLE_RUSTING.get()) return;

        if (state.is(Blocks.IRON_BLOCK) && !Config.VANILLA_IRON_RUSTS.get()) {
            return;
        }

        Optional<Block> nextRustState = RustedBlocks.getNextRustState(state.getBlock());
        if (nextRustState.isPresent()) {
            boolean wet = (Config.RAIN_ACCELERATION.get() && level.isRainingAt(pos.above()))
                    || (Config.WATER_ACCELERATION.get() && level.getFluidState(pos).is(Fluids.WATER));

            double chance = wet ? Config.WET_RUST_CHANCE.get() : Config.NORMAL_RUST_CHANCE.get();

            if (random.nextDouble() < chance) {
                BlockState newState = copyProperties(state, nextRustState.get());
                level.setBlockAndUpdate(pos, newState);
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