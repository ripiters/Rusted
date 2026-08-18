package dev.ripiters.rusted.common;

import dev.ripiters.rusted.Rusted;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RustedParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, Rusted.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RUST_SCRAPE =
            PARTICLE_TYPES.register("rust_scrape", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CREATE_WEATHERED_SCRAPE =
            PARTICLE_TYPES.register("create_weathered_scrape", () -> new SimpleParticleType(false));
}