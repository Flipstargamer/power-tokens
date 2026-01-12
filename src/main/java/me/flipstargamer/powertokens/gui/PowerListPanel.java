package me.flipstargamer.powertokens.gui;

import me.flipstargamer.powertokens.PowerTokenTags;
import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.neoforge.client.gui.widget.ScrollPanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PowerListPanel extends ScrollPanel {
    public List<Holder<Power>> powers = new ArrayList<>();

    private static final int ENTRY_HEIGHT = 14;
    public @Nullable Holder<Power> hoveredEntry;

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
            guiGraphics.drawString(Minecraft.getInstance().font, getTextForEmpty(), this.left + 5, y, 0xFFFFFFFF);
            return;
        }

        int entryY = y;

        hoveredEntry = null;
        for (Holder<Power> powerHolder : powers) {
            drawEntry(guiGraphics, entryY, mouseX, mouseY, powerHolder);

            entryY += ENTRY_HEIGHT;
        }
    }

    protected void drawEntry(GuiGraphics guiGraphics, int entryY, int mouseX, int mouseY, Holder<Power> powerHolder) {
        if (entryY + ENTRY_HEIGHT < this.top || entryY > this.bottom)
            return;

        int entryTopY = entryY - 2;
        int entryBottomY = entryY + ENTRY_HEIGHT - 2;

        boolean isHovered = mouseX >= this.left && mouseX <= right && mouseY >= entryTopY && mouseY < entryBottomY;

        if (mouseY <= top || mouseY >= bottom) {
            isHovered = false;
        }

        guiGraphics.drawString(Minecraft.getInstance().font,
                getTextForEntry(powerHolder, isHovered), this.left + 5, entryY,
                getColorForEntry(powerHolder));

        if (isHovered) {
            guiGraphics.setComponentTooltipForNextFrame(Minecraft.getInstance().font,
                    List.of(powerHolder.value().getDescription()), mouseX, mouseY);

            hoveredEntry = powerHolder;
        }
    }

    protected MutableComponent getTextForEntry(Holder<Power> powerHolder, boolean isHovered) {
        MutableComponent text = powerHolder.value().getTranslation();

        if (isHovered) text.withStyle(ChatFormatting.UNDERLINE);

        return text;
    }

    protected MutableComponent getTextForEmpty() {
        return Component.translatable("menu.power_list.no_powers");
    }

    protected int getColorForEntry(Holder<Power> powerHolder) {
        boolean isPositive = powerHolder.value().is(PowerTokenTags.POSITIVE_POWER);
        boolean isNegative = powerHolder.value().is(PowerTokenTags.NEGATIVE_POWER);

        if (isPositive) return 0xff80efc2;
        if (isNegative) return 0xffd95763;

        return 0xFFFFFFFF;
    }

    @Override
    public @NotNull NarrationPriority narrationPriority() {
        return NarrationPriority.NONE;
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput narrationElementOutput) {
    }
}
