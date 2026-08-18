package dev.ripiters.rusted.integration.create;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.decoration.palettes.WeatheredIronWindowCTBehaviour;
import com.simibubi.create.content.decoration.palettes.WeatheredIronWindowPaneCTBehaviour;
import com.simibubi.create.foundation.block.connected.GlassPaneCTBehaviour;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.ripiters.rusted.common.RustedBlocks;

public class CreateCompatClient {

    public static void registerCTM() {
        CreateRegistrate.connectedTextures(() -> new SimpleCTBehaviour(AllSpriteShifts.INDUSTRIAL_IRON_WINDOW))
                .accept(RustedBlocks.WAXED_INDUSTRIAL_IRON_WINDOW.get());

        CreateRegistrate.connectedTextures(() -> new GlassPaneCTBehaviour(AllSpriteShifts.INDUSTRIAL_IRON_WINDOW))
                .accept(RustedBlocks.WAXED_INDUSTRIAL_IRON_WINDOW_PANE.get());

        CreateRegistrate.connectedTextures(WeatheredIronWindowCTBehaviour::new)
                .accept(RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW.get());

        CreateRegistrate.connectedTextures(WeatheredIronWindowPaneCTBehaviour::new)
                .accept(RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW_PANE.get());
    }
}