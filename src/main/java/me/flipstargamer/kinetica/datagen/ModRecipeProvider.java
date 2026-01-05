package me.flipstargamer.kinetica.datagen;

import me.flipstargamer.kinetica.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
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
                .pattern(" S ")
                .pattern("SCS")
                .pattern(" S ")
                .define('S', ModItems.POWER_TOKEN_SHARD)
                .define('C', ModItems.POWER_TOKEN_CORE)
                .unlockedBy("has_core", has(ModItems.POWER_TOKEN_CORE))
                .save(this.output);

        shaped(RecipeCategory.COMBAT, ModItems.POWER_TOKEN_CORE.get())
                .define('x', Tags.Items.NETHER_STARS)
                .define('N', Tags.Items.INGOTS_NETHERITE)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .pattern("DND")
                .pattern("DxD")
                .pattern("DND")
                .unlockedBy("has_nether_star", has(Tags.Items.NETHER_STARS))
                .save(this.output);

        shaped(RecipeCategory.COMBAT, ModItems.POWER_TOKEN_SHARD.get())
                .pattern("GIG")
                .pattern("GNG")
                .pattern("GIG")
                .define('G', Blocks.GOLD_BLOCK)
                .define('I', Blocks.IRON_BLOCK)
                .define('N', Tags.Items.INGOTS_NETHERITE)
                .unlockedBy("has_netherite", has(Tags.Items.INGOTS_NETHERITE))
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
