package me.flipstargamer.powertokens.powers.power;

import me.flipstargamer.powertokens.powers.PowerTickable;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class NightVisionPower extends Power implements PowerTickable {
    @Override
    public void tick(LivingEntity entity) {
        if (entity.hasEffect(MobEffects.NIGHT_VISION)) {
            MobEffectInstance effectInstance = entity.getEffect(MobEffects.NIGHT_VISION);

            assert effectInstance != null;
            if (effectInstance.getDuration() >= 210) return;
        }
        entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, false));
    }
}
