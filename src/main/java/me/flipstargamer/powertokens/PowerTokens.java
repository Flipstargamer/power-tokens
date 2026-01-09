package me.flipstargamer.powertokens;

import me.flipstargamer.powertokens.advancements.ModTriggerTypes;
import me.flipstargamer.powertokens.items.ModCreativeTabs;
import me.flipstargamer.powertokens.items.ModItems;
import me.flipstargamer.powertokens.powers.Powers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(PowerTokens.MOD_ID)
public class PowerTokens {
    public static final String MOD_ID = "kinetica";

    public static final Logger LOGGER = LogUtils.getLogger();

    public PowerTokens(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModCreativeTabs.register(eventBus);
        Powers.register(eventBus);
        ModDataAttachments.register(eventBus);
        ModTriggerTypes.register(eventBus);
    }
}
