package me.flipstargamer.kinetica.powers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;

import java.util.Optional;
import java.util.function.Predicate;

public record PowerPredicate(Optional<Holder<Power>> power) implements Predicate<Power> {
    public static final Codec<PowerPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Power.CODEC.optionalFieldOf("power").forGetter(PowerPredicate::power)
    ).apply(instance, PowerPredicate::new));

    @Override
    public boolean test(Power toTest) {
        return power.map(powerHolder -> powerHolder.value() == toTest).orElse(true);
    }
}
