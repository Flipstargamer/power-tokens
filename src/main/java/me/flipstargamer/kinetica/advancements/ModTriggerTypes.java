package me.flipstargamer.kinetica.advancements;

import me.flipstargamer.kinetica.Kinetica;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModTriggerTypes {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGER_TYPES =
            DeferredRegister.create(Registries.TRIGGER_TYPE, Kinetica.MOD_ID);

    public static final DeferredHolder<CriterionTrigger<?>, @NotNull PowerObtainedTrigger> POWER_OBTAINED_TRIGGER =
            TRIGGER_TYPES.register("power_triggers", PowerObtainedTrigger::new);

    public static void register(IEventBus bus) {
        TRIGGER_TYPES.register(bus);
    }
}
