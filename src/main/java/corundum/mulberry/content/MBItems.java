package corundum.mulberry.content;

import corundum.mulberry.Mulberry;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MBItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mulberry.MODID);

    public static final DeferredItem<Item> METEORITE_INGOT = ITEMS.registerSimpleItem(
            "meteorite_ingot",
            new Item.Properties()
    );

    public static final DeferredItem<Item> METEORITE_NUGGET = ITEMS.registerSimpleItem(
            "meteorite_nugget",
            new Item.Properties()
    );

    public static final DeferredItem<Item> METEORITE_SLAG = ITEMS.registerSimpleItem(
            "meteorite_slag",
            new Item.Properties()
    );

    public static final DeferredItem<Item> METEORITE_PLATE = ITEMS.registerSimpleItem(
            "meteorite_plate",
            new Item.Properties()
    );
    public static final DeferredItem<Item> PYRITE = ITEMS.registerSimpleItem(
            "pyrite",
            new Item.Properties()
    );

    public static final DeferredItem<Item> IRON_PLATE = ITEMS.registerSimpleItem(
            "iron_plate",
            new Item.Properties()
    );

    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.registerSimpleItem(
            "steel_plate",
            new Item.Properties()
    );

    public static final DeferredItem<Item> ELECTRUM_PLATE = ITEMS.registerSimpleItem(
            "electrum_plate",
            new Item.Properties()
    );

    public static final DeferredItem<Item> BREEZE_POWDER = ITEMS.registerSimpleItem(
            "breeze_powder",
            new Item.Properties()
    );

    public static final DeferredItem<SwordItem> METEORITE_SWORD = ITEMS.register("meteorite_sword",
            () -> new SwordItem(MBToolTiers.METEORITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(MBToolTiers.METEORITE, 3, -2.4f))));
    public static final DeferredItem<PickaxeItem> METEORITE_PICKAXE = ITEMS.register("meteorite_pickaxe",
            () -> new PickaxeItem(MBToolTiers.METEORITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(MBToolTiers.METEORITE, 1.0F, -2.8f))));
    public static final DeferredItem<ShovelItem> METEORITE_SHOVEL = ITEMS.register("meteorite_shovel",
            () -> new ShovelItem(MBToolTiers.METEORITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(MBToolTiers.METEORITE, 1.5F, -3.0f))));
    public static final DeferredItem<AxeItem> METEORITE_AXE = ITEMS.register("meteorite_axe",
            () -> new AxeItem(MBToolTiers.METEORITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(MBToolTiers.METEORITE, 5.0F, -3.0f))));
    public static final DeferredItem<HoeItem> METEORITE_HOE = ITEMS.register("meteorite_hoe",
            () -> new HoeItem(MBToolTiers.METEORITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(MBToolTiers.METEORITE, -3.0F, 0.0F))));

    public static final DeferredItem<ArmorItem> METEORITE_HELMET = ITEMS.register("meteorite_helmet",
            () -> new ArmorItem(MBArmorMaterials.METEORITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<ArmorItem> METEORITE_CHESTPLATE = ITEMS.register("meteorite_chestplate",
            () -> new ArmorItem(MBArmorMaterials.METEORITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<ArmorItem> METEORITE_LEGGINGS = ITEMS.register("meteorite_leggings",
            () -> new ArmorItem(MBArmorMaterials.METEORITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<ArmorItem> METEORITE_BOOTS = ITEMS.register("meteorite_boots",
            () -> new ArmorItem(MBArmorMaterials.METEORITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
}