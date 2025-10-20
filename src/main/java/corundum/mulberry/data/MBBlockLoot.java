package corundum.mulberry.data;

import java.util.Set;

import corundum.mulberry.content.MBBlocks;
import corundum.mulberry.content.MBItems;
import corundum.mulberry.content.SticksBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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
        this.dropSelf(MBBlocks.PYRITE_BLOCK.get());

        this.add(MBBlocks.STICKS.get(),
                block -> LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.STICK)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(SticksBlock.STICKS, 1))))
                                .add(LootItem.lootTableItem(Items.STICK)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(SticksBlock.STICKS, 2))))
                                .add(LootItem.lootTableItem(Items.STICK)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(SticksBlock.STICKS, 3))))
                        )
        );

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

        this.add(
                MBBlocks.PYRITE_ORE.get(),
                (block) -> createSilkTouchDispatchTable(
                        block,
                        applyExplosionDecay(
                                block,
                                LootItem.lootTableItem(MBItems.PYRITE)
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