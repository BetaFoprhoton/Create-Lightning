package com.betaforge1.createlightning.content.curiosities.armor;

import com.betaforge1.createlightning.CLItems;
import com.betaforge1.createlightning.CreateLightning;
import com.simibubi.create.content.contraptions.fluids.actors.HosePulleyBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CopperLeydenHelmetItem extends ElectricArmorItem {
    public static final ResourceLocation TEXTURE = CreateLightning.asResource("textures/models/armor/copper_leyden_helmet.png");
    private static final String TEXTURE_STRING;
    public CopperLeydenHelmetItem(Properties properties) {
        super(EquipmentSlot.HEAD, properties);
    }

    @SubscribeEvent
    public static void nothing(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();

        Level world = entity.level;

        if (!CLItems.LEYDEN_HELMET.get()
                .isWornBy(entity))
            return;

    }


    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return TEXTURE_STRING;
    }

    static {
        TEXTURE_STRING = TEXTURE.toString();
    }
}
