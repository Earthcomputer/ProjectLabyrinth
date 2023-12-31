package net.earthcomputer.projectlabyrinth.data;

import net.earthcomputer.projectlabyrinth.block.LabyrinthBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class LabyrinthLootTableProvider extends LootTableProvider {
    public LabyrinthLootTableProvider(PackOutput pOutput) {
        super(pOutput, Set.of(), List.of(new SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)));
    }

    private static class BlockLoot extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

        protected BlockLoot() {
            super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return LabyrinthBlocks.getKnownBlocks();
        }

        @Override
        protected void generate() {
            dropSelf(LabyrinthBlocks.GAMER_CHAIR.get());
            dropSelf(LabyrinthBlocks.PACKED_SEAGRASS.get());
        }
    }
}
