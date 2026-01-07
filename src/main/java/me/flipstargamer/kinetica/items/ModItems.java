package me.flipstargamer.kinetica.items;

import me.flipstargamer.kinetica.Kinetica;
import me.flipstargamer.kinetica.KineticaTags;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Kinetica.MOD_ID);

    public static final DeferredItem<@NotNull PowerTokenItem> POWER_TOKEN = ITEMS.registerItem("power_token",
            properties -> new PowerTokenItem(properties, List.of(KineticaTags.POSITIVE_POWER, KineticaTags.NEGATIVE_POWER)),
            () -> new Item.Properties().useCooldown(1).stacksTo(1));

    public static final DeferredItem<@NotNull Item> POWER_TOKEN_SHARD = ITEMS.registerItem("power_token_shard", Item::new,
            Item.Properties::new);

    public static final DeferredItem<@NotNull Item> POWER_TOKEN_CORE = ITEMS.registerItem("power_token_core", Item::new,
            Item.Properties::new);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
