package me.flipstargamer.powertokens;

import me.flipstargamer.powertokens.powers.Power;
import net.minecraft.core.Holder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, PowerTokens.MOD_ID);

    public static final Supplier<AttachmentType<ArrayList<Holder<Power>>>> PLAYER_POWERS = ATTACHMENT_TYPES.register("player_powers",
            () -> AttachmentType.builder(() -> new ArrayList<Holder<Power>>())
                    .serialize(Power.CODEC.listOf().xmap(ArrayList::new, List::copyOf).fieldOf("powers"))
                    .sync((holder, player) -> holder == player, ByteBufCodecs.collection(ArrayList::new, ByteBufCodecs.holderRegistry(PowerTokenRegistries.POWER_REGISTRY_KEY)))
                    .build());

    public static void register(IEventBus bus) {
        ATTACHMENT_TYPES.register(bus);
    }
}
