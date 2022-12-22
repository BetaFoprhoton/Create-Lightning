package com.betaforge1.createlightning;

import com.betaforge1.createlightning.context.entities.ElectrostaticMarker;
import com.betaforge1.createlightning.context.entities.PosLightningEntity;
import com.betaforge1.createlightning.context.render.PosLightningRender;
import com.simibubi.create.Create;
import com.simibubi.create.content.curiosities.weapons.PotatoProjectileEntity;
import com.simibubi.create.content.curiosities.weapons.PotatoProjectileRenderer;
import com.simibubi.create.foundation.data.CreateEntityBuilder;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.repack.registrate.util.entry.EntityEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullConsumer;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import com.simibubi.create.repack.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CLEntityTypes {

    public static final DeferredRegister<EntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.ENTITIES, CreateLightning.MODID);

    public static final RegistryObject<EntityType<ElectrostaticMarker>> ELECTROSTATIC_MARKER = DEF_REG.register("electrostatic_marker", () -> build(EntityType.Builder.of(ElectrostaticMarker::new, MobCategory.MISC)
            .sized(0.0F, 0.0F).setCustomClientFactory(ElectrostaticMarker::new)
            .fireImmune(), "electrostatic_marker"));

    //public static final RegistryObject<EntityType<PosLightningEntity>> POS_LIGHTNING_ENTITY = DEF_REG.register("pos_lightning", () -> build(EntityType.Builder.of(PosLightningEntity::new, MobCategory.MISC)
    //        .sized(0.5F, 0.5F).setCustomClientFactory(PosLightningEntity::new)
    //        .fireImmune(), "pos_lightning"));

    public static final EntityEntry<PosLightningEntity> POS_LIGHTNING_ENTITY =
            register("pos_lightning", PosLightningEntity::new, () -> PosLightningRender::new,
                    MobCategory.MISC, 4, 20, false, false, PosLightningEntity::build).register();


    private static final EntityType build(EntityType.Builder builder, String entityName) {
        ResourceLocation nameLoc = new ResourceLocation(CreateLightning.MODID, entityName);
        return (EntityType) builder.build(entityName);
    }

    private static <T extends Entity> CreateEntityBuilder<T, ?> register(String name, EntityType.EntityFactory<T> factory,
                                                                         NonNullSupplier<NonNullFunction<EntityRendererProvider.Context, EntityRenderer<? super T>>> renderer,
                                                                         MobCategory group, int range, int updateFrequency, boolean sendVelocity, boolean immuneToFire,
                                                                         NonNullConsumer<EntityType.Builder<T>> propertyBuilder) {
        String id = Lang.asId(name);
        return (CreateEntityBuilder<T, ?>) CreateLightning.registrate()
                .entity(id, factory, group)
                .properties(b -> b.setTrackingRange(range)
                        .setUpdateInterval(updateFrequency)
                        .setShouldReceiveVelocityUpdates(sendVelocity))
                .properties(propertyBuilder)
                .properties(b -> {
                    if (immuneToFire)
                        b.fireImmune();
                })
                .renderer(renderer);
    }

    public static void register() {}
}
