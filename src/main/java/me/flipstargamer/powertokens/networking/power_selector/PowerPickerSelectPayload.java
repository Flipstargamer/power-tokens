package me.flipstargamer.powertokens.networking.power_selector;

import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.items.ModItems;
import me.flipstargamer.powertokens.powers.PowerManager;
import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PowerPickerSelectPayload(Holder<Power> powerHolder) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<@NotNull PowerPickerSelectPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, "power_picker_select"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PowerPickerSelectPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.holderRegistry(PowerTokenRegistries.POWER_REGISTRY_KEY),
                    PowerPickerSelectPayload::powerHolder,
                    PowerPickerSelectPayload::new
            );

    @Override
    public @NotNull Type<? extends @NotNull CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleServer(PowerPickerSelectPayload payload, IPayloadContext context) {
        ItemStack itemStack = context.player().getMainHandItem();

        if (itemStack.is(ModItems.SELECTIVE_TOKEN)) {
            itemStack.shrink(1);
            PowerManager.addPower(context.player(), payload.powerHolder);
            context.player().setData(ModDataAttachments.CAN_PICK_POWER, false);
            context.player().getCooldowns().addCooldown(itemStack, 20);
        }
    }
}
