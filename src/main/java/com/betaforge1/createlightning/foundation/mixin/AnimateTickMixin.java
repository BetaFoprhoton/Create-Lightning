package com.betaforge1.createlightning.foundation.mixin;

import net.minecraft.world.level.block.LightningRodBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LightningRodBlock.class)
public class AnimateTickMixin {

    /**
     * @author sb.
     * @reason nothing
     */
//
//    @Overwrite
//    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random random) {
//        if (level.isThundering() && (long)level.random.nextInt(200) <= level.getGameTime() % 200L && blockPos.getY() == level.getHeight(Heightmap.Types.WORLD_SURFACE, blockPos.getX(), blockPos.getZ()) - 1) {
//            ParticleUtils.spawnParticlesAlongAxis(blockState.getValue(FACING).getAxis(), level, blockPos, 0.125D, ParticleTypes.ELECTRIC_SPARK, UniformInt.of(1, 2));
//            getOrCreateElectrostaticMarker(level, blockPos).addCharge(10.0F);
//        }
//    }
}
