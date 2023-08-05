package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.earthcomputer.projectlabyrinth.block.LabyrinthBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LabyrinthBlockStateProvider extends BlockStateProvider {
    public LabyrinthBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ProjectLabyrinth.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(LabyrinthBlocks.GAMER_CHAIR.get(), models().getExistingFile(modLoc("gamer_chair")));
        simpleBlockItem(LabyrinthBlocks.GAMER_CHAIR.get(), models().getExistingFile(modLoc("gamer_chair")));

        simpleBlockWithItem(LabyrinthBlocks.PACKED_SEAGRASS.get(), models().cubeAll("packed_seagrass", modLoc("block/packed_seagrass")));
    }
}
