package me.flipstargamer.powertokens.gui;

import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.networking.power_selector.PowerPickerClosePayload;
import me.flipstargamer.powertokens.networking.power_selector.PowerPickerSelectPayload;
import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PowerPickerScreen extends Screen {
    private final ItemStack itemStack;

    private static final int IMAGE_WIDTH = 256;
    private static final int IMAGE_HEIGHT = 256;

    private static final Identifier BACKGROUND_TEXTURE = Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, "textures/gui/powers.png");

    private int uiTop;
    private int uiLeft;

    private PowerPickerListPanel listPanel;

    public PowerPickerScreen(ItemStack itemStack) {
        super(Component.translatable("menu.power_picker.title"));
        this.itemStack = itemStack;
    }

    @Override
    protected void init() {
        super.init();

        uiLeft = (width - IMAGE_WIDTH) / 2;
        uiTop = (height - IMAGE_HEIGHT) / 2;

        addRenderableWidget(Button.builder(
                Component.translatable("menu.power_picker.confirm"),
                this::onDonePressed
        ).bounds(uiLeft + 7, uiTop + IMAGE_HEIGHT - 27, IMAGE_HEIGHT - 14, 20).build());

        assert minecraft.player != null;
        ArrayList<Holder<Power>> ownedPowers = new ArrayList<>(minecraft.player.getData(ModDataAttachments.PLAYER_POWERS));

        listPanel = new PowerPickerListPanel(minecraft,IMAGE_WIDTH - 15, IMAGE_HEIGHT - 50, uiTop + 20, uiLeft + 7);
        listPanel.powers = PowerTokenRegistries.POWER_REGISTRY.stream()
                .map(PowerTokenRegistries.POWER_REGISTRY::wrapAsHolder)
                .filter(a -> !ownedPowers.contains(a))
                .toList();

        addRenderableWidget(listPanel);
    }

    private void onDonePressed(Button button) {
        if (listPanel.selectedPower != null) {
            itemStack.shrink(1);

            assert minecraft.player != null;
            minecraft.player.getCooldowns().addCooldown(itemStack, 20);

            ClientPacketDistributor.sendToServer(new PowerPickerSelectPayload(listPanel.selectedPower));
        }

        onClose();
        minecraft.setScreen(null);
    }

    @Override
    public void onClose() {
        super.onClose();
        ClientPacketDistributor.sendToServer(new PowerPickerClosePayload());
    }

    @Override
    public void tick() {
        if (itemStack == null || itemStack.isEmpty()) {
            onClose();
            minecraft.setScreen(null);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, uiLeft, uiTop, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT);
        guiGraphics.drawString(font, title, uiLeft + 7, uiTop + 7, 0xFFFFFFFF);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderTransparentBackground(guiGraphics);
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
