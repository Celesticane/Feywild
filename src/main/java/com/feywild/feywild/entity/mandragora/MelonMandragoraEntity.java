package com.feywild.feywild.entity.mandragora;

import com.feywild.feywild.entity.base.MandragoraEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class MelonMandragoraEntity extends MandragoraEntity {

    public MelonMandragoraEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
        this.entityData.set(VARIANT, 0);
    }

    @Override
    public MandragoraVariant getVariation() {
        return MandragoraVariant.MELON;
    }
}
