package dev.ripiters.rusted.common;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.config.Config;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RustedCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Rusted.MOD_ID);

    public static final Supplier<CreativeModeTab> RUSTED_TAB = CREATIVE_TABS.register("rusted_tab",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group." + Rusted.MOD_ID + ".rusted_tab"))
                    .icon(() -> new ItemStack(RustedBlocks.RUSTED_IRON_BLOCK.get()))
                    .displayItems((params, output) -> {
                        // Standard blocks
                        output.accept(Blocks.IRON_BLOCK);
                        output.accept(RustedBlocks.EXPOSED_IRON_BLOCK.get());
                        output.accept(RustedBlocks.WEATHERED_IRON_BLOCK.get());
                        output.accept(RustedBlocks.RUSTED_IRON_BLOCK.get());

                        // Cut blocks
                        output.accept(RustedBlocks.CUT_IRON_BLOCK.get());
                        output.accept(RustedBlocks.EXPOSED_CUT_IRON_BLOCK.get());
                        output.accept(RustedBlocks.WEATHERED_CUT_IRON_BLOCK.get());
                        output.accept(RustedBlocks.RUSTED_CUT_IRON_BLOCK.get());

                        // Stairs
                        output.accept(RustedBlocks.CUT_IRON_STAIRS.get());
                        output.accept(RustedBlocks.EXPOSED_CUT_IRON_STAIRS.get());
                        output.accept(RustedBlocks.WEATHERED_CUT_IRON_STAIRS.get());
                        output.accept(RustedBlocks.RUSTED_CUT_IRON_STAIRS.get());

                        // Slabs
                        output.accept(RustedBlocks.CUT_IRON_SLAB.get());
                        output.accept(RustedBlocks.EXPOSED_CUT_IRON_SLAB.get());
                        output.accept(RustedBlocks.WEATHERED_CUT_IRON_SLAB.get());
                        output.accept(RustedBlocks.RUSTED_CUT_IRON_SLAB.get());

                        // Waxed standard blocks
                        output.accept(RustedBlocks.WAXED_IRON_BLOCK.get());
                        output.accept(RustedBlocks.WAXED_EXPOSED_IRON_BLOCK.get());
                        output.accept(RustedBlocks.WAXED_WEATHERED_IRON_BLOCK.get());
                        output.accept(RustedBlocks.WAXED_RUSTED_IRON_BLOCK.get());

                        // Waxed cut blocks
                        output.accept(RustedBlocks.WAXED_CUT_IRON_BLOCK.get());
                        output.accept(RustedBlocks.WAXED_EXPOSED_CUT_IRON_BLOCK.get());
                        output.accept(RustedBlocks.WAXED_WEATHERED_CUT_IRON_BLOCK.get());
                        output.accept(RustedBlocks.WAXED_RUSTED_CUT_IRON_BLOCK.get());

                        // Waxed stairs
                        output.accept(RustedBlocks.WAXED_CUT_IRON_STAIRS.get());
                        output.accept(RustedBlocks.WAXED_EXPOSED_CUT_IRON_STAIRS.get());
                        output.accept(RustedBlocks.WAXED_WEATHERED_CUT_IRON_STAIRS.get());
                        output.accept(RustedBlocks.WAXED_RUSTED_CUT_IRON_STAIRS.get());

                        // Waxed slabs
                        output.accept(RustedBlocks.WAXED_CUT_IRON_SLAB.get());
                        output.accept(RustedBlocks.WAXED_EXPOSED_CUT_IRON_SLAB.get());
                        output.accept(RustedBlocks.WAXED_WEATHERED_CUT_IRON_SLAB.get());
                        output.accept(RustedBlocks.WAXED_RUSTED_CUT_IRON_SLAB.get());

                        if (Rusted.Mods.isCreateLoaded()) {
                            output.accept(RustedItems.RUST_POWDER.get());
                        }

                        // Create Compat & Waxed Create Blocks
                        if (Config.isCreateCompatEnabled()) {
                            addCreateItem(output, "industrial_iron_block");
                            addCreateItem(output, "weathered_iron_block");
                            addCreateItem(output, "industrial_iron_window");
                            addCreateItem(output, "weathered_iron_window");
                            addCreateItem(output, "industrial_iron_window_pane");
                            addCreateItem(output, "weathered_iron_window_pane");

                            output.accept(RustedBlocks.WAXED_INDUSTRIAL_IRON_BLOCK.get());
                            output.accept(RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_BLOCK.get());
                            output.accept(RustedBlocks.WAXED_INDUSTRIAL_IRON_WINDOW.get());
                            output.accept(RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW.get());
                            output.accept(RustedBlocks.WAXED_INDUSTRIAL_IRON_WINDOW_PANE.get());
                            output.accept(RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW_PANE.get());

                        }
                    })
                    .build());

    private static void addCreateItem(CreativeModeTab.Output output, String path) {
        Block block = RustedBlocks.getBlock("create", path);
        if (block != Blocks.AIR) {
            output.accept(block);
        }
    }
}