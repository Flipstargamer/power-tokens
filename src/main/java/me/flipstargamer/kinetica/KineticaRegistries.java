package me.flipstargamer.kinetica;

import me.flipstargamer.kinetica.powers.Power;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = Kinetica.MOD_ID)
public class KineticaRegistries {
    public static final ResourceKey<Registry<Power>> POWER_REGISTRY_KEY =
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Kinetica.MOD_ID, "powers"));

    public static final Registry<Power> POWER_REGISTRY = new RegistryBuilder<>(POWER_REGISTRY_KEY).sync(true).create();

    @SubscribeEvent
    public static void register(NewRegistryEvent event) {
        event.register(POWER_REGISTRY);
    }
}
