package net.earthcomputer.projectlabyrinth;

import net.earthcomputer.projectlabyrinth.block.LabyrinthBlocks;
import net.earthcomputer.projectlabyrinth.block.LabyrinthPOIs;
import net.earthcomputer.projectlabyrinth.entity.LabyrinthEntities;
import net.earthcomputer.projectlabyrinth.entity.LabyrinthVillagerProfessions;
import net.earthcomputer.projectlabyrinth.item.LabyrinthItems;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(ProjectLabyrinth.MODID)
public class ProjectLabyrinth {
    public static final String MODID = "projectlabyrinth";

    public ProjectLabyrinth() {
        LabyrinthBlocks.register();
        LabyrinthItems.register();
        LabyrinthPOIs.register();
        LabyrinthVillagerProfessions.register();
        LabyrinthEntities.register();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
