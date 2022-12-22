package com.betaforge1.createlightning;

import com.betaforge1.createlightning.content.CreateLightningItemGroup;
import com.mojang.logging.LogUtils;
import com.simibubi.create.AllEntityTypes;
import com.simibubi.create.Create;
import com.simibubi.create.content.CreateItemGroup;
import com.simibubi.create.content.logistics.block.redstone.RedstoneLinkBlock;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.nullness.NonNullSupplier;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.redstone.Redstone;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

//mixin: LightningRodBlock.animateTick
//       LightningRodBlock.onLightningStrike
//       LightningBolt.powerLightningRod
//AnimateTickMixin

@Mod(CreateLightning.MODID)
public class CreateLightning {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final NonNullSupplier <CreateRegistrate> registrate = CreateRegistrate.lazy(CreateLightning.MODID);

    public static final String MODID = "createlightning";

    public static final CreativeModeTab BASE_CREATIVE_TAB = new CreateLightningItemGroup();

    public CreateLightning() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        MinecraftForge.EVENT_BUS.register(this);

        CLEntityTypes.DEF_REG.register(FMLJavaModLoadingContext.get().getModEventBus());
        CLItems.register();
        CLBlocks.register();
        CLEntityTypes.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("createlightning loaded.");
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
        //InterModComms.sendTo("cmsrenewable", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("createlightning started in the server");

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry (final RegistryEvent.Register<Block> blockRegistryEvent) {
            // Register a new block here

        }
    }

    public static CreateRegistrate registrate() {
        return registrate.get();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
