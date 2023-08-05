package net.earthcomputer.projectlabyrinth.item;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.earthcomputer.projectlabyrinth.block.LabyrinthBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class LabyrinthItems {
    private LabyrinthItems() {
    }

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectLabyrinth.MODID);

    public static final RegistryObject<Item> GAMER_CHAIR = ITEMS.register("gamer_chair", () -> new BlockItem(LabyrinthBlocks.GAMER_CHAIR.get(), new Item.Properties()));
    public static final RegistryObject<Item> PACKED_SEAGRASS = ITEMS.register("packed_seagrass", () -> new BlockItem(LabyrinthBlocks.PACKED_SEAGRASS.get(), new Item.Properties()));
    public static final RegistryObject<Item> GAMER_JUICE = ITEMS.register("gamer_juice", () -> new DrinkItem(new Item.Properties().food(new FoodProperties.Builder()
        .nutrition(1).saturationMod(2f).build())));

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        DeferredRegister<CreativeModeTab> creativeModeTabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProjectLabyrinth.MODID);
        creativeModeTabs.register(FMLJavaModLoadingContext.get().getModEventBus());
        creativeModeTabs.register("labyrinth_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup.projectLabyrinth"))
            .icon(() -> GAMER_JUICE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(GAMER_CHAIR.get());
                output.accept(PACKED_SEAGRASS.get());
                output.accept(GAMER_JUICE.get());
            }).build());
    }
}
