package me.flipstargamer.powertokens.networking.power_selector;

import io.netty.buffer.ByteBuf;
import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.PowerTokens;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PowerPickerClosePayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<@NotNull PowerPickerClosePayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, "power_picker_close"));

    public static final StreamCodec<ByteBuf, PowerPickerClosePayload> STREAM_CODEC = StreamCodec.unit(new PowerPickerClosePayload());

    @Override
    public @NotNull Type<? extends @NotNull CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleServer(PowerPickerClosePayload payload, IPayloadContext context) {
        PowerTokens.LOGGER.debug("Server received close message!");
        context.player().setData(ModDataAttachments.CAN_PICK_POWER, false);
    }
}
