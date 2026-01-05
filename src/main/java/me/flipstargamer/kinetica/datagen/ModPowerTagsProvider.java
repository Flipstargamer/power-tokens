package me.flipstargamer.kinetica.datagen;

import me.flipstargamer.kinetica.Kinetica;
import me.flipstargamer.kinetica.KineticaRegistries;
import me.flipstargamer.kinetica.KineticaTags;
import me.flipstargamer.kinetica.powers.Power;
import me.flipstargamer.kinetica.powers.Powers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModPowerTagsProvider extends TagsProvider<Power> {

    protected ModPowerTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, KineticaRegistries.POWER_REGISTRY_KEY, lookupProvider, Kinetica.MOD_ID);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        getOrCreateRawBuilder(KineticaTags.POSITIVE_POWER)
                .addElement(Powers.HEALTH_POWER.getId())
                .addElement(Powers.NATURAL_ARMOR.getId())
                .addElement(Powers.SPEED_POWER.getId())
                .addElement(Powers.TINY_POWER.getId());
    }
}
