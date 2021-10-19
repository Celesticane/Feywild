package com.feywild.feywild.entity.mandragora;

import com.feywild.feywild.entity.base.MandragoraEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class TomatoMandragoraEntity extends MandragoraEntity {

    public TomatoMandragoraEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
        this.entityData.set(VARIANT, 4);
    }

    @Override
    public MandragoraVariant getVariation() {
        return MandragoraVariant.TOMATO;
    }
}
