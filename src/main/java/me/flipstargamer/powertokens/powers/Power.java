package me.flipstargamer.powertokens.powers;

import com.mojang.serialization.Codec;
import me.flipstargamer.powertokens.PowerTokenRegistries;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;

public abstract class Power implements PowerComparable {
    public static final Codec<Holder<Power>> CODEC = PowerTokenRegistries.POWER_REGISTRY.holderByNameCodec();

    public abstract void apply(LivingEntity entity);
    public abstract void remove(LivingEntity entity);

    public boolean shouldReapplyOnJoin() {
        return false;
    }

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

    public void tick(LivingEntity entity) {}
    public boolean shouldTick() { return false; }

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
