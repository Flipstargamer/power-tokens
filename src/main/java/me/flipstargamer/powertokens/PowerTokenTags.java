package me.flipstargamer.powertokens;

import me.flipstargamer.powertokens.powers.power.Power;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;

public class PowerTokenTags {
    public static final TagKey<Power> POSITIVE_POWER = create("positive_power");
    public static final TagKey<Power> NEGATIVE_POWER = create("negative_power");

    private static TagKey<Power> create(String name) {
        return TagKey.create(PowerTokenRegistries.POWER_REGISTRY_KEY, Identifier.fromNamespaceAndPath(PowerTokens.MOD_ID, name));
    }
}
