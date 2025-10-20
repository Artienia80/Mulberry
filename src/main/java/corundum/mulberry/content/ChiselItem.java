package corundum.mulberry.content;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = "mulberry")
public class ChiselItem extends DiggerItem {

    public ChiselItem(Tier tier, Item.Properties properties) {
        super(tier, MBTags.Blocks.NEEDS_CHISEL_TOOL, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        ItemStack tool = event.getPlayer().getMainHandItem();

        if (!(tool.getItem() instanceof ChiselItem)) {
            return;
        }

        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        Block block = state.getBlock();

        BlockPos above = pos.above();
        BlockPos below = pos.below();

        if (!level.getBlockState(above).isAir() || level.getBlockState(below).isAir()) {
            return;
        }

        BlockState newState = null;

        if (state.is(BlockTags.LOGS)) {
            newState = Blocks.CRAFTING_TABLE.defaultBlockState();
        }
        else if (block == Blocks.BRICKS) {
            net.minecraft.core.Direction facing = event.getPlayer().getDirection().getOpposite();
            newState = Blocks.FURNACE.defaultBlockState()
                    .setValue(net.minecraft.world.level.block.FurnaceBlock.FACING, facing);
        }

        if (newState != null) {
            event.setCanceled(true);

            level.setBlock(pos, newState, 2);
            level.setBlock(pos, newState, 3);

            if (!level.isClientSide) {
                tool.hurtAndBreak(26, event.getPlayer(),
                        net.minecraft.world.entity.EquipmentSlot.MAINHAND);

                event.getPlayer().awardStat(net.minecraft.stats.Stats.ITEM_USED.get(tool.getItem()));
            }
        }
    }
}