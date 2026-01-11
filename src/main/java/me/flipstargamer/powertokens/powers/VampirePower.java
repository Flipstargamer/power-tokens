package me.flipstargamer.powertokens.powers;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class VampirePower extends Power {
    @Override
    public void apply(LivingEntity entity) {}

    @Override
    public void remove(LivingEntity entity) {}

    @Override
    public void tick(LivingEntity entity) {
        if (entity.level().isBrightOutside() && entity.level().canSeeSky(entity.blockPosition())) {
            if (entity.hasEffect(MobEffects.FIRE_RESISTANCE)) return;

            entity.igniteForTicks(Math.clamp(entity.getRemainingFireTicks() + 3, 20, 100));
            entity.hurtServer((ServerLevel) entity.level(), new DamageSource(
                    entity.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.IN_FIRE)
            ), 1f);
        }
    }

    @Override
    public boolean shouldTick() {
        return true;
    }
}
