package com.feywild.feywild.entity;

import com.feywild.feywild.entity.base.MandragoraEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class PotatoMandragoraEntity extends MandragoraEntity {

    protected PotatoMandragoraEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
        this.entityData.set(VARIANT, 2);
    }

    @Override
    public MandragoraVariant getVariation() {
        return MandragoraVariant.POTATO;
    }
}
