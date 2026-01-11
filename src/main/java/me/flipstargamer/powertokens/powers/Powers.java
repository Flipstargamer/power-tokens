package me.flipstargamer.powertokens.powers;

import me.flipstargamer.powertokens.PowerTokenRegistries;
import me.flipstargamer.powertokens.PowerTokens;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = PowerTokens.MOD_ID)
public class Powers {
    // Positives

    public static final DeferredRegister<Power> POWERS = DeferredRegister.create(PowerTokenRegistries.POWER_REGISTRY, PowerTokens.MOD_ID);

    public static final DeferredHolder<Power, @NotNull AttributePower> HEALTH_POWER =
            registerAttribute("more_health", Attributes.MAX_HEALTH, 10, AttributeModifier.Operation.ADD_VALUE);

    public static final DeferredHolder<Power, @NotNull AttributePower> SPEED_POWER =
            registerAttribute("more_speed", Attributes.MOVEMENT_SPEED, 0.6f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> TINY_POWER =
            registerAttribute("tiny", Attributes.ARMOR, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> NATURAL_ARMOR =
            registerAttribute("natural_armor", Attributes.ARMOR, 5, AttributeModifier.Operation.ADD_VALUE);

    public static final DeferredHolder<Power, @NotNull AttributePower> BURN_RESISTANCE =
            registerAttribute("burn_resistance", Attributes.BURNING_TIME, -1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> MINER_TRAINING =
            registerAttribute("miner_training", Attributes.BLOCK_BREAK_SPEED, 1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> SWIFT_HANDS =
            registerAttribute("swift_hands", Attributes.ATTACK_SPEED, 0.7f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> LESS_KNOCKBACK =
            registerAttribute("less_knockback", Attributes.ATTACK_KNOCKBACK, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    // Negatives

    public static final DeferredHolder<Power, @NotNull AttributePower> LESS_HEALTH =
            registerAttribute("less_health", Attributes.MAX_HEALTH, -5, AttributeModifier.Operation.ADD_VALUE);

    public static final DeferredHolder<Power, @NotNull AttributePower> FLAMMABLE =
            registerAttribute("flammable", Attributes.BURNING_TIME, 1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> WEAKENED_DEFENSES =
            registerAttribute("weakened_defense", Attributes.ARMOR, -5, AttributeModifier.Operation.ADD_VALUE);

    public static final DeferredHolder<Power, @NotNull AttributePower> MORE_KNOCKBACK =
            registerAttribute("more_knockback", Attributes.ATTACK_KNOCKBACK, 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> HEAVY_HANDS =
            registerAttribute("heavy_hands", Attributes.ATTACK_SPEED, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);



    private static DeferredHolder<Power, @NotNull AttributePower> registerAttribute(String name,
                                                                                   Holder<Attribute> attributeHolder,
                                                                                   float value,
                                                                                   AttributeModifier.Operation operation) {
        return POWERS.register(name,
                () -> new AttributePower(attributeHolder, value, operation,
                        Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, name + "_power")));
    }

    public static void register(IEventBus bus) {
        POWERS.register(bus);
    }
}
