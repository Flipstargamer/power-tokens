package me.flipstargamer.powertokens.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class PowerPickerHelper {
    public static void open(ItemStack itemStack) {
        Minecraft.getInstance().setScreen(new PowerPickerScreen(itemStack));
    }
}
