package com.betaforge1.createlightning.context.render;

import com.betaforge1.createlightning.context.entities.PosLightningEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector4f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class PosLightningRender extends EntityRenderer<PosLightningEntity> {
    private LightningRender lightningRender = new LightningRender();
    private LightningBoltData.BoltRenderInfo lightningBoltData = new LightningBoltData.BoltRenderInfo(1.3F, 0.15F, 0.5F, 0.25F, new Vector4f(0.1F, 0.3F, 0.5F, 0.5F), 0.45F);

    public PosLightningRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public boolean shouldRender(PosLightningEntity entity, Frustum frustum, double x, double y, double z) {
        return super.shouldRender(entity, frustum, x, y, z);
    }

    @Override
    public void render(PosLightningEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light) {
        super.render(entity, yaw, partialTicks, poseStack, buffer, light);
        poseStack.pushPose();
        Vec3 from = entity.getEyePosition(), to = entity.getTo();
        float x = (float) Mth.lerp(partialTicks, entity.xo, entity.getX());
        float y = (float) Mth.lerp(partialTicks, entity.yo, entity.getY());
        float z = (float) Mth.lerp(partialTicks, entity.zo, entity.getZ());
        if (from != null && to != null) {
            LightningBoltData bolt = new LightningBoltData(lightningBoltData, from, to, 5)
                    .size(entity.getCharge() / 100.0F)
                    .lifespan(20)
                    .spawn(LightningBoltData.SpawnFunction.NO_DELAY);
            lightningRender.update(null, bolt, partialTicks);
            poseStack.translate(-x, -y, -z);
            lightningRender.render(partialTicks, poseStack, buffer);
        }
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(PosLightningEntity entity) {
        return null;
    }
}
