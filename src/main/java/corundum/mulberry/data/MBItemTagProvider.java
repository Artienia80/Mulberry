package corundum.mulberry.data;

import corundum.mulberry.Mulberry;
import corundum.mulberry.content.MBItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MBItemTagProvider extends ItemTagsProvider {
    public MBItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                             CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Mulberry.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SWORDS)
                .add(MBItems.METEORITE_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(MBItems.METEORITE_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(MBItems.METEORITE_SHOVEL.get());
        tag(ItemTags.AXES)
                .add(MBItems.METEORITE_AXE.get());
        tag(ItemTags.HOES)
                .add(MBItems.METEORITE_HOE.get());
    }
}