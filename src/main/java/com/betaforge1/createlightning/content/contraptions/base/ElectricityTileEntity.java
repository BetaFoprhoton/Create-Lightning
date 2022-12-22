package com.betaforge1.createlightning.content.contraptions.base;

import com.simibubi.create.content.contraptions.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.tileEntity.SmartTileEntity;
import com.simibubi.create.foundation.tileEntity.TileEntityBehaviour;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ElectricityTileEntity extends SmartTileEntity implements IHaveGoggleInformation {
    protected float charge;

    public ElectricityTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, float charge) {
        super(type, pos, state);
        this.charge = charge;
    }

    @Override
    public void addBehaviours(List<TileEntityBehaviour> behaviours) {}


    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        Lang.translate("tooltip.blockElectricCharge")
                .forGoggles(tooltip);

        return true;
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        tag.putFloat("Charge", charge);
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        charge = tag.getFloat("Charge");
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public float getCharge() {
        return charge;
    }
}
