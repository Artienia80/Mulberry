package corundum.mulberry.content;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class MBToolTiers {
    public static final Tier METEORITE = new SimpleTier(MBTags.Blocks.INCORRECT_FOR_METEORITE_TOOL,
            1561, 8.0F, 3.0F, 10, () -> Ingredient.of(MBItems.METEORITE_INGOT));

    public static final Tier CHISEL = new SimpleTier(MBTags.Blocks.INCORRECT_FOR_CHISEL_TOOL,
            131, 4.0F, 0.0F, 5, () -> Ingredient.of(MBTags.Items.CHISEL_REPAIR_ITEM));

}