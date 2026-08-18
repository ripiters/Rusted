package dev.ripiters.rusted.common.network;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.common.network.packets.RustScrapePayload;
import dev.ripiters.rusted.common.network.packets.WaxOffPayload;
import dev.ripiters.rusted.common.network.packets.WaxOnPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Rusted.MOD_ID)
public class RustedNetwork {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                RustScrapePayload.TYPE,
                RustScrapePayload.STREAM_CODEC,
                RustScrapePayload::handle
        );
        registrar.playToClient(
                WaxOnPayload.TYPE,
                WaxOnPayload.STREAM_CODEC,
                WaxOnPayload::handle
        );
        registrar.playToClient(
                WaxOffPayload.TYPE,
                WaxOffPayload.STREAM_CODEC,
                WaxOffPayload::handle
        );
    }
}