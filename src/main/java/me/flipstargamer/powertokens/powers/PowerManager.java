package me.flipstargamer.powertokens.powers;

import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.advancements.ModTriggerTypes;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.List;

@EventBusSubscriber(modid = PowerTokens.MOD_ID)
public class PowerManager {
    private PowerManager() {}

    public static void addPower(LivingEntity entity, Holder<Power> power) {
        List<Holder<Power>> powers = entity.getData(ModDataAttachments.PLAYER_POWERS);

        if (powers.contains(power))
            return;

        powers.add(power);
        entity.syncData(ModDataAttachments.PLAYER_POWERS);

        Power truePower = power.value();
        truePower.apply(entity);

        if (entity instanceof ServerPlayer player) {
            ModTriggerTypes.POWER_OBTAINED_TRIGGER.get().trigger(player, power.value());
        }
    }

    public static void addPower(LivingEntity entity, Power power) {
        addPower(entity, PowerTokenRegistries.POWER_REGISTRY.wrapAsHolder(power));
    }

    public static void removePower(LivingEntity entity, Holder<Power> power) {
        List<Holder<Power>> powers = entity.getData(ModDataAttachments.PLAYER_POWERS);

        if (!powers.contains(power))
            return;

        powers.remove(power);
        entity.syncData(ModDataAttachments.PLAYER_POWERS);

        Power truePower = power.value();
        truePower.remove(entity);
    }

    public static void removePower(LivingEntity entity, Power power) {
        removePower(entity, PowerTokenRegistries.POWER_REGISTRY.wrapAsHolder(power));
    }

    @SubscribeEvent
    public static void clonePowers(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        PowerTokens.LOGGER.debug("Test test!");

        Player originalPlayer = event.getOriginal();
        if (!originalPlayer.hasData(ModDataAttachments.PLAYER_POWERS)) return;

        Player newPlayer = event.getEntity();
        List<Holder<Power>> powers = originalPlayer.getData(ModDataAttachments.PLAYER_POWERS);

        for (Holder<Power> power : powers) {
            addPower(newPlayer, power);
        }
    }

    @SubscribeEvent
    public static void entityJoined(EntityJoinLevelEvent event) {
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

    @SubscribeEvent
    public static void tickPowers(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            if (entity.level().isClientSide()) return;
            if (!entity.hasData(ModDataAttachments.PLAYER_POWERS)) return;

            List<Holder<Power>> powers = entity.getData(ModDataAttachments.PLAYER_POWERS);

            for (Holder<Power> power : powers) {
                Power truePower = power.value();

                if (truePower.shouldTick())
                    truePower.tick(entity);
            }
        }
    }
}
