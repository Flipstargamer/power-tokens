package me.flipstargamer.powertokens.datagen;

import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.advancements.PowerObtainedTrigger;
import me.flipstargamer.powertokens.advancements.PowersWipedTrigger;
import me.flipstargamer.powertokens.items.ModItems;
import me.flipstargamer.powertokens.powers.PowerPredicate;
import me.flipstargamer.powertokens.powers.Powers;
import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class ModAdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(@NotNull HolderLookup.Provider registries, @NotNull Consumer<AdvancementHolder> writer) {
        List<Holder<Power>> allPowers = PowerTokenRegistries.POWER_REGISTRY.stream().map(PowerTokenRegistries.POWER_REGISTRY::wrapAsHolder).toList();

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
                .addCriterion("obtain_a_power", PowerObtainedTrigger.createCriterion(new PowerPredicate(Optional.empty())))
                .save(writer, easyId("main/root"));

        AdvancementHolder speedAdvancement = Advancement.Builder.advancement()
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
                        PowerObtainedTrigger.createCriterion(new PowerPredicate(Optional.of(Powers.SPEED_POWER))))
                .parent(root)
                .save(writer, easyId("main/speed_boost"));

        AdvancementHolder wipedPowers = Advancement.Builder.advancement()
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

        Advancement.Builder allPowersBuilder = Advancement.Builder.advancement()
                .display(
                        ModItems.REINFORCED_TOKEN.get(),
                        Component.translatable("advancements.power_tokens.all_powers.title"),
                        Component.translatable("advancements.power_tokens.all_powers.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(wipedPowers);

        for (Holder<Power> power : allPowers) {
            allPowersBuilder.addCriterion("has_" + Objects.requireNonNull(power.getKey()).identifier().getPath(),
                    PowerObtainedTrigger.createCriterion(new PowerPredicate(Optional.of(power))));
        }

        allPowersBuilder.save(writer, easyId("main/all_powers"));

        AdvancementHolder vampireAdvancement = Advancement.Builder.advancement()
                .display(
                        Items.GUNPOWDER,
                        Component.translatable("advancements.power_tokens.vampire.title"),
                        Component.translatable("advancements.power_tokens.vampire.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .parent(speedAdvancement)
                .addCriterion("has_vampire",
                        PowerObtainedTrigger.createCriterion(new PowerPredicate(Optional.of(Powers.VAMPIRE))))
                .save(writer, easyId("main/vampire"));
    }

    private Identifier easyId(String path) {
        return Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, path);
    }
}
