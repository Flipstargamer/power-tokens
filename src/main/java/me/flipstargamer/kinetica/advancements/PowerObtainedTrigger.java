package me.flipstargamer.kinetica.advancements;

import com.mojang.serialization.Codec;
import me.flipstargamer.kinetica.powers.Power;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PowerObtainedTrigger extends SimpleCriterionTrigger<@NotNull PowerObtainedTriggerInstance> {
    @Override
    public Codec<PowerObtainedTriggerInstance> codec() {
        return PowerObtainedTriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, Power power) {
        this.trigger(player, triggerInstance -> triggerInstance.matches(power));
    }
}
