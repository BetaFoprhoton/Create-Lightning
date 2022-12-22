package com.betaforge1.createlightning.context.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ResistiveGelItem extends Item {

    public ResistiveGelItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        Player playerEntity = entity instanceof Player ? (Player) entity : null;
        if (playerEntity instanceof ServerPlayer)
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) playerEntity, stack);

        if (!world.isClientSide)
            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 3 * 60 * 20, 0, false, false, false));

        if (playerEntity != null) {
            playerEntity.awardStat(Stats.ITEM_USED.get(this));
            playerEntity.getFoodData().eat(1, .6F);
            if (!playerEntity.getAbilities().instabuild)
                stack.shrink(1);
        }

        return stack;
    }

    public int getUseDuration(ItemStack p_77626_1_) {
        return 50;
    }

    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.EAT;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (!level.getBlockState(pos).isAir())
            return InteractionResult.SUCCESS;
        return super.useOn(context);
    }
}
