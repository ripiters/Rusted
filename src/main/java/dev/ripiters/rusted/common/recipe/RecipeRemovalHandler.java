package dev.ripiters.rusted.common.recipe;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.config.Config;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

import java.util.List;

@EventBusSubscriber(modid = Rusted.MOD_ID)
public class RecipeRemovalHandler {

    private static final ResourceLocation TARGET_RECIPE_ID = ResourceLocation.fromNamespaceAndPath("create", "weathered_iron_block_from_ingots_iron_stonecutting");

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        if (!Rusted.Mods.isCreateLoaded() || !Config.isCreateCompatEnabled()) return;

        var recipeManager = event.getPlayerList().getServer().getRecipeManager();
        removeTargetRecipe(recipeManager);
    }

    @SubscribeEvent
    public static void onRecipesUpdated(RecipesUpdatedEvent event) {
        if (!Rusted.Mods.isCreateLoaded() || !Config.isCreateCompatEnabled()) return;

        removeTargetRecipe(event.getRecipeManager());
    }

    private static void removeTargetRecipe(net.minecraft.world.item.crafting.RecipeManager recipeManager) {
        if (recipeManager.byKey(TARGET_RECIPE_ID).isPresent()) {
            List<RecipeHolder<?>> filteredRecipes = recipeManager.getRecipes().stream()
                    .filter(recipeHolder -> !recipeHolder.id().equals(TARGET_RECIPE_ID))
                    .toList();

            recipeManager.replaceRecipes(filteredRecipes);
        }
    }
}