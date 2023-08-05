package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectLabyrinth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class LabyrinthDataGen {
    private LabyrinthDataGen() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), (DataProvider.Factory<LabyrinthBlockStateProvider>) output -> new LabyrinthBlockStateProvider(output, efh));
        gen.addProvider(event.includeClient(), (DataProvider.Factory<LabyrinthItemModelProvider>) output -> new LabyrinthItemModelProvider(output, efh));
        gen.addProvider(event.includeClient(), (DataProvider.Factory<LabyrinthLanguageProvider>) LabyrinthLanguageProvider::new);

        gen.addProvider(event.includeServer(), (DataProvider.Factory<LabyrinthRecipeProvider>) LabyrinthRecipeProvider::new);
        gen.addProvider(event.includeServer(), (DataProvider.Factory<LabyrinthLootTableProvider>) LabyrinthLootTableProvider::new);
        gen.addProvider(event.includeServer(), (DataProvider.Factory<LabyrinthBlockTagsProvider>) output -> new LabyrinthBlockTagsProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        gen.addProvider(event.includeServer(), (DataProvider.Factory<LabyrinthPoiTypeTagsProvider>) output -> new LabyrinthPoiTypeTagsProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
    }
}
