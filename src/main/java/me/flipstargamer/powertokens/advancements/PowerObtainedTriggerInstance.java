package me.flipstargamer.powertokens.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.flipstargamer.powertokens.powers.Power;
import me.flipstargamer.powertokens.powers.PowerPredicate;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record PowerObtainedTriggerInstance(Optional<ContextAwarePredicate> player, PowerPredicate predicate) implements SimpleCriterionTrigger.SimpleInstance {
    public static final Codec<PowerObtainedTriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(PowerObtainedTriggerInstance::player),
            PowerPredicate.CODEC.fieldOf("power").forGetter(PowerObtainedTriggerInstance::predicate)
    ).apply(instance, PowerObtainedTriggerInstance::new));

    public static Criterion<@NotNull PowerObtainedTriggerInstance> create(PowerPredicate predicate) {
        return ModTriggerTypes.POWER_OBTAINED_TRIGGER.value().createCriterion(new PowerObtainedTriggerInstance(Optional.empty(), predicate));
    }

    public boolean matches(Power power) {
        return predicate.test(power);
    }
}
