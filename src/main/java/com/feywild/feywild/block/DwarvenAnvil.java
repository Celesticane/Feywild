package com.feywild.feywild.block;

import com.feywild.feywild.block.entity.FeyAltarBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.Objects;
import java.util.Random;

public class DwarvenAnvil extends Block {

    Random random = new Random();


    //Constructor
    public DwarvenAnvil() {
        super(AbstractBlock.Properties.create(Material.ANVIL)
                .hardnessAndResistance(3f,10f)
                .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().sound(SoundType.ANVIL));
    }

    // This is the black thing that surrounds the block and that prevents it from making blocks around it invisible
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.create(0.01,0.01,0.01,0.99,0.99,0.99);
    }
    
}