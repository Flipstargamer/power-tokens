package me.flipstargamer.powertokens.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.ModDataAttachments;
import me.flipstargamer.powertokens.powers.power.Power;
import me.flipstargamer.powertokens.powers.PowerManager;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class PowerCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(Commands.literal("powers")
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("list")
                        .executes(PowerCommand::listPowers)
                )
                .then(Commands.literal("add")
                        .then(Commands.argument("power", ResourceArgument.resource(context, PowerTokenRegistries.POWER_REGISTRY_KEY))
                                .executes(PowerCommand::addPower)
                        )
                )
                .then(Commands.literal("remove")
                        .then(Commands.argument("power", ResourceArgument.resource(context, PowerTokenRegistries.POWER_REGISTRY_KEY))
                                .executes(PowerCommand::removePower)
                        )
                )
        );
    }

    private static int addPower(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        Holder<Power> power = ResourceArgument.getResource(context, "power", PowerTokenRegistries.POWER_REGISTRY_KEY);

        PowerManager.addPower(player, power);

        context.getSource().sendSuccess(() -> Component.translatable("commands.powers.add",  power.value().getTranslation()), true);

        return 1;
    }

    private static int removePower(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        Holder<Power> power = ResourceArgument.getResource(context, "power", PowerTokenRegistries.POWER_REGISTRY_KEY);

        PowerManager.removePower(player, power);

        context.getSource().sendSuccess(() -> Component.translatable("commands.powers.remove", power.value().getTranslation()), true);

        return 1;
    }

    private static int listPowers(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();

        List<Holder<Power>> powers = player.getData(ModDataAttachments.PLAYER_POWERS);

        if (powers.isEmpty()) {
            context.getSource().sendSuccess(() -> Component.translatable("commands.powers.list.none"), false);
            return 0;
        }

        MutableComponent message = Component.translatable("commands.powers.list.title").withStyle(ChatFormatting.GOLD);

        for (Holder<Power> power : powers) {
            message.append(Component.literal("\n - ").withStyle(ChatFormatting.YELLOW));
            message.append(power.value().getTranslation());
        }

        context.getSource().sendSuccess(() -> message, false);
        return powers.size(); // Returning the count is good practice in Brigadier
    }
}
