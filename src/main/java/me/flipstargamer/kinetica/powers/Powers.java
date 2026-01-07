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
import net.minecraft.world.entity.ai.attributes.Attribute;
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

    public static final DeferredHolder<Power, @NotNull AttributePower> HEALTH_POWER =
            registerAttribute("more_health", Attributes.MAX_HEALTH, 10, AttributeModifier.Operation.ADD_VALUE);

    public static final DeferredHolder<Power, @NotNull AttributePower> SPEED_POWER =
            registerAttribute("natural_armor", Attributes.MOVEMENT_SPEED, 0.6f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> TINY_POWER =
            registerAttribute("natural_armor", Attributes.ARMOR, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static final DeferredHolder<Power, @NotNull AttributePower> NATURAL_ARMOR =
            registerAttribute("natural_armor", Attributes.ARMOR, 5, AttributeModifier.Operation.ADD_VALUE);

    private static DeferredHolder<Power, @NotNull AttributePower> registerAttribute(String name,
                                                                                   Holder<Attribute> attributeHolder,
                                                                                   float value,
                                                                                   AttributeModifier.Operation operation) {
        return POWERS.register(name,
                () -> new AttributePower(attributeHolder, value, operation,
                        Identifier.fromNamespaceAndPath(Kinetica.MOD_ID, name + "_power")));
    }

    public static void register(IEventBus bus) {
        POWERS.register(bus);
    }
}
