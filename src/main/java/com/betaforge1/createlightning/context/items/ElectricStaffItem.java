package com.betaforge1.createlightning.context.items;

import com.betaforge1.createlightning.CLEntityTypes;
import com.betaforge1.createlightning.context.entities.PosLightningEntity;
import com.simibubi.create.content.curiosities.weapons.PotatoCannonItem;
import com.simibubi.create.content.curiosities.weapons.PotatoProjectileRenderer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.context.UseOnContext;

public class ElectricStaffItem extends Item implements Vanishable {
    float change;

    public ElectricStaffItem(Properties properties) {
        super(properties);
    }

    public ElectricStaffItem(float change, Properties properties) {
        super(properties);
        this.change = change;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        PosLightningEntity lightning = CLEntityTypes.POS_LIGHTNING_ENTITY.create(context.getLevel());
        lightning.setPos(context.getClickLocation().add(0, 0, 0));
        lightning.setTo(context.getClickLocation().add(0, 5, 0));
        lightning.setCharge(100.0F);
        lightning.setDiesInTicks(20);
        //System.out.println("Player use on:" + context.getClickedPos());
        context.getLevel().addFreshEntity(lightning);
        return super.useOn(context);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 1;
    }

    @Override
    public int getDamage(ItemStack stack) {
        return 7;
    }
}
