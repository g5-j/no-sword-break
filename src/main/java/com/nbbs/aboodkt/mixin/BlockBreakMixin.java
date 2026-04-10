package com.nbbs.aboodkt.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class BlockBreakMixin {

    @Inject(method = "attackBlock", at = @At("HEAD"), cancellable = true)
    private void cancelSwordBreak(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {

        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null) return;

        ItemStack item = client.player.getMainHandStack();

        if (item.getItem() instanceof SwordItem) {
            cir.setReturnValue(false);
        }
    }
}
