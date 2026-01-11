package me.flipstargamer.powertokens.keybinds;

import me.flipstargamer.powertokens.PowerTokens;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = PowerTokens.MOD_ID)
public class ModKeybinds {
    public static final KeyMapping OPEN_POWER_LIST = new KeyMapping(
            "key.power_tokens.open_powers",
            GLFW.GLFW_KEY_V,
            KeyMapping.Category.INVENTORY
    );

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(OPEN_POWER_LIST);
    }
}
