package net.earthcomputer.projectlabyrinth.entity;

import net.earthcomputer.projectlabyrinth.ProjectLabyrinth;
import net.earthcomputer.projectlabyrinth.item.LabyrinthItems;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectLabyrinth.MODID)
public final class GamerVillager {
    private GamerVillager() {
    }

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == LabyrinthVillagerProfessions.GAMER.get()) {
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.COOKIE, 6), new ItemStack(Items.EMERALD), 16, 1, 0.05f));
            event.getTrades().get(1).add(new BasicItemListing(8, new ItemStack(LabyrinthItems.GAMER_JUICE.get()), 16, 1));
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Villager villager) {
            if (!villager.level().isClientSide && villager.getVillagerData().getProfession() == LabyrinthVillagerProfessions.GAMER.get() && villager.isSunBurnTick()) {
                villager.setSecondsOnFire(8);
            }
        }
    }
}
