package me.flipstargamer.powertokens.gui;

import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.Nullable;

public class PowerPickerListPanel extends PowerListPanel {
    public @Nullable Holder<Power> selectedPower = null;

    public PowerPickerListPanel(Minecraft client, int width, int height, int top, int left) {
        super(client, width, height, top, left);
    }

    @Override
    protected MutableComponent getTextForEntry(Holder<Power> powerHolder, boolean isHovered) {
        MutableComponent text = super.getTextForEntry(powerHolder, isHovered);

        if (selectedPower == powerHolder) {
            text.withStyle(ChatFormatting.BOLD);
        }

        return text;
    }

    @Override
    protected MutableComponent getTextForEmpty() {
        return Component.translatable("menu.power_picker.no_pickable_powers");
    }

    @Override
    protected boolean clickPanel(double mouseX, double mouseY, MouseButtonEvent event) {
        if (hoveredEntry != null) {
            selectedPower = hoveredEntry;

            assert Minecraft.getInstance().level != null;
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }

        return super.clickPanel(mouseX, mouseY, event);
    }
}
