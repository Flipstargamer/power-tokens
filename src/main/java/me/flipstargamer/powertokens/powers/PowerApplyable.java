package me.flipstargamer.powertokens.powers;

import net.minecraft.world.entity.LivingEntity;

public interface PowerApplyable {
    void apply(LivingEntity entity);
    boolean shouldReapplyOnJoin();
}
