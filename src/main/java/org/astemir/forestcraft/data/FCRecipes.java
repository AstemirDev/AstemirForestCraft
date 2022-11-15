package org.astemir.forestcraft.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCNewBlocks;

import java.util.function.Consumer;

public class FCRecipes extends RecipeProvider {

    public FCRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(FCNewBlocks.SKY_STONE.asItem(),1).addIngredient(FCItems.SKY_FRAGMENT).addCriterion("has_sky_fragment",hasItem(FCItems.SKY_FRAGMENT)).setGroup("cobblestone").addIngredient(Blocks.COBBLESTONE.asItem()).addCriterion("has_cobblestone",hasItem(Blocks.COBBLESTONE.asItem())).setGroup("cobblestone").build(consumer);
    }
}
