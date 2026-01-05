package me.flipstargamer.kinetica.powers;

import me.flipstargamer.kinetica.Kinetica;
import me.flipstargamer.kinetica.ModDataAttachments;
import me.flipstargamer.kinetica.KineticaRegistries;
import me.flipstargamer.kinetica.advancements.ModTriggerTypes;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

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

    public static void addPower(LivingEntity entity, Power power) {
        addPower(entity, KineticaRegistries.POWER_REGISTRY.wrapAsHolder(power));
    }

    public static void removePower(LivingEntity entity, Holder<Power> power) {
        List<Holder<Power>> powers = entity.getData(ModDataAttachments.PLAYER_POWERS);

        if (!powers.contains(power))
            return;

        powers.remove(power);

        Power truePower = power.value();
        truePower.remove(entity);
    }

    public static void removePower(LivingEntity entity, Power power) {
        removePower(entity, KineticaRegistries.POWER_REGISTRY.wrapAsHolder(power));
    }

    @SubscribeEvent
    private static void clonePowers(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        Kinetica.LOGGER.debug("Test test!");

        Player originalPlayer = event.getOriginal();
        if (!originalPlayer.hasData(ModDataAttachments.PLAYER_POWERS)) return;

        Player newPlayer = event.getEntity();
        List<Holder<Power>> powers = originalPlayer.getData(ModDataAttachments.PLAYER_POWERS);

        for (Holder<Power> power : powers) {
            addPower(newPlayer, power);
        }
    }

    @SubscribeEvent
    private static void entityJoined(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            if (!entity.hasData(ModDataAttachments.PLAYER_POWERS)) return;

            List<Holder<Power>> powers = entity.getData(ModDataAttachments.PLAYER_POWERS);

            for (Holder<Power> power : powers) {
                Power truePower = power.value();

                if (truePower.shouldReapplyOnJoin())
                    truePower.apply(entity);
            }
        }
    }

    public static Component getPowerTranslation(Holder<Power> power) {
        Identifier key = Objects.requireNonNull(power.getKey()).identifier();
        return Component.translatable("powers." + key.getNamespace() + "." + key.getPath());
    }
}
