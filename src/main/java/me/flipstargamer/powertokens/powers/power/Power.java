package me.flipstargamer.powertokens.powers.power;

import com.mojang.serialization.Codec;
import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.powers.PowerComparable;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;

public class Power implements PowerComparable {
    public static final Codec<Holder<Power>> CODEC = PowerTokenRegistries.POWER_REGISTRY.holderByNameCodec();

    public MutableComponent getTranslation() {
        Identifier identifier = PowerTokenRegistries.POWER_REGISTRY.getKey(this);

        assert identifier != null;
        return Component.translatable("powers." + identifier.getNamespace() + "." + identifier.getPath());
    }

    public MutableComponent getDescription() {
        Identifier identifier = PowerTokenRegistries.POWER_REGISTRY.getKey(this);

        assert identifier != null;
        return Component.translatable("powers." + identifier.getNamespace() + "." + identifier.getPath() + ".description");
    }

    public boolean is(Power power) {
        return this == power;
    }

    public boolean is(Holder<Power> power) {
        return is(power.value());
    }

    public boolean is(TagKey<Power> powerTagKey) {
        return PowerTokenRegistries.POWER_REGISTRY.getOrThrow(powerTagKey).stream().anyMatch(this::is);
    }
}
