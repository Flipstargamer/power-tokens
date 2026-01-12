package me.flipstargamer.powertokens.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.flipstargamer.powertokens.powers.PowerPredicate;
import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PowerObtainedTrigger extends SimpleCriterionTrigger<PowerObtainedTrigger.@NotNull Instance> {
    @Override
    public @NotNull Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player, Power power) {
        this.trigger(player, triggerInstance -> triggerInstance.matches(power));
    }

    public static Criterion<@NotNull Instance> createCriterion(PowerPredicate predicate) {
        return ModTriggerTypes.POWER_OBTAINED_TRIGGER.value().createCriterion(new Instance(Optional.empty(), predicate));
    }

    public record Instance(Optional<ContextAwarePredicate> player, PowerPredicate predicate) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player),
                PowerPredicate.CODEC.fieldOf("power").forGetter(Instance::predicate)
        ).apply(instance, Instance::new));

        public boolean matches(Power power) {
            return predicate.test(power);
        }
    }
}
