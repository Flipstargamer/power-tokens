package me.flipstargamer.powertokens;

import me.flipstargamer.powertokens.powers.Power;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = PowerTokens.MOD_ID)
public class PowerTokenRegistries {
    public static final ResourceKey<Registry<Power>> POWER_REGISTRY_KEY =
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, "powers"));

    public static final Registry<Power> POWER_REGISTRY = new RegistryBuilder<>(POWER_REGISTRY_KEY).sync(true).create();

    @SubscribeEvent
    public static void register(NewRegistryEvent event) {
        event.register(POWER_REGISTRY);
    }
}
