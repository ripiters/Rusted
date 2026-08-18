package dev.ripiters.rusted.common.block;

import dev.ripiters.rusted.common.RustHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class RustableBlock extends Block {

    public RustableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(@Nonnull BlockState state, @Nonnull ServerLevel level, @Nonnull BlockPos pos, @Nonnull RandomSource random) {
        RustHelper.tryRust(state, level, pos, random);
    }
}