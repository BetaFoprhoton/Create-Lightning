package com.betaforge1.createlightning.context.entities;

import com.betaforge1.createlightning.CLEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class ElectrostaticMarker extends Marker {
    protected BlockPos holdBlockPos;
    protected BlockState holdBlockState;
    protected float charge;
    protected int age = 0;
    public ElectrostaticMarker(Level level, BlockPos holdBlockPos, float charge) {
        super(CLEntityTypes.ELECTROSTATIC_MARKER.get(), level);
        this.level = level;
        this.holdBlockState = level.getBlockState(holdBlockPos);
        this.holdBlockPos = holdBlockPos;
        this.charge = charge;
    }

    public ElectrostaticMarker(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public ElectrostaticMarker(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(CLEntityTypes.ELECTROSTATIC_MARKER.get(), world);
    }


    @Override
    public void tick() {
        boolean has = true;
        for (ElectrostaticMarker marker : level.getEntitiesOfClass(ElectrostaticMarker.class, new AABB(holdBlockPos))) {
            if (marker != null && has) has = false;
            if (marker != null && !has) marker.remove(RemovalReason.DISCARDED);
        }

        holdBlockState = level.getBlockState(holdBlockPos);
        for (BlockPos blockPos : getAdjacentBlockPos()) {
            ElectrostaticMarker marker = getOrCreateElectrostaticMarker(level, blockPos);
            if (abs(marker.charge) <= abs(this.charge * 0.5)) {
                moveChargeTo((float) (this.charge * 0.5 - marker.charge), marker);
            }
        }
        if (-0.1F <= charge && charge <= 0.1F) {
            remove(RemovalReason.DISCARDED);
        }
        animateTick();
        moveTo(Vec3.atCenterOf(holdBlockPos));
        ++ this.age;
    }

    @Override
    public void kill() {
    }

    private float getBlockConductivity (BlockState state) {
        //state.getTags().anyMatch(tagKey -> (tagKey))
        return 1.0F;
    }

    public synchronized void moveChargeTo (float charge, @NotNull ElectrostaticMarker target) {
        target.charge += charge;
        this.charge -= charge;
    }

    public synchronized float delCharge (float charge) {
        this.charge -= charge;
        return this.charge;
    }

    public synchronized float addCharge (float charge) {
        this.charge += charge;
        return this.charge;
    }

    public synchronized void setCharge (float charge) {
        this.charge = charge;
    }

    public synchronized float getCharge (float charge) {
        return this.charge;
    }

    private BlockPos[] getAdjacentBlockPos() {
        int x = holdBlockPos.getX(),
                y = holdBlockPos.getX(),
                z = holdBlockPos.getZ();
        return new BlockPos[] {new BlockPos(x + 1, y, z),
                                new BlockPos(x - 1, y, z),
                                new BlockPos(x, y + 1, z),
                                new BlockPos(x, y - 1, z),
                                new BlockPos(x, y, z + 1),
                                new BlockPos(x, y, z - 1),};
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        holdBlockPos = new BlockPos(tag.getIntArray("HoldBlockPos")[0],
                                    tag.getIntArray("HoldBlockPos")[1],
                                    tag.getIntArray("HoldBlockPos")[2]);
        charge = tag.getFloat("Charge");

    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putIntArray("HoldBlockPos", new int[] {holdBlockPos.getX(),
                                                                holdBlockPos.getY(),
                                                                holdBlockPos.getZ(), 0});
        tag.putFloat("Charge", charge);
    }

    protected void animateTick() {
        if ((long) level.random.nextInt(200) <= level.getGameTime() % 200L) {
            if (charge <= 10.0F) return;
            ParticleUtils.spawnParticlesOnBlockFaces(this.level, holdBlockPos, ParticleTypes.ELECTRIC_SPARK, UniformInt.of((int) (charge * 0.5), (int) (charge * 0.8)));
        }
    }

    public static void addChargeToBlock (Level level, BlockPos blockPos, float charge) {
        ElectrostaticMarker marker = getOrCreateElectrostaticMarker(level, blockPos);
        marker.charge = charge;
        marker.animateTick();
    }

    public static ElectrostaticMarker getOrCreateElectrostaticMarker (Level level, BlockPos addBlockPos) {
        List<ElectrostaticMarker> markers = level.getEntitiesOfClass(ElectrostaticMarker.class, new AABB(addBlockPos));
        if (markers.isEmpty() || markers.get(0) == null) {
            ElectrostaticMarker newMarker = new ElectrostaticMarker(level, addBlockPos, 0);
            level.addFreshEntity(newMarker);
            return newMarker;
        }

        boolean has = true;
        List <ElectrostaticMarker> removeList = new LinkedList<>();
        for (ElectrostaticMarker marker : markers) {
            if (marker != null && has) has = false;
            if (marker != null && !has) removeList.add(marker);
        }

        for (ElectrostaticMarker marker : removeList) {
            marker.remove(RemovalReason.DISCARDED);
        }
        return markers.get(0);
    }
}
