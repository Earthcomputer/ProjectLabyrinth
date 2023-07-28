package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class LabyrinthLanguageProvider extends LanguageProvider {
    public LabyrinthLanguageProvider(PackOutput output) {
        super(output, ProjectLabyrinth.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(ProjectLabyrinth.GAMER_JUICE_ITEM, "Gamer Juice");

        add("itemGroup.projectLabyrinth", "Project Labyrinth");
    }
}