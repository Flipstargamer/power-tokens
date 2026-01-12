package me.flipstargamer.powertokens.advancements;

import com.mojang.serialization.Codec;
import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class PowerObtainedTrigger extends SimpleCriterionTrigger<@NotNull PowerObtainedTriggerInstance> {
    @Override
    public @NotNull Codec<PowerObtainedTriggerInstance> codec() {
        return PowerObtainedTriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, Power power) {
        this.trigger(player, triggerInstance -> triggerInstance.matches(power));
    }
}
