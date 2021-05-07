package com.feywild.feywild.block.trees;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class SummerTreeSapling extends BaseSapling {

    public SummerTreeSapling() {
        super(SummerTree::new, Properties.copy(Blocks.OAK_SAPLING));
    }


    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {

        super.performBonemeal(worldIn, rand, pos, state);


        if (state.getValue(STAGE) == 1) {

            if (!worldIn.isClientSide()) {
                //x
                int sizeFlowerPatch = 4;

                //-4 to 4
                for (int x = -sizeFlowerPatch; x <= sizeFlowerPatch; x++) {
                    for (int z = -sizeFlowerPatch; z <= sizeFlowerPatch; z++) {
                        if (rand.nextDouble() < 0.2) {
                            //Only if its a Dirt Block
                            if (worldIn.getBlockState(new BlockPos(pos.getX() - x, pos.getY() - 1, pos.getZ() - z)).is(Blocks.GRASS_BLOCK)) {

                                worldIn.setBlockAndUpdate(new BlockPos(pos.getX() - x, pos.getY(), pos.getZ() - z), getBlocks(rand));
                            }
                        }
                    }
                }
            }
        }
    }

    public BlockState getBlocks(Random random) {

        switch (random.nextInt(10)) {
            case 0: return Blocks.OXEYE_DAISY.defaultBlockState();
            case 1: return Blocks.DANDELION.defaultBlockState();
            case 2: return Blocks.POPPY.defaultBlockState();
            case 4: return Blocks.ALLIUM.defaultBlockState();
            case 5: return Blocks.CORNFLOWER.defaultBlockState();
            //case 6: return Blocks.SUNFLOWER.getDefaultState();
            default: return Blocks.GRASS.defaultBlockState();
        }


    }

}
