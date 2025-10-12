package corundum.mulberry.content;

import corundum.mulberry.Mulberry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MBCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB,
            Mulberry.MODID
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MULBERRY_TAB = CREATIVE_MODE_TABS.register(
            "mulberry_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.mulberry"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> MBItems.METEORITE_INGOT.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        addItems(
                                output,

                                MBBlocks.METEORITE_ROCK,
                                MBBlocks.METEORITE_BLOCK,
                                MBBlocks.METEORITE_SLAG_BLOCK,

                                MBItems.METEORITE_INGOT,
                                MBItems.METEORITE_NUGGET,
                                MBItems.METEORITE_SLAG,
                                MBItems.METEORITE_PLATE,

                                MBItems.IRON_PLATE,
                                MBItems.STEEL_PLATE,
                                MBItems.ELECTRUM_PLATE,

                                MBItems.METEORITE_SWORD,
                                MBItems.METEORITE_PICKAXE,
                                MBItems.METEORITE_AXE,
                                MBItems.METEORITE_SHOVEL,
                                MBItems.METEORITE_HOE,

                                MBItems.BREEZE_POWDER

                        );
                    })
                    .build()
    );

    private static void addItems(Output output, ItemLike... items) {
        for (var item : items)
            output.accept(item);
    }
}