package corundum.mulberry.data;

import corundum.mulberry.Mulberry;
import corundum.mulberry.content.MBBlocks;
import corundum.mulberry.content.MBItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class MBItemModels extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public MBItemModels(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, Mulberry.MODID, fileHelper);
    }

    @Override
    protected void registerModels() {
        // Block items
        simpleBlockItems(
                MBBlocks.METEORITE_ROCK,
                MBBlocks.METEORITE_BLOCK,
                MBBlocks.METEORITE_SLAG_BLOCK,
                MBBlocks.PYRITE_ORE,
                MBBlocks.PYRITE_BLOCK


        );

        // Basic items
        basicItems(
                MBItems.METEORITE_INGOT,
                MBItems.METEORITE_NUGGET,

                MBItems.METEORITE_SLAG,
                MBItems.METEORITE_PLATE,
                MBItems.IRON_PLATE,
                MBItems.STEEL_PLATE,
                MBItems.ELECTRUM_PLATE,

                MBItems.PYRITE,


                MBItems.BREEZE_POWDER
        );

        // Tools
        handheldItems(
                MBItems.METEORITE_SWORD,
                MBItems.METEORITE_PICKAXE,
                MBItems.METEORITE_AXE,
                MBItems.METEORITE_SHOVEL,
                MBItems.METEORITE_HOE,
                MBItems.CHISEL

        );

        trimmedArmorItem(MBItems.METEORITE_HELMET);
        trimmedArmorItem(MBItems.METEORITE_CHESTPLATE);
        trimmedArmorItem(MBItems.METEORITE_LEGGINGS);
        trimmedArmorItem(MBItems.METEORITE_BOOTS);
    }

    private void simpleBlockItems(DeferredBlock<?>... blocks) {
        for (var block : blocks)
            withExistingParent(
                    block.getId().toString(),
                    modLoc("block/" + block.getId().getPath())
            );
    }

    private void basicItems(ItemLike... items) {
        for (var item : items)
            basicItem(item.asItem());
    }

    private void handheldItems(DeferredItem<?>... items) {
        for (var item : items)
            withExistingParent(item.getId().getPath(),
                    ResourceLocation.parse("item/handheld")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(Mulberry.MODID, "item/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = Mulberry.MODID;

        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);


                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }
}