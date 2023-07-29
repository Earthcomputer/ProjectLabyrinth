package net.earthcomputer.projectlabyrinth.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GamerChairBlock extends HorizontalDirectionalBlock {
    private static final VoxelShape BASE_SHAPE = Block.box(2, 0, 2, 14, 7.5, 14);
    private static final VoxelShape BACK_SHAPE_EAST = Block.box(2, 7.5, 2, 3.5, 16, 14);
    private static final VoxelShape SHAPE_EAST = Shapes.or(BASE_SHAPE, BACK_SHAPE_EAST);
    private static final VoxelShape BACK_SHAPE_WEST = Block.box(12.5, 7.5, 2, 14, 16, 14);
    private static final VoxelShape SHAPE_WEST = Shapes.or(BASE_SHAPE, BACK_SHAPE_WEST);
    private static final VoxelShape BACK_SHAPE_NORTH = Block.box(2, 7.5, 12.5, 14, 16, 14);
    private static final VoxelShape SHAPE_NORTH = Shapes.or(BASE_SHAPE, BACK_SHAPE_NORTH);
    private static final VoxelShape BACK_SHAPE_SOUTH = Block.box(2, 7.5, 2, 14, 16, 3.5);
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(BASE_SHAPE, BACK_SHAPE_SOUTH);

    public GamerChairBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> throw new IllegalStateException();
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
