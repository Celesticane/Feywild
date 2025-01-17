package com.feywild.feywild.entity.goals.pixie;

import com.feywild.feywild.effects.ModEffects;
import com.feywild.feywild.entity.base.Pixie;
import com.feywild.feywild.quest.player.QuestData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class WindWalkGoal extends AddShieldGoal {

    private final Pixie entity;

    public WindWalkGoal(Pixie entity) {
        super(entity, ModEffects.windWalk);
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        Player owning = this.entity.getOwningPlayer();
        if (owning instanceof ServerPlayer serverPlayer) {
            return this.entity.level.random.nextFloat() < 0.01f && QuestData.get(serverPlayer).getAlignment() == entity.alignment
                    && this.entity.getAbility().equals(Ability.WIND_WALK);
        } else {
            return false;
        }
    }
}
