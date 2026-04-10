package com.nbbs.aboodkt.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class BlockBreakMixin {

    @Inject(method = "canHarvest", at = @At("HEAD"), cancellable = true)
    private void noSwordBlockBreak(World world, net.minecraft.block.BlockState state, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {

        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack heldItem = player.getMainHandStack();

        // إذا ماسك سيف -> يمنع كسر البلوك
        if (heldItem.getItem() instanceof SwordItem) {
            cir.setReturnValue(false);
        }
    }
}
