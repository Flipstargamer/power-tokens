package me.flipstargamer.kinetica.items;

import me.flipstargamer.kinetica.KineticaRegistries;
import me.flipstargamer.kinetica.KineticaTags;
import me.flipstargamer.kinetica.ModDataAttachments;
import me.flipstargamer.kinetica.powers.Power;
import me.flipstargamer.kinetica.powers.PowerManager;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class PowerTokenItem extends Item {
    public PowerTokenItem(Properties properties) {
        super(properties);
    }

    private Optional<Holder<Power>> getRandomPower(LivingEntity entity) {
        List<Holder<Power>> ownedPowers = entity.getData(ModDataAttachments.PLAYER_POWERS);

        List<Holder<Power>> available = KineticaRegistries.POWER_REGISTRY.getOrThrow(KineticaTags.POSITIVE_POWER).stream()
                .filter((power) -> !ownedPowers.contains(power))
                .toList();

        if (available.isEmpty())
            return Optional.empty();

        return Optional.of(available.get(entity.getRandom().nextInt(available.size())));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide()) {
            Optional<Holder<Power>> powerOptional = getRandomPower(player);

            if (powerOptional.isEmpty()) {
                player.displayClientMessage(Component.translatable("item.kinetica.power_token.fail")
                        .withStyle(ChatFormatting.RED), true);
                return InteractionResult.SUCCESS_SERVER;
            }

            Holder<Power> power = powerOptional.get();

            player.getItemInHand(hand).shrink(1);
            player.displayClientMessage(Component.translatable("item.kinetica.power.token.pass",
                    power.value().getTranslation()), true);
            PowerManager.addPower(player, power);
            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.SUCCESS;
    }
}
