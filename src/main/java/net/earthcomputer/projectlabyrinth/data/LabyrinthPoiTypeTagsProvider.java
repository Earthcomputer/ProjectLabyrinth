package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.earthcomputer.projectlabyrinth.block.LabyrinthPOIs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LabyrinthPoiTypeTagsProvider extends PoiTypeTagsProvider {
    public LabyrinthPoiTypeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, ProjectLabyrinth.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(LabyrinthPOIs.GAMER_CHAIR.getKey());
    }
}
