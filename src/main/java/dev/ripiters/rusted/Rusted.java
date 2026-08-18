package dev.ripiters.rusted;

import dev.emi.emi.config.EmiConfig;
import dev.ripiters.rusted.common.*;
import dev.ripiters.rusted.common.event.RustedEvents;
import dev.ripiters.rusted.config.Config;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Rusted.MOD_ID)
public class Rusted {

    public static final String MOD_ID = "rusted";
    public static final String NAME = "Rusted";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    private final ModContainer modContainer;

    public Rusted(IEventBus modEventBus, ModContainer modContainer) {
        this.modContainer = modContainer;

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        modContainer.registerConfig(ModConfig.Type.STARTUP, Config.STARTUP_SPEC);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        RustedBlocks.BLOCKS.register(modEventBus);
        RustedBlocks.ITEMS.register(modEventBus);
        RustedItems.ITEMS.register(modEventBus);
        RustedCreativeTabs.CREATIVE_TABS.register(modEventBus);
        RustedParticles.PARTICLE_TYPES.register(modEventBus);

        NeoForge.EVENT_BUS.register(RustedEvents.class);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Rusted::doSetup);
        LOGGER.info(NAME + " common setup complete!");
    }

    private void clientSetup(FMLClientSetupEvent event) {
        new RustedClient(this.modContainer);
        LOGGER.info(NAME + " client setup complete!");
    }

    private static void doSetup() {
        RustedBlocks.setupMaps();
    }

    public static boolean isModLoaded(String modId) {
        ModList modList = ModList.get();
        if (modList != null) return modList.isLoaded(modId);
        else return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }

    public static boolean isClientThread() {
        return isClientSide() && Minecraft.getInstance().isSameThread();
    }

    public static boolean isClientSide() {
        return FMLEnvironment.dist.isClient();
    }

    public static final String
            MODID_JEI = "jei",
            MODID_REI = "roughlyenoughitems",
            MODID_EMI = "emi",
            MODID_CREATE = "create";

    public static class Mods {
        public static boolean isJEILoaded() { return !(isModLoaded(MODID_EMI) || isModLoaded(MODID_REI)) && isModLoaded(MODID_JEI); }
        public static boolean isEMILoaded() { return isModLoaded(MODID_EMI) && (!isClientSide() || EmiConfig.enabled); }
        public static boolean isCreateLoaded() { return isModLoaded(MODID_CREATE); }
    }
}