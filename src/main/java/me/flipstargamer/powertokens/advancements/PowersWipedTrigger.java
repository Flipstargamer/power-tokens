package me.flipstargamer.powertokens.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PowersWipedTrigger extends SimpleCriterionTrigger<PowersWipedTrigger.@NotNull Instance> {

    @Override
    public @NotNull Codec<@NotNull Instance> codec() {
        return Instance.CODEC;
    }

    public static Criterion<@NotNull Instance> createCriterion() {
        return ModTriggerTypes.POWERS_WIPED_TRIGGER.get().createCriterion(new Instance(Optional.empty()));
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, _ -> true);
    }

    public record Instance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player)
        ).apply(instance, Instance::new));
    }
}
