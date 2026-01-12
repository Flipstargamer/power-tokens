package me.flipstargamer.powertokens.datagen;

import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.advancements.PowerObtainedTriggerInstance;
import me.flipstargamer.powertokens.advancements.PowersWipedTrigger;
import me.flipstargamer.powertokens.items.ModItems;
import me.flipstargamer.powertokens.powers.PowerPredicate;
import me.flipstargamer.powertokens.powers.Powers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class ModAdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(@NotNull HolderLookup.Provider registries, @NotNull Consumer<AdvancementHolder> writer) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ModItems.POWER_TOKEN.get(),
                        Component.translatable("advancements.power_tokens.root.title"),
                        Component.translatable("advancements.power_tokens.root.description"),
                        Identifier.fromNamespaceAndPath("minecraft", "gui/advancements/backgrounds/stone"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtain_a_power", PowerObtainedTriggerInstance.create(new PowerPredicate(Optional.empty())))
                .save(writer, easyId("main/root"));

        Advancement.Builder.advancement()
                .display(
                        Items.RABBIT_FOOT,
                        Component.translatable("advancements.power_tokens.speed.title"),
                        Component.translatable("advancements.power_tokens.speed.description"),
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

        Advancement.Builder.advancement()
                .display(
                        ModItems.INVERTED_TOKEN.get(),
                        Component.translatable("advancements.power_tokens.wiped.title"),
                        Component.translatable("advancements.power_tokens.wiped.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("wiped_powers", PowersWipedTrigger.createCriterion())
                .parent(root)
                .save(writer, easyId("main/wiped_powers"));
    }

    private Identifier easyId(String path) {
        return Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, path);
    }
}
