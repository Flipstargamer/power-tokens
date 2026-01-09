package me.flipstargamer.powertokens.commands;

import me.flipstargamer.powertokens.PowerTokens;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = PowerTokens.MOD_ID)
public class CommandRegistration {
    @SubscribeEvent
    public static void commandsRegister(RegisterCommandsEvent event) {
        PowerCommand.register(event.getDispatcher(), event.getBuildContext());
    }
}
