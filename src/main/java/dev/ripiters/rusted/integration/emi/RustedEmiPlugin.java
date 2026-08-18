package dev.ripiters.rusted.integration.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.common.RustedBlocks;
import dev.ripiters.rusted.config.Config;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

@EmiEntrypoint
public class RustedEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        // --- IRON BLOCK ---
        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/waxing/rusted/iron_block"))
                .leftInput(EmiStack.of(Blocks.IRON_BLOCK))
                .rightInput(EmiStack.of(Items.HONEYCOMB), false)
                .output(EmiStack.of(RustedBlocks.WAXED_IRON_BLOCK.get()))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/waxing/rusted/exposed_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.EXPOSED_IRON_BLOCK.get()))
                .rightInput(EmiStack.of(Items.HONEYCOMB), false)
                .output(EmiStack.of(RustedBlocks.WAXED_EXPOSED_IRON_BLOCK.get()))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/waxing/rusted/weathered_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.WEATHERED_IRON_BLOCK.get()))
                .rightInput(EmiStack.of(Items.HONEYCOMB), false)
                .output(EmiStack.of(RustedBlocks.WAXED_WEATHERED_IRON_BLOCK.get()))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/waxing/rusted/rusted_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.RUSTED_IRON_BLOCK.get()))
                .rightInput(EmiStack.of(Items.HONEYCOMB), false)
                .output(EmiStack.of(RustedBlocks.WAXED_RUSTED_IRON_BLOCK.get()))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/stripping/rusted/exposed_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.EXPOSED_IRON_BLOCK.get()))
                .rightInput(EmiIngredient.of(ItemTags.AXES), false)
                .output(EmiStack.of(Blocks.IRON_BLOCK))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/stripping/rusted/weathered_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.WEATHERED_IRON_BLOCK.get()))
                .rightInput(EmiIngredient.of(ItemTags.AXES), false)
                .output(EmiStack.of(RustedBlocks.EXPOSED_IRON_BLOCK.get()))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/stripping/rusted/rusted_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.RUSTED_IRON_BLOCK.get()))
                .rightInput(EmiIngredient.of(ItemTags.AXES), false)
                .output(EmiStack.of(RustedBlocks.WEATHERED_IRON_BLOCK.get()))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/stripping/rusted/waxed_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.WAXED_IRON_BLOCK.get()))
                .rightInput(EmiIngredient.of(ItemTags.AXES), false)
                .output(EmiStack.of(Blocks.IRON_BLOCK))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/stripping/rusted/waxed_exposed_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.WAXED_EXPOSED_IRON_BLOCK.get()))
                .rightInput(EmiIngredient.of(ItemTags.AXES), false)
                .output(EmiStack.of(RustedBlocks.EXPOSED_IRON_BLOCK.get()))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/stripping/rusted/waxed_weathered_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.WAXED_WEATHERED_IRON_BLOCK.get()))
                .rightInput(EmiIngredient.of(ItemTags.AXES), false)
                .output(EmiStack.of(RustedBlocks.WEATHERED_IRON_BLOCK.get()))
                .build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/stripping/rusted/waxed_rusted_iron_block"))
                .leftInput(EmiStack.of(RustedBlocks.WAXED_RUSTED_IRON_BLOCK.get()))
                .rightInput(EmiIngredient.of(ItemTags.AXES), false)
                .output(EmiStack.of(RustedBlocks.RUSTED_IRON_BLOCK.get()))
                .build());

        // --- CUT IRON BLOCK ---
        addWeatheringGroup(registry, "cut_iron_block",
                RustedBlocks.CUT_IRON_BLOCK.get(), RustedBlocks.EXPOSED_CUT_IRON_BLOCK.get(), RustedBlocks.WEATHERED_CUT_IRON_BLOCK.get(), RustedBlocks.RUSTED_CUT_IRON_BLOCK.get(),
                RustedBlocks.WAXED_CUT_IRON_BLOCK.get(), RustedBlocks.WAXED_EXPOSED_CUT_IRON_BLOCK.get(), RustedBlocks.WAXED_WEATHERED_CUT_IRON_BLOCK.get(), RustedBlocks.WAXED_RUSTED_CUT_IRON_BLOCK.get()
        );

        // --- CUT IRON STAIRS ---
        addWeatheringGroup(registry, "cut_iron_stairs",
                RustedBlocks.CUT_IRON_STAIRS.get(), RustedBlocks.EXPOSED_CUT_IRON_STAIRS.get(), RustedBlocks.WEATHERED_CUT_IRON_STAIRS.get(), RustedBlocks.RUSTED_CUT_IRON_STAIRS.get(),
                RustedBlocks.WAXED_CUT_IRON_STAIRS.get(), RustedBlocks.WAXED_EXPOSED_CUT_IRON_STAIRS.get(), RustedBlocks.WAXED_WEATHERED_CUT_IRON_STAIRS.get(), RustedBlocks.WAXED_RUSTED_CUT_IRON_STAIRS.get()
        );

        // --- CUT IRON SLAB ---
        addWeatheringGroup(registry, "cut_iron_slab",
                RustedBlocks.CUT_IRON_SLAB.get(), RustedBlocks.EXPOSED_CUT_IRON_SLAB.get(), RustedBlocks.WEATHERED_CUT_IRON_SLAB.get(), RustedBlocks.RUSTED_CUT_IRON_SLAB.get(),
                RustedBlocks.WAXED_CUT_IRON_SLAB.get(), RustedBlocks.WAXED_EXPOSED_CUT_IRON_SLAB.get(), RustedBlocks.WAXED_WEATHERED_CUT_IRON_SLAB.get(), RustedBlocks.WAXED_RUSTED_CUT_IRON_SLAB.get()
        );

        // --- CREATE MOD COMPAT ---
        if (Config.isCreateCompatEnabled()) {
            addCreateCompatRecipes(registry, "industrial_iron_block", "weathered_iron_block",
                    RustedBlocks.WAXED_INDUSTRIAL_IRON_BLOCK.get(), RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_BLOCK.get());
            addCreateCompatRecipes(registry, "industrial_iron_window", "weathered_iron_window",
                    RustedBlocks.WAXED_INDUSTRIAL_IRON_WINDOW.get(), RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW.get());
            addCreateCompatRecipes(registry, "industrial_iron_window_pane", "weathered_iron_window_pane",
                    RustedBlocks.WAXED_INDUSTRIAL_IRON_WINDOW_PANE.get(), RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW_PANE.get());
        }
    }

    private void addCreateCompatRecipes(EmiRegistry registry, String baseName, String weatheredName, Block waxedBase, Block waxedWeathered) {
        Block base = RustedBlocks.getBlock("create", baseName);
        Block weathered = RustedBlocks.getBlock("create", weatheredName);

        if (base != Blocks.AIR && weathered != Blocks.AIR) {
            registry.addRecipe(wax(base, waxedBase, "create_" + baseName));
            registry.addRecipe(wax(weathered, waxedWeathered, "create_" + weatheredName));
            registry.addRecipe(scrape(weathered, base, "create_" + weatheredName));
            registry.addRecipe(scrape(waxedBase, base, "create_waxed_" + baseName));
            registry.addRecipe(scrape(waxedWeathered, weathered, "create_waxed_" + weatheredName));
        }
    }

    private void addWeatheringGroup(EmiRegistry registry, String name, Block base, Block exposed, Block weathered, Block rusted, Block waxedBase, Block waxedExposed, Block waxedWeathered, Block waxedRusted) {
        // Waxing
        registry.addRecipe(wax(base, waxedBase, name));
        registry.addRecipe(wax(exposed, waxedExposed, "exposed_" + name));
        registry.addRecipe(wax(weathered, waxedWeathered, "weathered_" + name));
        registry.addRecipe(wax(rusted, waxedRusted, "rusted_" + name));

        // Scraping rust
        registry.addRecipe(scrape(exposed, base, "exposed_" + name));
        registry.addRecipe(scrape(weathered, exposed, "weathered_" + name));
        registry.addRecipe(scrape(rusted, weathered, "rusted_" + name));

        // Scraping wax
        registry.addRecipe(scrape(waxedBase, base, "waxed_" + name));
        registry.addRecipe(scrape(waxedExposed, exposed, "waxed_exposed_" + name));
        registry.addRecipe(scrape(waxedWeathered, weathered, "waxed_weathered_" + name));
        registry.addRecipe(scrape(waxedRusted, rusted, "waxed_rusted_" + name));
    }

    private EmiWorldInteractionRecipe wax(ItemLike input, ItemLike output, String path) {
        return EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/waxing/" + path))
                .leftInput(EmiStack.of(input))
                .rightInput(EmiStack.of(Items.HONEYCOMB), false)
                .output(EmiStack.of(output))
                .build();
    }

    private EmiWorldInteractionRecipe scrape(ItemLike input, ItemLike output, String path) {
        return EmiWorldInteractionRecipe.builder()
                .id(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "/world/stripping/" + path))
                .leftInput(EmiStack.of(input))
                .rightInput(EmiIngredient.of(ItemTags.AXES), false)
                .output(EmiStack.of(output))
                .build();
    }
}