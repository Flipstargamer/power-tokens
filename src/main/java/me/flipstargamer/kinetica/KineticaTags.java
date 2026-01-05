package me.flipstargamer.kinetica;

import me.flipstargamer.kinetica.powers.Power;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;

public class KineticaTags {
    public static final TagKey<Power> POSITIVE_POWER = create("positive_power");
    public static final TagKey<Power> NEGATIVE_POWER = create("negative_power");

    private static TagKey<Power> create(String name) {
        return TagKey.create(KineticaRegistries.POWER_REGISTRY_KEY, Identifier.fromNamespaceAndPath(Kinetica.MOD_ID, name));
    }
}
