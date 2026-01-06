package me.flipstargamer.kinetica.powers;

import me.flipstargamer.kinetica.Kinetica;
import me.flipstargamer.kinetica.ModDataAttachments;
import me.flipstargamer.kinetica.KineticaRegistries;
import me.flipstargamer.kinetica.advancements.ModTriggerTypes;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@EventBusSubscriber(modid = Kinetica.MOD_ID)
public class Powers {
    public static final DeferredRegister<Power> POWERS = DeferredRegister.create(KineticaRegistries.POWER_REGISTRY, Kinetica.MOD_ID);

    public static final DeferredHolder<Power, @NotNull AttributePower> HEALTH_POWER = POWERS.register("more_health",
            () -> new AttributePower(Attributes.MAX_HEALTH, 10, AttributeModifier.Operation.ADD_VALUE,
                    Identifier.parse("kinetica:more_health_power")));

    public static final DeferredHolder<Power, @NotNull AttributePower> SPEED_POWER = POWERS.register("more_speed",
            () -> new AttributePower(Attributes.MOVEMENT_SPEED, 0.6f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL,
                    Identifier.parse("kinetica:more_speed_power")));

    public static final DeferredHolder<Power, @NotNull AttributePower> TINY_POWER = POWERS.register("tiny",
            () -> new AttributePower(Attributes.SCALE, -0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL,
                    Identifier.parse("kinetica:tiny_power")));

    public static final DeferredHolder<Power, @NotNull AttributePower> NATURAL_ARMOR = POWERS.register("natural_armor",
            () -> new AttributePower(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE,
                    Identifier.parse("kinetica:armor_power")));

    public static void register(IEventBus bus) {
        POWERS.register(bus);
    }

    @Deprecated(since = "1.0.0", forRemoval = true)
    public static void addPower(LivingEntity entity, Holder<Power> power) {
        List<Holder<Power>> powers = entity.getData(ModDataAttachments.PLAYER_POWERS);

        if (powers.contains(power))
            return;

        powers.add(power);

        Power truePower = power.value();
        truePower.apply(entity);

        if (entity instanceof ServerPlayer player) {
            ModTriggerTypes.POWER_OBTAINED_TRIGGER.get().trigger(player, power.value());
        }
    }

    @Deprecated(since = "1.0.0", forRemoval = true)
    public static void addPower(LivingEntity entity, Power power) {
        addPower(entity, KineticaRegistries.POWER_REGISTRY.wrapAsHolder(power));
    }

    @Deprecated(since = "1.0.0", forRemoval = true)
    public static void removePower(LivingEntity entity, Holder<Power> power) {
        List<Holder<Power>> powers = entity.getData(ModDataAttachments.PLAYER_POWERS);

        if (!powers.contains(power))
            return;

        powers.remove(power);

        Power truePower = power.value();
        truePower.remove(entity);
    }

    @Deprecated(since = "1.0.0", forRemoval = true)
    public static void removePower(LivingEntity entity, Power power) {
        removePower(entity, KineticaRegistries.POWER_REGISTRY.wrapAsHolder(power));
    }

    @Deprecated(since = "1.0.0", forRemoval = true)
    public static Component getPowerTranslation(Holder<Power> power) {
        Identifier key = Objects.requireNonNull(power.getKey()).identifier();
        return Component.translatable("powers." + key.getNamespace() + "." + key.getPath());
    }
}
