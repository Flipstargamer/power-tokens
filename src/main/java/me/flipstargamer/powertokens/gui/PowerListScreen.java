package me.flipstargamer.powertokens.gui;

import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.PowerTokenTags;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.powers.Power;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PowerListScreen extends Screen {
    private static final int IMAGE_WIDTH = 256;
    private static final int IMAGE_HEIGHT = 256;

    private static final Identifier BACKGROUND_TEXTURE = Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, "textures/gui/powers.png");

    private int uiTop;
    private int uiLeft;

    public PowerListScreen() {
        super(Component.translatable("menu.power_list.title"));
    }

    private int getPowerWeight(Holder<Power> powerHolder) {
        if (powerHolder.is(PowerTokenTags.NEGATIVE_POWER)) return 0;
        if (powerHolder.is(PowerTokenTags.POSITIVE_POWER)) return 2;
        return 1;
    }

    @Override
    protected void init() {
        uiLeft = (width - IMAGE_WIDTH) / 2;
        uiTop = (height - IMAGE_HEIGHT) / 2;

        PowerListPanel listPanel = new PowerListPanel(minecraft, IMAGE_WIDTH - 15, IMAGE_HEIGHT - 28, uiTop + 20, uiLeft + 7);

        assert minecraft.player != null;
        ArrayList<Holder<Power>> powerList = new ArrayList<>(minecraft.player.getData(ModDataAttachments.PLAYER_POWERS));

        powerList.sort((a, b) -> {
            if (getPowerWeight(a) > getPowerWeight(b)) return -1;
            if (getPowerWeight(b) > getPowerWeight(a)) return 1;

            return a.value().getTranslation().getString().compareToIgnoreCase(b.value().getTranslation().getString());
        });

        listPanel.powers = powerList;

        addRenderableWidget(listPanel);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, uiLeft, uiTop, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT);

        guiGraphics.drawString(font, title, uiLeft + 7, uiTop + 7, 0xFFFFFFFF);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderTransparentBackground(guiGraphics);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean isInGameUi() {
        return true;
    }
}
