package me.flipstargamer.powertokens.datagen;

import me.flipstargamer.powertokens.datamaps.ModDataMaps;
import me.flipstargamer.powertokens.powers.Powers;
import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    private final HashMap<Holder<Power>, ArrayList<Holder<Power>>> MUTUAL_MAP = new HashMap<>();

    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        createMutual(Powers.VEGETARIAN, Powers.CARNIVORE);
        createMutual(Powers.BIG, Powers.TINY_POWER);

        Builder<List<Holder<Power>>, Power> mutualBuilder = builder(ModDataMaps.MUTUAL_POWERS);
        MUTUAL_MAP.forEach((key, mutual) -> mutualBuilder.add(key, mutual, false));
    }

    private ArrayList<Holder<Power>> getMutualListOrCreateNew(Holder<Power> power) {
        @Nullable ArrayList<Holder<Power>> list = MUTUAL_MAP.computeIfAbsent(power, k -> new ArrayList<>());
        return list;
    }

    protected void createMutual(Holder<Power> a, Holder<Power> b) {
        getMutualListOrCreateNew(a).add(b);
        getMutualListOrCreateNew(b).add(a);
    }
}
