package com.betaforge1.createlightning.foundation.mixin;

import net.minecraft.world.level.block.LightningRodBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LightningRodBlock.class)
public class onLightningStrikeMixin {
    /**
     * @author
     * @reason
     */

//    @Inject(at = @At("HEAD"), method = "onLightningStrike")
//    public void onLightningStrike(BlockState state, Level level, BlockPos blockPos, CallbackInfo ci) {
//        ElectrostaticMarker.getOrCreateElectrostaticMarker(level, blockPos).setCharge(0);
//    }

}
