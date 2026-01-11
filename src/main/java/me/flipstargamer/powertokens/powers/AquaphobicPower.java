package me.flipstargamer.powertokens.powers;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class AquaphobicPower extends Power {

    @Override
    public void apply(LivingEntity entity) {
    }

    @Override
    public void remove(LivingEntity entity) {
    }

    @Override
    public void tick(LivingEntity entity) {
        if (entity.isInWaterOrRain()) {
            entity.hurtServer((ServerLevel) entity.level(), new DamageSource(
                    entity.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.DROWN)
            ), 1);
        }
    }

    @Override
    public boolean shouldTick() {
        return true;
    }
}
