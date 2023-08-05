package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.earthcomputer.projectlabyrinth.item.LabyrinthItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LabyrinthItemModelProvider extends ItemModelProvider {
    public LabyrinthItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ProjectLabyrinth.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(LabyrinthItems.GAMER_JUICE.get());
    }
}
