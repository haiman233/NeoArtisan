package io.github.moyusowo.neoartisan.recipe.listener;

import io.github.moyusowo.neoartisan.NeoArtisan;
import io.github.moyusowo.neoartisan.util.init.InitMethod;
import io.github.moyusowo.neoartisan.util.init.InitPriority;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanCampfireRecipe;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanRecipe;
import io.github.moyusowo.neoartisanapi.api.recipe.RecipeType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;

import java.util.Collection;
import java.util.UUID;

final class CampfireListener implements Listener {
    private CampfireListener() {}

    @InitMethod(priority = InitPriority.LISTENER)
    static void registerListener() {
        NeoArtisan.registerListener(new CampfireListener());
    }

    private static boolean hasRecipe(ItemStack itemStack) {
        for (Recipe recipe : Bukkit.getRecipesFor(itemStack)) {
            if (recipe instanceof CampfireRecipe campfireRecipe) {
                if (campfireRecipe.getInputChoice() instanceof RecipeChoice.ExactChoice exactChoice) {
                    if (exactChoice.test(itemStack)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        assert event.getClickedBlock() != null;
        if (event.getClickedBlock().getType() != Material.CAMPFIRE) return;
        final ItemStack itemStack = event.getItem();
        if (itemStack == null || itemStack.isEmpty()) return;
        if (!hasRecipe(itemStack)) {
            final Collection<ArtisanRecipe> campfireRecipes = NeoArtisanAPI.getRecipeRegistry().getRecipes(RecipeType.CAMPFIRE);
            for (ArtisanRecipe artisanRecipe : campfireRecipes) {
                if (artisanRecipe instanceof ArtisanCampfireRecipe campfireRecipe) {
                    if (campfireRecipe.matches(new ItemStack[] { itemStack })) {
                        FurnaceRecipe recipe = new FurnaceRecipe(
                                new NamespacedKey(campfireRecipe.getKey().namespace(), campfireRecipe.getKey().getKey() + "_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString().toLowerCase()),
                                campfireRecipe.getResultGenerator().generate(),
                                new RecipeChoice.ExactChoice(itemStack),
                                campfireRecipe.getExp(),
                                campfireRecipe.getCookTime()
                        );
                        Bukkit.addRecipe(recipe);
                        return;
                    }
                }
            }
        }
    }
}
