package com.feywild.feywild.item;

import com.feywild.feywild.FeywildMod;
import com.feywild.feywild.entity.AutumnPixieEntity;
import com.feywild.feywild.entity.ModEntityTypes;
import com.feywild.feywild.util.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class SummoningScrollAutumnPixie extends Item {

    public SummoningScrollAutumnPixie() {
        super(new Item.Properties().group(FeywildMod.FEYWILD_TAB));
    }


    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if(!context.getWorld().isRemote){
            AutumnPixieEntity entity = new AutumnPixieEntity(ModEntityTypes.AUTUMN_PIXIE.get(),context.getWorld());
            entity.setPosition(context.getHitVec().getX(), context.getHitVec().getY(), context.getHitVec().getZ());
            context.getWorld().addEntity(entity);
            context.getPlayer().getHeldItem(context.getHand()).shrink(1);
        }
        return ActionResultType.SUCCESS;
    }


    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag){

        if(KeyboardHelper.isHoldingShift()){

            tooltip.add(new StringTextComponent("A summoning scroll for a Autumn Pixie"));
        }
        else {
            tooltip.add(new StringTextComponent("Hold "+ "\u00A7e" + "SHIFT" + "\u00A77" + " for more information."));
        }

        super.addInformation(stack, world, tooltip, flag);
    }
}