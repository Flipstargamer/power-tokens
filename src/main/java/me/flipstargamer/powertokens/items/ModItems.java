package me.flipstargamer.powertokens.items;

import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.PowerTokenTags;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PowerTokens.MOD_ID);

    public static final DeferredItem<@NotNull PowerTokenItem> POWER_TOKEN = ITEMS.registerItem("power_token",
            properties -> new PowerTokenItem(properties, List.of(PowerTokenTags.POSITIVE_POWER, PowerTokenTags.NEGATIVE_POWER)),
            () -> new Item.Properties().useCooldown(1).stacksTo(1));

    public static final DeferredItem<@NotNull Item> POWER_TOKEN_SHARD = ITEMS.registerItem("power_token_shard", Item::new,
            Item.Properties::new);

    public static final DeferredItem<@NotNull Item> POWER_TOKEN_CORE = ITEMS.registerItem("power_token_core", Item::new,
            Item.Properties::new);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
