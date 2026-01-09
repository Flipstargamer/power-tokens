package me.flipstargamer.powertokens.items;

import me.flipstargamer.powertokens.PowerTokens;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, PowerTokens.MOD_ID);

    public static final Supplier<CreativeModeTab> MAIN_TAB =
            CREATIVE_MODE_TABS.register("kinetica", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.kinetica.main"))
                    .icon(() -> new ItemStack(ModItems.POWER_TOKEN.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.POWER_TOKEN.get());
                        output.accept(ModItems.POWER_TOKEN_CORE.get());
                        output.accept(ModItems.POWER_TOKEN_SHARD.get());
                    })
                    .build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
