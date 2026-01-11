package me.flipstargamer.powertokens.keybinds;

import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.gui.PowerListScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = PowerTokens.MOD_ID)
public class OpenPowerListEvent {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (ModKeybinds.OPEN_POWER_LIST.consumeClick()) {
            Minecraft.getInstance().setScreen(new PowerListScreen());
        }
    }
}
