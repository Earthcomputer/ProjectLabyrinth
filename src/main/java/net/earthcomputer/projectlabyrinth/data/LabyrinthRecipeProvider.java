package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

public class LabyrinthRecipeProvider extends RecipeProvider {
    public LabyrinthRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ProjectLabyrinth.GAMER_CHAIR_ITEM.get())
            .pattern("w")
            .pattern("s")
            .pattern("f")
            .define('w', ItemTags.WOOL)
            .define('s', ItemTags.WOODEN_STAIRS)
            .define('f', ItemTags.WOODEN_FENCES)
            .unlockedBy("has_stairs", has(ItemTags.STAIRS))
            .save(writer);
    }
}
