package me.flipstargamer.powertokens.items;

import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.powers.Power;
import me.flipstargamer.powertokens.powers.PowerManager;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PowerTokenItem extends Item {
    public final List<TagKey<Power>> tagKeyList;

    public PowerTokenItem(Properties properties, List<TagKey<Power>> tagKeyList) {
        super(properties);

        this.tagKeyList = tagKeyList;
    }

    private Optional<Holder<Power>> getRandomPower(LivingEntity entity, TagKey<Power> fromTag) {
        List<Holder<Power>> ownedPowers = entity.getData(ModDataAttachments.PLAYER_POWERS);
        Registry<Power> powerRegistry = entity.level().registryAccess().lookupOrThrow(PowerTokenRegistries.POWER_REGISTRY_KEY);

        List<Holder<Power>> available = powerRegistry.getOrThrow(fromTag).stream()
                .filter((power) -> !ownedPowers.contains(power))
                .toList();

        if (available.isEmpty())
            return Optional.empty();

        return Optional.of(available.get(entity.getRandom().nextInt(available.size())));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            ArrayList<Holder<Power>> powersPicked = new ArrayList<>();

            for (TagKey<Power> tagKey : tagKeyList) {
                Optional<Holder<Power>> powerOptional = getRandomPower(serverPlayer, tagKey);

                if (powerOptional.isEmpty()) {
                    serverPlayer.displayClientMessage(Component.translatable("item.power_tokens.power_token.fail")
                            .withStyle(ChatFormatting.RED), true);
                    return InteractionResult.SUCCESS_SERVER;
                }

                powersPicked.add(powerOptional.get());
            }

            for (Holder<Power> power : powersPicked) {
                PowerManager.addPower(serverPlayer, power);

                serverPlayer.sendSystemMessage(Component.translatable("item.power_tokens.power.token.pass",
                        power.value().getTranslation()));
            }

            PowerTokens.LOGGER.debug("Test debug");

            serverPlayer.getItemInHand(hand).shrink(1);
            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.SUCCESS;
    }
}
