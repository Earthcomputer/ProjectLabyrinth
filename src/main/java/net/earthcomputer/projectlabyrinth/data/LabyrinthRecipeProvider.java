package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.block.LabyrinthBlocks;
import net.earthcomputer.projectlabyrinth.item.LabyrinthItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class LabyrinthRecipeProvider extends RecipeProvider {
    public LabyrinthRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, LabyrinthItems.GAMER_CHAIR.get())
            .pattern("w")
            .pattern("s")
            .pattern("f")
            .define('w', ItemTags.WOOL)
            .define('s', ItemTags.WOODEN_STAIRS)
            .define('f', ItemTags.WOODEN_FENCES)
            .unlockedBy("has_stairs", has(ItemTags.STAIRS))
            .save(writer);

        nineBlockStorageRecipes(writer, RecipeCategory.MISC, Items.SEAGRASS, RecipeCategory.BUILDING_BLOCKS, LabyrinthBlocks.PACKED_SEAGRASS.get(), "projectlabyrinth:packed_seagrass", null, "projectlabyrinth:seagrass", null);
    }
}
