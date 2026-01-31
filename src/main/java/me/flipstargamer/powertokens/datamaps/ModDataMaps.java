package me.flipstargamer.powertokens.datamaps;

import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

import java.util.List;

@EventBusSubscriber(modid = PowerTokens.MOD_ID)
public class ModDataMaps {
    public static final DataMapType<Power, List<Holder<Power>>> MUTUAL_POWERS = DataMapType.builder(
            Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, "mutual_powers"),
            PowerTokenRegistries.POWER_REGISTRY_KEY,
            Power.CODEC.listOf()
    ).synced(Power.CODEC.listOf(), false).build();

    @SubscribeEvent
    public static void register(RegisterDataMapTypesEvent event) {
        event.register(MUTUAL_POWERS);
    }
}
