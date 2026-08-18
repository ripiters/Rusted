package dev.ripiters.rusted.client;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.common.RustedBlocks;
import dev.ripiters.rusted.config.Config;
import dev.ripiters.rusted.integration.create.CreateCompatClient;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Rusted.MOD_ID)
public class ClientModEvents {

    @SubscribeEvent
    @SuppressWarnings("deprecation")
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            if (Config.isCreateCompatEnabled()) {
                ItemBlockRenderTypes.setRenderLayer(RustedBlocks.WAXED_INDUSTRIAL_IRON_WINDOW.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(RustedBlocks.WAXED_INDUSTRIAL_IRON_WINDOW_PANE.get(), RenderType.cutoutMipped());

                ItemBlockRenderTypes.setRenderLayer(RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW_PANE.get(), RenderType.translucent());

                CreateCompatClient.registerCTM();

            }
        });
    }
}