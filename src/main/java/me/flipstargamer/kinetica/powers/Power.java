package me.flipstargamer.kinetica.powers;

import com.mojang.serialization.Codec;
import me.flipstargamer.kinetica.KineticaRegistries;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;

public abstract class Power {
    public static final Codec<Holder<Power>> CODEC = KineticaRegistries.POWER_REGISTRY.holderByNameCodec();

    public abstract void apply(LivingEntity entity);
    public abstract void remove(LivingEntity entity);

    public boolean shouldReapplyOnJoin() {
        return false;
    }
}
