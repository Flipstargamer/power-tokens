package me.flipstargamer.kinetica.datagen;

import me.flipstargamer.kinetica.Kinetica;
import me.flipstargamer.kinetica.advancements.PowerObtainedTriggerInstance;
import me.flipstargamer.kinetica.items.ModItems;
import me.flipstargamer.kinetica.powers.PowerPredicate;
import me.flipstargamer.kinetica.powers.Powers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.RecipeCraftedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(@NotNull HolderLookup.Provider registries, @NotNull Consumer<AdvancementHolder> writer) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ModItems.POWER_TOKEN.get(),
                        Component.translatable("advancements.kinetica.root.title"),
                        Component.translatable("advancements.kinetica.root.description"),
                        Identifier.withDefaultNamespace("textures/block/dirt"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtain_a_power", PowerObtainedTriggerInstance.create(new PowerPredicate(Optional.empty())))
                .save(writer, easyId("main/root"));

        Advancement.Builder.advancement()
                .display(
                        Items.NETHER_STAR,
                        Component.translatable("advancements.kinetica.craftedtoken.title"),
                        Component.translatable("advancements.kinetica.craftedtoken.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        true
                )
                .parent(root)
                .addCriterion("craft_power_token",
                        RecipeCraftedTrigger.TriggerInstance.craftedItem(ResourceKey.create(Registries.RECIPE, easyId("power_token"))))
                .save(writer, easyId("main/crafted_token"));

        Advancement.Builder.advancement()
                .display(
                        ModItems.POWER_TOKEN.get(),
                        Component.translatable("advancements.kinetica.speed.title"),
                        Component.translatable("advancements.kinetica.speed.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtain_speed_boost_power",
                        PowerObtainedTriggerInstance.create(new PowerPredicate(Optional.of(Powers.SPEED_POWER))))
                .parent(root)
                .save(writer, easyId("main/speed_boost"));
    }

    private Identifier easyId(String path) {
        return Identifier.fromNamespaceAndPath(Kinetica.MOD_ID, path);
    }
}
