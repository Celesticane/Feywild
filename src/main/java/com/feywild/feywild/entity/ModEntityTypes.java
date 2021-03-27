package com.feywild.feywild.entity;

import com.feywild.feywild.FeywildMod;
import com.feywild.feywild.util.Registration;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.ResourceBundle;
public class ModEntityTypes {

    /* REGISTRATION FOR EACH ENTITY*/
    public static final RegistryObject<EntityType<SpringPixieEntity>> SPRING_PIXIE = Registration.ENTITIES.register("spring_pixie",
            () -> EntityType.Builder.create(SpringPixieEntity::new, EntityClassification.CREATURE)
    .size(1,1)
    .build(new ResourceLocation(FeywildMod.MOD_ID + "spring_pixie").toString()));

    public static final RegistryObject<EntityType<AutumnPixieEntity>> AUTUMN_PIXIE = Registration.ENTITIES.register("autumn_pixie",
            () -> EntityType.Builder.create(AutumnPixieEntity::new, EntityClassification.CREATURE)
                    .size(1,1)
                    .build(new ResourceLocation(FeywildMod.MOD_ID + "autumn_pixie").toString()));

    public static final RegistryObject<EntityType<SummerPixieEntity>> SUMMER_PIXIE = Registration.ENTITIES.register("summer_pixie",
            () -> EntityType.Builder.create(SummerPixieEntity::new, EntityClassification.CREATURE)
                    .size(1,1)
                    .build(new ResourceLocation(FeywildMod.MOD_ID + "summer_pixie").toString()));

    public static final RegistryObject<EntityType<WinterPixieEntity>> WINTER_PIXIE = Registration.ENTITIES.register("winter_pixie",
            () -> EntityType.Builder.create(WinterPixieEntity::new, EntityClassification.CREATURE)
                    .size(1,1)
                    .build(new ResourceLocation(FeywildMod.MOD_ID + "winter_pixie").toString()));

    public static void register() {}


    /* CUSTOM CONDITIONS */
    public static boolean spawnFey(EntityType<? extends Entity> entity, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.down()).isIn(Blocks.GRASS_BLOCK);
    }
}