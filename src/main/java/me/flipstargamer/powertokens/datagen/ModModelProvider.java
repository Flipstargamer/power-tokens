package me.flipstargamer.powertokens.datagen;

import me.flipstargamer.powertokens.PowerTokens;
import me.flipstargamer.powertokens.items.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, PowerTokens.MOD_ID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.POWER_TOKEN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.POWER_TOKEN_CORE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.POWER_TOKEN_SHARD.get(), ModelTemplates.FLAT_ITEM);
    }
}
