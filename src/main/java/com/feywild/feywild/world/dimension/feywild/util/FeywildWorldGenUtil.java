package com.feywild.feywild.world.dimension.feywild.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class FeywildWorldGenUtil {

    public static <T extends FeatureConfiguration> boolean generateTries(FeaturePlaceContext<T> context, int tries, BiFunction<FeaturePlaceContext<T>, HorizontalPos, Boolean> placeFunc) {
        boolean success = false;
        for (int i = 0; i < tries; i++) {
            if (placeFunc.apply(context, new HorizontalPos(context.origin().getX() + context.random().nextInt(16), context.origin().getZ() + context.random().nextInt(16)))) {
                success = true;
            }
        }
        return success;
    }

    public static BlockPos highestFreeBlock(LevelAccessor level, HorizontalPos hor, Predicate<BlockState> passthrough) {
        return hor.trace(level.getMaxBuildHeight(), level.getMinBuildHeight(), pos -> !level.isEmptyBlock(pos) && !passthrough.test(level.getBlockState(pos)));
    }

    public static BlockPos lowestFreeBlock(LevelAccessor level, HorizontalPos hor, Predicate<BlockState> passthrough) {
        return hor.trace(level.getMinBuildHeight(), level.getMaxBuildHeight(), pos -> !level.isEmptyBlock(pos) && !passthrough.test(level.getBlockState(pos)));
    }

    public static boolean passReplaceableAndLeaves(BlockState state) {
        return (state.getMaterial().isReplaceable() && state.getMaterial() != Material.WATER && state.getMaterial() != Material.LAVA) || state.getMaterial() == Material.LEAVES;
    }

    public static Predicate<BlockState> passNoCrops(Predicate<BlockState> test) {
        return state -> test.test(state) && !(state.getBlock() instanceof CropBlock);
    }

}
