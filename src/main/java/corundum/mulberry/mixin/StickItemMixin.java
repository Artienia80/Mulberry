package corundum.mulberry.mixin;

import corundum.mulberry.content.MBBlocks;
import corundum.mulberry.content.SticksBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class StickItemMixin {

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void onUseOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if ((Object)this == Items.STICK) {
            Level level = context.getLevel();
            BlockPos pos = context.getClickedPos();
            BlockState clickedState = level.getBlockState(pos);
            Direction face = context.getClickedFace();

            if (clickedState.getBlock() instanceof SticksBlock) {
                ItemStack stack = context.getItemInHand();
                InteractionResult result = clickedState.useItemOn(stack, level, context.getPlayer(), context.getHand(),
                                new net.minecraft.world.phys.BlockHitResult(context.getClickLocation(), face, pos, false))
                        .result();
                if (result != InteractionResult.PASS) {
                    cir.setReturnValue(result);
                }
                return;
            }

            if (face == Direction.UP && level.getBlockState(pos).isFaceSturdy(level, pos, Direction.UP)) {
                BlockPos placePos = pos.above();

                if (level.getBlockState(placePos).isAir()) {
                    if (!level.isClientSide) {
                        level.setBlock(placePos, MBBlocks.STICKS.get().defaultBlockState(), 3);
                        level.playSound(null, placePos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                        if (!context.getPlayer().getAbilities().instabuild) {
                            context.getItemInHand().shrink(1);
                        }
                    }
                    cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
                }
            }
        }
    }
}