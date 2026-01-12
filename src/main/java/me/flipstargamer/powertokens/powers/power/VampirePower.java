package me.flipstargamer.powertokens.powers.power;

import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.powers.Powers;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class VampirePower extends Power {
    @Override
    public void apply(LivingEntity entity) {}

    @Override
    public void remove(LivingEntity entity) {}

    @Override
    public void tick(LivingEntity entity) {
        if (entity.level().isBrightOutside() && entity.level().canSeeSky(entity.blockPosition())) {
            List<Holder<Power>> powers = entity.getData(ModDataAttachments.PLAYER_POWERS);

            if (entity.hasEffect(MobEffects.FIRE_RESISTANCE)) return;
            if (powers.contains(Powers.BURN_RESISTANCE) || powers.contains(Powers.FLAMMABLE)) return;

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
