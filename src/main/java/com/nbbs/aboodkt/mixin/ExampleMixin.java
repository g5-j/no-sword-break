package com.nbbs.aboodkt.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class ExampleMixin {

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void preventSwordBlockUse(Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack item = player.getStackInHand(hand);

        if (item.getItem() instanceof SwordItem) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }
}
