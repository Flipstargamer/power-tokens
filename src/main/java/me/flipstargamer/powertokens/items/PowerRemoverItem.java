package me.flipstargamer.powertokens.items;

import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.advancements.ModTriggerTypes;
import me.flipstargamer.powertokens.powers.power.Power;
import me.flipstargamer.powertokens.powers.PowerManager;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PowerRemoverItem extends Item {
    public PowerRemoverItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            List<Holder<Power>> ownedPowers = new ArrayList<>(serverPlayer.getData(ModDataAttachments.PLAYER_POWERS));

            for (Holder<Power> power : ownedPowers) {
                PowerManager.removePower(serverPlayer, power);
            }

            if (!ownedPowers.isEmpty()) {
                ModTriggerTypes.POWERS_WIPED_TRIGGER.get().trigger(serverPlayer);
            }

            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.SUCCESS;
    }
}
