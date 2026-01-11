package me.flipstargamer.powertokens.datagen;

import me.flipstargamer.powertokens.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.COMBAT, ModItems.POWER_TOKEN.get())
                .pattern("GDG")
                .pattern("DBD")
                .pattern("GDG")
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('B', Blocks.DIAMOND_BLOCK)
                .define('G', Tags.Items.INGOTS_GOLD)
                .unlockedBy("has_diamonds", has(Tags.Items.GEMS_DIAMOND))
                .save(this.output);

        shaped(RecipeCategory.COMBAT, ModItems.UNSTABLE_POWER_TOKEN.get())
                .pattern("igi")
                .pattern("igi")
                .pattern("igi")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('g', Tags.Items.INGOTS_GOLD)
                .unlockedBy("has_gold", has(Tags.Items.INGOTS_GOLD))
                .save(this.output);

        shaped(RecipeCategory.COMBAT, ModItems.REINFORCED_TOKEN.get())
                .pattern("SDS")
                .pattern("TNT")
                .pattern("SDS")
                .define('S', Tags.Items.ORES_NETHERITE_SCRAP)
                .define('N', Tags.Items.INGOTS_NETHERITE)
                .define('T', ModItems.POWER_TOKEN.get())
                .define('D', Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_netherite", has(Tags.Items.INGOTS_NETHERITE))
                .save(this.output);

        shaped(RecipeCategory.COMBAT, ModItems.INVERTED_TOKEN.get())
                .pattern("gig")
                .pattern("gtg")
                .pattern("gig")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('g', Tags.Items.INGOTS_GOLD)
                .define('t', ModItems.UNSTABLE_POWER_TOKEN.get())
                .unlockedBy("has_gold", has(Tags.Items.INGOTS_GOLD))
                .save(this.output);
    }

    public static class Runner extends RecipeProvider.Runner {

        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider registries, @NotNull RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public @NotNull String getName() {
            return "kinetica";
        }
    }
}
