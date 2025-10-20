package corundum.mulberry.data;

import corundum.mulberry.Mulberry;
import corundum.mulberry.content.MBBlocks;
import corundum.mulberry.content.MBTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MBBlockTagProvider extends BlockTagsProvider {
    public MBBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Mulberry.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MBBlocks.METEORITE_ROCK.get())
                .add(MBBlocks.METEORITE_BLOCK.get())
                .add(MBBlocks.METEORITE_SLAG_BLOCK.get())
                .add(MBBlocks.PYRITE_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(MBBlocks.PYRITE_ORE.get())
                .add(MBBlocks.PYRITE_BLOCK.get());


        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(MBBlocks.METEORITE_ROCK.get())
                .add(MBBlocks.METEORITE_BLOCK.get())
                .add(MBBlocks.METEORITE_SLAG_BLOCK.get());

        tag(MBTags.Blocks.NEEDS_METEORITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(MBTags.Blocks.INCORRECT_FOR_METEORITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(MBTags.Blocks.NEEDS_METEORITE_TOOL);

    }
}