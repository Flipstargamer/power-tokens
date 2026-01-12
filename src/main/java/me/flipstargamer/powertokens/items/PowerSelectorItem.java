package me.flipstargamer.powertokens.items;

import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.gui.PowerPickerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PowerSelectorItem extends Item {
    public PowerSelectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            PowerPickerHelper.open(player.getItemInHand(hand));

            return InteractionResult.SUCCESS;
        }

        player.setData(ModDataAttachments.CAN_PICK_POWER, true);
        return InteractionResult.SUCCESS_SERVER;
    }
}
