package com.feywild.feywild.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public abstract class BasePixieRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {

    protected BasePixieRenderer(EntityRendererManager renderManager, AnimatedGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowSize = 0.2F;
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
        generateParticles(entity);
    }

    private void generateParticles(Entity entity) {
        World world = entity.world;

        if (world.rand.nextInt(11) == 0) {
            world.addParticle(
                    getParticleType(),
                    entity.getPosX() + (Math.random() - 0.5),
                    entity.getPosY() + 1 + (Math.random() - 0.5),
                    entity.getPosZ() + (Math.random() - 0.5),
                    0,
                    -0.1,
                    0);
        }

    }

    protected abstract BasicParticleType getParticleType();
}

