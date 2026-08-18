package dev.ripiters.rusted;

import dev.ripiters.rusted.common.RustedParticles;
import net.minecraft.client.particle.GlowParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.fml.ModContainer;

@EventBusSubscriber(modid = Rusted.MOD_ID, value = Dist.CLIENT)
public class RustedClient {

    public RustedClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(RustedParticles.RUST_SCRAPE.get(), sprites -> (type, level, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            GlowParticle.ScrapeProvider provider = new GlowParticle.ScrapeProvider(sprites);
            var particle = provider.createParticle(type, level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setColor(0.52F, 0.22F, 0.08F);
            return particle;
        });

        event.registerSpriteSet(RustedParticles.CREATE_WEATHERED_SCRAPE.get(), sprites -> (type, level, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            GlowParticle.ScrapeProvider provider = new GlowParticle.ScrapeProvider(sprites);
            var particle = provider.createParticle(type, level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setColor(0.12F, 0.35F, 0.28F);
            return particle;
        });
    }
}