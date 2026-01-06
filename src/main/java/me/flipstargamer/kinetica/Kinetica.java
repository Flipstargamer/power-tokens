package me.flipstargamer.kinetica;

import me.flipstargamer.kinetica.advancements.ModTriggerTypes;
import me.flipstargamer.kinetica.items.ModCreativeTabs;
import me.flipstargamer.kinetica.items.ModItems;
import me.flipstargamer.kinetica.powers.Powers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Kinetica.MOD_ID)
public class Kinetica {
    public static final String MOD_ID = "kinetica";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Kinetica(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModCreativeTabs.register(eventBus);
        Powers.register(eventBus);
        ModDataAttachments.register(eventBus);
        ModTriggerTypes.register(eventBus);
    }
}
