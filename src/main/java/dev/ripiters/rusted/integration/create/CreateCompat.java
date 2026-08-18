package dev.ripiters.rusted.integration.create;

import com.simibubi.create.content.decoration.palettes.ConnectedGlassBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CreateCompat {

    public static Block createGlassBlock(BlockBehaviour.Properties properties) {
        return new ConnectedGlassBlock(properties);
    }

    public static Block createGlassPaneBlock(BlockBehaviour.Properties properties) {
        return new ConnectedGlassPaneBlock(properties);
    }
}