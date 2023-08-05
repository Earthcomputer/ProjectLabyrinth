package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.earthcomputer.projectlabyrinth.block.LabyrinthBlocks;
import net.earthcomputer.projectlabyrinth.entity.LabyrinthEntities;
import net.earthcomputer.projectlabyrinth.item.LabyrinthItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class LabyrinthLanguageProvider extends LanguageProvider {
    public LabyrinthLanguageProvider(PackOutput output) {
        super(output, ProjectLabyrinth.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlock(LabyrinthBlocks.GAMER_CHAIR, "Gamer Chair");
        addItem(LabyrinthItems.GAMER_JUICE, "Gamer Juice");

        addBlock(LabyrinthBlocks.PACKED_SEAGRASS, "Packed Seagrass");

        addEntityType(LabyrinthEntities.SENTIENT_PISTON, "Sentient Piston");

        add("itemGroup.projectLabyrinth", "Project Labyrinth");
        add("entity.minecraft.villager.projectlabyrinth.gamer", "Gamer");
    }
}
