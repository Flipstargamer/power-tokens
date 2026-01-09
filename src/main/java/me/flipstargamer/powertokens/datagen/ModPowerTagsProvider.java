package me.flipstargamer.powertokens.datagen;

import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.PowerTokenTags;
import me.flipstargamer.powertokens.powers.Power;
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
                .addElement(Powers.SPEED_POWER.getId())
                .addElement(Powers.TINY_POWER.getId());
    }
}
