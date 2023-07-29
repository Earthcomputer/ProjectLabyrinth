package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LabyrinthBlockStateProvider extends BlockStateProvider {
    public LabyrinthBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ProjectLabyrinth.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(ProjectLabyrinth.GAMER_CHAIR_BLOCK.get(), models().getExistingFile(modLoc("gamer_chair")));
        simpleBlockItem(ProjectLabyrinth.GAMER_CHAIR_BLOCK.get(), models().getExistingFile(modLoc("gamer_chair")));
    }
}
