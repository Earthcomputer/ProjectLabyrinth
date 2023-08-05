package net.earthcomputer.projectlabyrinth.entity;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ProjectLabyrinth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class LabyrinthEntities {
    private LabyrinthEntities() {
    }

    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ProjectLabyrinth.MODID);

    public static final RegistryObject<EntityType<SentientPistonEntity>> SENTIENT_PISTON = ENTITIES.register("sentient_piston", () -> EntityType.Builder.of(SentientPistonEntity::new, MobCategory.MONSTER).sized(0.98f, 1.98f).clientTrackingRange(8).build("projectlabyrinth:sentient_piston"));

    public static void register() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public static void registerMobAttributes(EntityAttributeCreationEvent event) {
        event.put(SENTIENT_PISTON.get(), SentientPistonEntity.createAttributes().build());
    }

    @Mod.EventBusSubscriber(modid = ProjectLabyrinth.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class ClientEvents {
        private ClientEvents() {
        }

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(LabyrinthEntities.SENTIENT_PISTON.get(), SentientPistonRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(SentientPistonModel.LAYER_LOCATION, SentientPistonModel::createBodyLayer);
        }
    }
}
