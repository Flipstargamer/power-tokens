package me.flipstargamer.kinetica.datagen;

import me.flipstargamer.kinetica.Kinetica;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Kinetica.MOD_ID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput output = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new ModModelProvider(output));
        event.createProvider(ModRecipeProvider.Runner::new);
        event.createProvider(ModPowerTagsProvider::new);

        event.addProvider(new AdvancementProvider(
                output, lookupProvider,
                List.of(
                        new ModAdvancementProvider()
                )
        ));
    }
}
