package corundum.mulberry;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableList;
import com.mojang.logging.LogUtils;
import corundum.mulberry.content.MBBlocks;
import corundum.mulberry.content.MBItems;
import corundum.mulberry.content.MBCreativeTabs;
import corundum.mulberry.data.MBDatagen;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Mulberry.MODID)
public class Mulberry {
    public static final String MODID = "mulberry";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final ImmutableList<DeferredRegister<?>> REGISTRIES = ImmutableList.of(
            MBBlocks.BLOCKS,
            MBItems.ITEMS,
            MBCreativeTabs.CREATIVE_MODE_TABS
    );

    public Mulberry(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        for (var registry : REGISTRIES)
            registry.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("she mull on my ber til i ry");
    }

    @EventBusSubscriber(modid = Mulberry.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class ClientModEvents {
        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Mull these berries lmao");
        }
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}