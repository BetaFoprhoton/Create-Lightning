package com.betaforge1.createlightning.content.curiosities.armor;

import com.simibubi.create.content.curiosities.armor.AllArmorMaterials;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Vanishable;

public class ElectricArmorItem extends ArmorItem implements Vanishable {
    float charge;

    public ElectricArmorItem(EquipmentSlot slot, Properties properties) {
        super(AllArmorMaterials.COPPER, slot, properties.stacksTo(1));
    }

    public ElectricArmorItem(EquipmentSlot slot, Properties properties, float charge) {
        super(AllArmorMaterials.COPPER, slot, properties.stacksTo(1));
        this.charge = charge;
    }

    public boolean isWornBy(Entity entity) {
        if (!(entity instanceof LivingEntity))
            return false;
        LivingEntity livingEntity = (LivingEntity) entity;
        return livingEntity.getItemBySlot(slot).getItem() == this;
    }



    public void setCharge(float charge) {
        this.charge = charge;
    }

    public float getCharge() {
        return charge;
    }
}
