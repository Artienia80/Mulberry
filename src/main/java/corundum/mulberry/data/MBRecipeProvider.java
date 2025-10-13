package corundum.mulberry.data;

import java.util.concurrent.CompletableFuture;
import corundum.mulberry.Mulberry;
import corundum.mulberry.content.MBBlocks;
import corundum.mulberry.content.MBItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class MBRecipeProvider extends RecipeProvider {
    private final PackOutput output;

    protected MBRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
        this.output = output;
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        smelting(
                recipeOutput,
                MBItems.PYRITE,
                Items.IRON_NUGGET,
                600,
                0.1f
        );

        freezing(
                recipeOutput,
                MBItems.METEORITE_SLAG,
                MBItems.METEORITE_INGOT,
                400,
                1.0f
        );

        twoByTwo(
                recipeOutput,
                MBItems.METEORITE_INGOT,
                MBItems.METEORITE_PLATE,
                1
        );

        threeByThree(
                recipeOutput,
                MBItems.METEORITE_INGOT,
                MBBlocks.METEORITE_BLOCK,
                1
        );

        threeByThree(
                recipeOutput,
                MBItems.METEORITE_SLAG,
                MBBlocks.METEORITE_SLAG_BLOCK,
                1
        );

        threeByThree(
                recipeOutput,
                MBItems.METEORITE_NUGGET,
                MBItems.METEORITE_INGOT,
                1
        );

        threeByThree(
                recipeOutput,
                MBItems.PYRITE,
                MBBlocks.PYRITE_BLOCK,
                1
        );

        one(
                recipeOutput,
                MBItems.METEORITE_INGOT,
                MBItems.METEORITE_NUGGET,
                9
        );

        one(
                recipeOutput,
                MBBlocks.METEORITE_BLOCK,
                MBItems.METEORITE_INGOT,
                9
        );

        one(
                recipeOutput,
                MBBlocks.METEORITE_SLAG_BLOCK,
                MBItems.METEORITE_SLAG,
                9
        );

        one(
                recipeOutput,
                MBBlocks.PYRITE_BLOCK,
                MBItems.PYRITE,
                9
        );



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MBItems.IRON_PLATE, 1)
                .define('X', Items.IRON_INGOT)
                .pattern("XX")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(MBItems.IRON_PLATE), has(MBItems.IRON_PLATE))
                .save(recipeOutput);


        // Chainmail Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.CHAINMAIL_HELMET, 1)
                .define('X', Items.CHAIN)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy(getHasName(Items.CHAIN), has(Items.CHAIN))
                .unlockedBy(getHasName(Items.CHAINMAIL_HELMET), has(Items.CHAINMAIL_HELMET))
                .save(recipeOutput);

        // Chainmail Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.CHAINMAIL_CHESTPLATE, 1)
                .define('X', Items.CHAIN)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.CHAIN), has(Items.CHAIN))
                .unlockedBy(getHasName(Items.CHAINMAIL_CHESTPLATE), has(Items.CHAINMAIL_CHESTPLATE))
                .save(recipeOutput);

        // Chainmail Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.CHAINMAIL_LEGGINGS, 1)
                .define('X', Items.CHAIN)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(Items.CHAIN), has(Items.CHAIN))
                .unlockedBy(getHasName(Items.CHAINMAIL_LEGGINGS), has(Items.CHAINMAIL_LEGGINGS))
                .save(recipeOutput);

        // Chainmail Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.CHAINMAIL_BOOTS, 1)
                .define('X', Items.CHAIN)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(Items.CHAIN), has(Items.CHAIN))
                .unlockedBy(getHasName(Items.CHAINMAIL_BOOTS), has(Items.CHAINMAIL_BOOTS))
                .save(recipeOutput);

    }

    private void twoByTwo(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .define('I', input)
                .pattern("II")
                .pattern("II")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, Mulberry.asResource(getItemName(output) + "_via_twobytwo"));
    }

    private void oneByTwo(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .define('I', input)
                .pattern("I")
                .pattern("I")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, Mulberry.asResource(getItemName(output) + "_via_onebytwo"));
    }

    private void threeByThree(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .define('I', input)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, Mulberry.asResource(getItemName(output) + "_via_threebythree"));
    }

    private void one(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .define('I', input)
                .pattern("I")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, Mulberry.asResource(getItemName(output) + "_via_one"));
    }

    private void stairsAndSlab(RecipeOutput recipeOutput, ItemLike input, ItemLike stairs, ItemLike slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                .define('S', input)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
                .define('S', input)
                .pattern("SSS")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput);
    }

    private void wall(RecipeOutput recipeOutput, ItemLike input, ItemLike wall) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wall, 6)
                .define('P', input)
                .pattern("PPP")
                .pattern("PPP")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput);
    }

    private void pane(RecipeOutput recipeOutput, ItemLike input, ItemLike pane) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, pane, 16)
                .define('P', input)
                .pattern("PPP")
                .pattern("PPP")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput);
    }

    private void grate(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .group(output.toString())
                .define('I', input)
                .pattern(" I ")
                .pattern("I I")
                .pattern(" I ")
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput);
    }

    private void stonecutterList(RecipeOutput recipeOutput, ItemLike input, ItemLike... outputs) {
        for (ItemLike output : outputs) {
            var count = 1;

            if (output.asItem().toString().contains("slab")) {
                count = 2;
            }

            stonecutterResultFromBase(
                    recipeOutput,
                    RecipeCategory.BUILDING_BLOCKS,
                    output,
                    input,
                    count
            );
        }
    }

    private void smelting(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int cookingTime, float experience) {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(input),
                        RecipeCategory.MISC,
                        output,
                        experience,
                        cookingTime
                )
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, getItemName(output) + "_from_smelting_" + getItemName(input));
    }

    private void freezing(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int cookingTime, float experience) {
        String recipeName = getItemName(output) + "_from_freezing_" + getItemName(input);

        RecipeOutput conditionalOutput = recipeOutput.withConditions(new ModLoadedCondition("rubinated_nether"));

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(input),
                        RecipeCategory.MISC,
                        output,
                        experience,
                        cookingTime
                )
                .unlockedBy(getHasName(input), has(input))
                .save(conditionalOutput, Mulberry.asResource(recipeName));
    }
}