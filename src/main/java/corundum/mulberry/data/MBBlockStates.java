package corundum.mulberry.data;

import corundum.mulberry.Mulberry;
import corundum.mulberry.content.MBBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MBBlockStates extends BlockStateProvider {
    public MBBlockStates(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, Mulberry.MODID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlock(MBBlocks.METEORITE_ROCK.get());
        simpleBlock(MBBlocks.METEORITE_BLOCK.get());
        simpleBlock(MBBlocks.METEORITE_SLAG_BLOCK.get());
        simpleBlock(MBBlocks.PYRITE_ORE.get());
        simpleBlock(MBBlocks.PYRITE_BLOCK.get());



    }
}