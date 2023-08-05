package net.earthcomputer.projectlabyrinth.entity;

import com.google.common.collect.ImmutableSet;
import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.earthcomputer.projectlabyrinth.block.LabyrinthPOIs;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class LabyrinthVillagerProfessions {
    private LabyrinthVillagerProfessions() {
    }

    private static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, ProjectLabyrinth.MODID);

    public static final RegistryObject<VillagerProfession> GAMER = VILLAGER_PROFESSIONS.register("gamer", () -> new VillagerProfession("gamer", poi -> poi.is(LabyrinthPOIs.GAMER_CHAIR.getKey()), poi -> poi.is(LabyrinthPOIs.GAMER_CHAIR.getKey()), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN));

    public static void register() {
        VILLAGER_PROFESSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
