package me.flipstargamer.kinetica.powers;

import com.mojang.serialization.Codec;
import me.flipstargamer.kinetica.KineticaRegistries;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;

public abstract class Power {
    public static final Codec<Holder<Power>> CODEC = KineticaRegistries.POWER_REGISTRY.holderByNameCodec();

    public abstract void apply(LivingEntity entity);
    public abstract void remove(LivingEntity entity);

    public boolean shouldReapplyOnJoin() {
        return false;
    }

    public Component getTranslation() {
        Identifier identifier = KineticaRegistries.POWER_REGISTRY.getKey(this);

        assert identifier != null;
        return Component.translatable("powers." + identifier.getNamespace() + "." + identifier.getPath());
    }

    public boolean is(Power power) {
        return this == power;
    }

    public boolean is(Holder<Power> power) {
        return is(power.value());
    }

    public boolean is(TagKey<Power> powerTagKey) {
        return KineticaRegistries.POWER_REGISTRY.getOrThrow(powerTagKey).stream().anyMatch(this::is);
    }
}
