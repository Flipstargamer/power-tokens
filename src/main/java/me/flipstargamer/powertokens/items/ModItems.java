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
            () -> new Item.Properties().useCooldown(1).stacksTo(16));

    public static final DeferredItem<@NotNull PowerTokenItem> UNSTABLE_POWER_TOKEN = ITEMS.registerItem("unstable_token",
            properties -> new PowerTokenItem(properties, List.of(PowerTokenTags.POSITIVE_POWER, PowerTokenTags.NEGATIVE_POWER, PowerTokenTags.NEGATIVE_POWER)),
            () -> new Item.Properties().useCooldown(1).stacksTo(16));

    public static final DeferredItem<@NotNull PowerTokenItem> REINFORCED_TOKEN = ITEMS.registerItem("reinforced_token",
            properties -> new PowerTokenItem(properties, List.of(PowerTokenTags.POSITIVE_POWER)),
            () -> new Item.Properties().useCooldown(1).stacksTo(16).fireResistant());

    public static final DeferredItem<@NotNull PowerRemoverItem> INVERTED_TOKEN = ITEMS.registerItem("inverted_token",
            PowerRemoverItem::new, () -> new Item.Properties().useCooldown(1).stacksTo(16));

    public static final DeferredItem<@NotNull PowerSelectorItem> SELECTIVE_TOKEN = ITEMS.registerItem("selective_token",
            PowerSelectorItem::new, () -> new Item.Properties().stacksTo(16));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
