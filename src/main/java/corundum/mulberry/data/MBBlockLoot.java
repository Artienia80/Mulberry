package corundum.mulberry.data;

import java.util.Set;

import corundum.mulberry.content.MBBlocks;
import corundum.mulberry.content.MBItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;

public class MBBlockLoot extends BlockLootSubProvider {
    public MBBlockLoot(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MBBlocks.BLOCKS.getEntries()
                .stream()
                .map(block -> (Block) block.value())
                .toList();
    }

    @Override
    protected void generate() {
        this.dropSelf(MBBlocks.METEORITE_BLOCK.get());
        this.dropSelf(MBBlocks.METEORITE_SLAG_BLOCK.get());

        this.add(
                MBBlocks.METEORITE_ROCK.get(),
                (block) -> createSilkTouchDispatchTable(
                        block,
                        applyExplosionDecay(
                                block,
                                LootItem.lootTableItem(MBItems.METEORITE_SLAG)
                                        .apply(
                                                ApplyBonusCount.addOreBonusCount(
                                                        registries
                                                                .lookupOrThrow(Registries.ENCHANTMENT)
                                                                .getOrThrow(Enchantments.FORTUNE)
                                                )
                                        )
                        )
                )
        );
    }
}