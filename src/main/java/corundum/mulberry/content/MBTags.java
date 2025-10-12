package corundum.mulberry.content;

import corundum.mulberry.Mulberry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MBTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_METEORITE_TOOL = createTag("needs_meteorite_tool");
        public static final TagKey<Block> INCORRECT_FOR_METEORITE_TOOL = createTag("incorrect_for_meteorite_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Mulberry.MODID, name));
        }
    }
}