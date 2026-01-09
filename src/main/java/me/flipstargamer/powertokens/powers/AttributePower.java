package me.flipstargamer.powertokens.powers;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AttributePower extends Power {
    public final Holder<Attribute> attribute;
    public final float value;
    public final AttributeModifier.Operation operation;
    public final Identifier id;

    public AttributePower(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation, Identifier id) {
        this.attribute = attribute;
        this.value = value;
        this.operation = operation;
        this.id = id;
    }

    @Override
    public void apply(LivingEntity entity) {
        AttributeMap attributeMap = entity.getAttributes();
        AttributeInstance attributeInstance = attributeMap.getInstance(attribute);

        assert attributeInstance != null;
        attributeInstance.addOrReplacePermanentModifier(new AttributeModifier(
                id,
                value,
                operation
        ));
    }

    @Override
    public void remove(LivingEntity entity) {
        AttributeMap attributeMap = entity.getAttributes();
        AttributeInstance attributeInstance = attributeMap.getInstance(attribute);

        assert attributeInstance != null;
        attributeInstance.removeModifier(id);
    }
}
