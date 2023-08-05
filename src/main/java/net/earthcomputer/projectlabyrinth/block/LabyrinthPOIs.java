package net.earthcomputer.projectlabyrinth.block;

import com.google.common.collect.ImmutableSet;
import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class LabyrinthPOIs {
    private LabyrinthPOIs() {
    }

    private static final DeferredRegister<PoiType> POIS = DeferredRegister.create(ForgeRegistries.POI_TYPES, ProjectLabyrinth.MODID);

    public static final RegistryObject<PoiType> GAMER_CHAIR = POIS.register("gamer_chair", () -> new PoiType(ImmutableSet.copyOf(LabyrinthBlocks.GAMER_CHAIR.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static void register() {
        POIS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
