package net.earthcomputer.projectlabyrinth.block;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class LabyrinthBlocks {
    private LabyrinthBlocks() {
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectLabyrinth.MODID);

    public static final RegistryObject<Block> GAMER_CHAIR = BLOCKS.register("gamer_chair", () -> new GamerChairBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.WOOL).strength(0.8f)));
    public static final RegistryObject<Block> PACKED_SEAGRASS = BLOCKS.register("packed_seagrass", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));

    public static Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
