package com.nbbs.aboodkt.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class BlockBreakMixin {

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void noSwordBlockBreak(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;

        ItemStack heldItem = player.getMainHandStack();

        // لو ماسك سيف، امنع الكسر/الضربة
        if (heldItem.getItem() instanceof SwordItem) {
            ci.cancel();
        }
    }
}
