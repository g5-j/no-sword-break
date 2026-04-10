package com.nbbs.aboodkt.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class BlockBreakMixin {

    @Inject(method = "canHarvest", at = @At("HEAD"), cancellable = true)
    private void noSwordBreak(BlockState state, CallbackInfoReturnable<Boolean> cir) {

        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack item = player.getMainHandStack();

        if (item.getItem() instanceof SwordItem) {
            cir.setReturnValue(false);
        }
    }
}
