package me.flipstargamer.powertokens.powers.power;

import me.flipstargamer.powertokens.powers.PowerTickable;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class AquaphobicPower extends Power implements PowerTickable {
    @Override
    public void tick(LivingEntity entity) {
        if (entity.isInWaterOrRain()) {
            entity.hurtServer((ServerLevel) entity.level(), new DamageSource(
                    entity.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.DROWN)
            ), 1);
        }
    }
}
