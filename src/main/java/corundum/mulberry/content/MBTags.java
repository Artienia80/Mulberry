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

        public static final TagKey<Block> NEEDS_CHISEL_TOOL = createTag("needs_chisel_tool");
        public static final TagKey<Block> INCORRECT_FOR_CHISEL_TOOL = createTag("incorrect_for_chisel_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Mulberry.MODID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> CHISEL_REPAIR_ITEM = createTag("chisel_repair_item");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Mulberry.MODID, name));
        }
    }
}