package net.earthcomputer.projectlabyrinth;

import com.google.common.collect.ImmutableSet;
import com.mojang.logging.LogUtils;
import net.earthcomputer.projectlabyrinth.block.GamerChairBlock;
import net.earthcomputer.projectlabyrinth.data.*;
import net.earthcomputer.projectlabyrinth.item.DrinkItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(ProjectLabyrinth.MODID)
public class ProjectLabyrinth {
    public static final String MODID = "projectlabyrinth";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<PoiType> POIS = DeferredRegister.create(ForgeRegistries.POI_TYPES, MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<Block> GAMER_CHAIR_BLOCK = BLOCKS.register("gamer_chair", () -> new GamerChairBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.WOOL).strength(0.8f)));
    public static final RegistryObject<Item> GAMER_CHAIR_ITEM = ITEMS.register("gamer_chair", () -> new BlockItem(GAMER_CHAIR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<PoiType> GAMER_CHAIR_POI = POIS.register("gamer_chair", () -> new PoiType(ImmutableSet.copyOf(GAMER_CHAIR_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<Block> PACKED_SEAGRASS_BLOCK = BLOCKS.register("packed_seagrass", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Item> PACKED_SEAGRASS_ITEM = ITEMS.register("packed_seagrass", () -> new BlockItem(PACKED_SEAGRASS_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> GAMER_JUICE_ITEM = ITEMS.register("gamer_juice", () -> new DrinkItem(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(1).saturationMod(2f).build())));

    public static final RegistryObject<CreativeModeTab> LABYRINTH_TAB = CREATIVE_MODE_TABS.register("labyrinth_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup.projectLabyrinth"))
            .icon(() -> GAMER_JUICE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(GAMER_CHAIR_ITEM.get());
                output.accept(GAMER_JUICE_ITEM.get());
                output.accept(PACKED_SEAGRASS_ITEM.get());
            }).build());

    public static final RegistryObject<VillagerProfession> GAMER_PROFESSION = VILLAGER_PROFESSIONS.register("gamer", () -> new VillagerProfession("gamer", poi -> poi.is(GAMER_CHAIR_POI.getKey()), poi -> poi.is(GAMER_CHAIR_POI.getKey()), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN));

    public ProjectLabyrinth() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        POIS.register(modEventBus);
        VILLAGER_PROFESSIONS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    public void gatherData(GatherDataEvent event) {
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

    @SubscribeEvent
    public void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == GAMER_PROFESSION.get()) {
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.COOKIE, 6), new ItemStack(Items.EMERALD), 16, 1, 0.05f));
            event.getTrades().get(1).add(new BasicItemListing(8, new ItemStack(ProjectLabyrinth.GAMER_JUICE_ITEM.get()), 16, 1));
        }
    }

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Villager villager) {
            if (!villager.level().isClientSide && villager.getVillagerData().getProfession() == GAMER_PROFESSION.get() && villager.isSunBurnTick()) {
                villager.setSecondsOnFire(8);
            }
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
