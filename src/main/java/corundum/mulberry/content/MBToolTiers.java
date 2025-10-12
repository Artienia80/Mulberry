package corundum.mulberry.content;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class MBToolTiers {
    public static final Tier METEORITE = new SimpleTier(MBTags.Blocks.INCORRECT_FOR_METEORITE_TOOL,
            1561, 8.0F, 3.0F, 10, () -> Ingredient.of(MBItems.METEORITE_INGOT));

}