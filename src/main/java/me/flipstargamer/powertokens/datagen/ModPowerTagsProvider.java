package me.flipstargamer.powertokens.datagen;

import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.PowerTokenTags;
import me.flipstargamer.powertokens.powers.power.Power;
import me.flipstargamer.powertokens.powers.Powers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModPowerTagsProvider extends TagsProvider<Power> {

    protected ModPowerTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, PowerTokenRegistries.POWER_REGISTRY_KEY, lookupProvider, PowerTokens.MOD_ID);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        getOrCreateRawBuilder(PowerTokenTags.POSITIVE_POWER)
                .addElement(Powers.HEALTH_POWER.getId())
                .addElement(Powers.NATURAL_ARMOR.getId())
                .addElement(Powers.TINY_POWER.getId())
                .addElement(Powers.SPEED_POWER.getId())
                .addElement(Powers.BURN_RESISTANCE.getId())
                .addElement(Powers.MINER_TRAINING.getId())
                .addElement(Powers.SWIFT_HANDS.getId())
                .addElement(Powers.LESS_KNOCKBACK.getId())
                .addElement(Powers.LUNG_CAPACITY.getId())
                .addElement(Powers.MORE_REACH.getId())
                .addElement(Powers.EYE_ENHANCEMENTS.getId())
                .addElement(Powers.TRAMPOLINE.getId())
                .addElement(Powers.QUICK_LEARNER.getId());

        getOrCreateRawBuilder(PowerTokenTags.NEGATIVE_POWER)
                .addElement(Powers.LESS_HEALTH.getId())
                .addElement(Powers.FLAMMABLE.getId())
                .addElement(Powers.MORE_KNOCKBACK.getId())
                .addElement(Powers.HEAVY_HANDS.getId())
                .addElement(Powers.WEAKENED_DEFENSES.getId())
                .addElement(Powers.LESS_DAMAGE.getId())
                .addElement(Powers.BIG.getId())
                .addElement(Powers.LESS_SPEED.getId())
                .addElement(Powers.METAL_BONES.getId())
                .addElement(Powers.AQUAPHOBIC.getId())
                .addElement(Powers.VAMPIRE.getId());
    }
}
