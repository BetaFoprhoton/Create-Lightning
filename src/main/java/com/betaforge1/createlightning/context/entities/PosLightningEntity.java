package com.betaforge1.createlightning.context.entities;

import com.betaforge1.createlightning.CLEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class PosLightningEntity extends Entity {
    protected int diesIn;
    protected float charge;
    protected Vec3 to = new Vec3(0, 0, 0);


    public PosLightningEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    public PosLightningEntity(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(CLEntityTypes.POS_LIGHTNING_ENTITY.get(), world);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void defineSynchedData() {
        this.diesIn = 5;
        this.charge = 15.0F;
        this.to = new Vec3(0, 0, 0);
    }

    @Override
    public void tick() {
        super.tick();
        if(!level.isClientSide) {
            if (getDiesInTicks() > 0){
                this.setDiesInTicks(getDiesInTicks() - 1);
            } else {
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    private boolean hasLineOfSight(Entity entity) {
        if (entity.level != this.level) {
            return false;
        } else {
            Vec3 vec3 = new Vec3(this.getX(), this.getEyeY(), this.getZ());
            Vec3 vec31 = new Vec3(entity.getX(), entity.getEyeY(), entity.getZ());
            if (vec31.distanceTo(vec3) > 128.0D) {
                return false;
            } else {
                return this.level.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)).getType() == HitResult.Type.MISS;
            }
        }
    }

    public static EntityType.Builder<?> build(EntityType.Builder<?> builder) {
        @SuppressWarnings("unchecked")
        EntityType.Builder<PosLightningEntity> entityBuilder = (EntityType.Builder<PosLightningEntity>) builder;
        return entityBuilder.sized(0.5f, 0.5f);
    }

    public int getDiesInTicks() {
        return this.diesIn;
    }

    public void setDiesInTicks(int i) {
        this.diesIn = i;
    }

    public Float getCharge() {
        return this.charge;
    }

    public void setCharge(float f) {
        this.charge = f;
    }

    public Vec3 getTo() {
        return to;
    }

    public void setTo(Vec3 To) {
        this.to = To;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }
}
