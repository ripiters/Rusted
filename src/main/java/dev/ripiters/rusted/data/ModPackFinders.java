package dev.ripiters.rusted.data;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.config.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.event.AddPackFindersEvent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@EventBusSubscriber(modid = Rusted.MOD_ID)
public class ModPackFinders {

    @SubscribeEvent
    public static void registerPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA && Config.isCreateCompatEnabled()) {

            Path resourcePath = FMLLoader.getLoadingModList()
                    .getModFileById(Rusted.MOD_ID)
                    .getFile()
                    .findResource("packs", "create_compat");

            if (!Files.exists(resourcePath)) {
                Rusted.LOGGER.error("Could not find Create Compat datapack path: {}", resourcePath);
                return;
            }

            PackLocationInfo locationInfo = new PackLocationInfo(
                    "rusted_create_compat",
                    Component.literal("Rusted Create Compat Data"),
                    PackSource.FEATURE,
                    Optional.empty()
            );

            PackSelectionConfig selectionConfig = new PackSelectionConfig(true, Pack.Position.TOP, false);

            Pack pack = Pack.readMetaAndCreate(
                    locationInfo,
                    new Pack.ResourcesSupplier() {
                        @Override
                        public PackResources openPrimary(PackLocationInfo info) {
                            return new PathPackResources(info, resourcePath);
                        }

                        @Override
                        public PackResources openFull(PackLocationInfo info, Pack.Metadata metadata) {
                            return openPrimary(info);
                        }
                    },
                    PackType.SERVER_DATA,
                    selectionConfig
            );

            if (pack != null) {
                event.addRepositorySource(consumer -> consumer.accept(pack));
                Rusted.LOGGER.info("Successfully registered Create Compat Datapack!");
            }
        }
    }
}