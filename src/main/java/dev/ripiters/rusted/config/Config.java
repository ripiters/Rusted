package dev.ripiters.rusted.config;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.common.RustedBlocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Rusted.MOD_ID)
public class Config {

    public static final ModConfigSpec COMMON_SPEC;
    public static final ModConfigSpec STARTUP_SPEC;

    public static final ModConfigSpec.BooleanValue ENABLE_RUSTING;
    public static final ModConfigSpec.BooleanValue VANILLA_IRON_RUSTS;
    public static final ModConfigSpec.DoubleValue NORMAL_RUST_CHANCE;
    public static final ModConfigSpec.DoubleValue WET_RUST_CHANCE;
    public static final ModConfigSpec.BooleanValue RAIN_ACCELERATION;
    public static final ModConfigSpec.BooleanValue WATER_ACCELERATION;
    public static final ModConfigSpec.BooleanValue ENABLE_PARTICLES;

    public static final ModConfigSpec.BooleanValue ENABLE_CREATE_COMPAT;

    static {
        ModConfigSpec.Builder commonBuilder = new ModConfigSpec.Builder();

        commonBuilder.comment("Rusted Mod General Configuration")
                .translation("rusted.config.title")
                .push("rusting");

        ENABLE_RUSTING = commonBuilder
                .comment("Enable or disable all iron rusting mechanics.")
                .translation("rusted.config.enable_rusting")
                .define("enableRusting", true);

        VANILLA_IRON_RUSTS = commonBuilder
                .comment("Allow vanilla iron blocks to rust over time.")
                .translation("rusted.config.vanilla_iron_rusts")
                .define("vanillaIronRusts", true);

        NORMAL_RUST_CHANCE = commonBuilder
                .comment("Chance of rusting per random tick in normal, dry conditions (0.0 to 1.0).")
                .translation("rusted.config.normal_rust_chance")
                .defineInRange("normalRustChance", 0.05D, 0.0D, 1.0D);

        WET_RUST_CHANCE = commonBuilder
                .comment("Chance of rusting per random tick during rain or while underwater (0.0 to 1.0).")
                .translation("rusted.config.wet_rust_chance")
                .defineInRange("wetRustChance", 0.25D, 0.0D, 1.0D);

        RAIN_ACCELERATION = commonBuilder
                .comment("Enable faster rusting when blocks are exposed to rain.")
                .translation("rusted.config.rain_acceleration")
                .define("rainAcceleration", true);

        WATER_ACCELERATION = commonBuilder
                .comment("Enable faster rusting when blocks are touching water.")
                .translation("rusted.config.water_acceleration")
                .define("waterAcceleration", true);

        ENABLE_PARTICLES = commonBuilder
                .comment("Enable particles when scraping rust or unwaxing blocks with an axe.")
                .translation("rusted.config.enable_particles")
                .define("enableParticles", false);

        commonBuilder.pop();
        COMMON_SPEC = commonBuilder.build();

        ModConfigSpec.Builder startupBuilder = new ModConfigSpec.Builder();

        startupBuilder.push("compat").push("create");

        ENABLE_CREATE_COMPAT = startupBuilder
                .comment("Enables rusting, axe scraping, and waxing mechanics for Create's Industrial Iron blocks and windows.",
                        "§cREQUIRES GAME RESTART!",
                        "§cWARNING: Disabling this option will remove all Create compatibility blocks placed in your world!")
                .translation("rusted.config.compat.create")
                .define("enableCreateCompat", true);

        startupBuilder.pop().pop();
        STARTUP_SPEC = startupBuilder.build();
    }

    public static boolean isCreateCompatEnabled() {
        if (!Rusted.Mods.isCreateLoaded()) {
            return false;
        }
        return STARTUP_SPEC != null && STARTUP_SPEC.isLoaded() && ENABLE_CREATE_COMPAT.get();
    }

    @SubscribeEvent
    public static void onConfigLoad(ModConfigEvent event) {
        if (event.getConfig().getSpec() == COMMON_SPEC || event.getConfig().getSpec() == STARTUP_SPEC) {
            RustedBlocks.setupMaps();
        }
    }
}