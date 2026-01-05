package me.flipstargamer.kinetica.commands;

import me.flipstargamer.kinetica.Kinetica;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = Kinetica.MOD_ID)
public class CommandRegistration {
    @SubscribeEvent
    public static void commandsRegister(RegisterCommandsEvent event) {
        PowerCommand.register(event.getDispatcher(), event.getBuildContext());
    }
}
