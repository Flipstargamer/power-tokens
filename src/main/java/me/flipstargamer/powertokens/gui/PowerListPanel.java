package me.flipstargamer.powertokens.gui;

import me.flipstargamer.powertokens.PowerTokenTags;
import me.flipstargamer.powertokens.powers.Power;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.neoforge.client.gui.widget.ScrollPanel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PowerListPanel extends ScrollPanel {
    public List<Holder<Power>> powers = new ArrayList<>();

    private static final int ENTRY_HEIGHT = 14;

    public PowerListPanel(Minecraft client, int width, int height, int top, int left) {
        super(client, width, height, top, left);
    }

    @Override
    protected int getContentHeight() {
        return ENTRY_HEIGHT * powers.size();
    }



    @Override
    protected void drawPanel(@NotNull GuiGraphics guiGraphics, int right, int y, int mouseX, int mouseY) {
        if (powers.isEmpty()) {
            guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("menu.power_list.no_powers"), this.left + 5, y, 0xFFFFFFFF);
            return;
        }

        int entryY = y;

        for (Holder<Power> powerHolder : powers) {
            if (entryY + ENTRY_HEIGHT < this.top || entryY > this.bottom) {
                entryY += ENTRY_HEIGHT;
                continue;
            }

            int entryTopY = entryY - 2;
            int entryBottomY = entryY + ENTRY_HEIGHT - 2;

            boolean isHovered = mouseX >= this.left && mouseX <= right && mouseY >= entryTopY && mouseY < entryBottomY;

            if (mouseY <= top || mouseY >= bottom) {
                isHovered = false;
            }

            boolean isPositive = powerHolder.value().is(PowerTokenTags.POSITIVE_POWER);
            boolean isNegative = powerHolder.value().is(PowerTokenTags.NEGATIVE_POWER);

            MutableComponent text = powerHolder.value().getTranslation();
            int color = 0xFFFFFFFF;

            if (isPositive) color = 0xff80efc2;
            if (isNegative) color = 0xffd95763;

            if (isHovered) text.withStyle(ChatFormatting.UNDERLINE);

            guiGraphics.drawString(Minecraft.getInstance().font, text, this.left + 5, entryY, color);

            if (isHovered) {
                guiGraphics.setComponentTooltipForNextFrame(Minecraft.getInstance().font,
                        List.of(powerHolder.value().getDescription()), mouseX, mouseY);
            }

            entryY += ENTRY_HEIGHT;
        }
    }

    @Override
    public @NotNull NarrationPriority narrationPriority() {
        return NarrationPriority.NONE;
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput narrationElementOutput) {
    }
}
