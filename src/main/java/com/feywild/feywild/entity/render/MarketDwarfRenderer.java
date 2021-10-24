package com.feywild.feywild.entity.render;

import com.feywild.feywild.entity.DwarfBlacksmith;
import com.feywild.feywild.entity.model.DwarfBlacksmithModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class MarketDwarfRenderer extends GeoEntityRenderer<DwarfBlacksmith> {

    public MarketDwarfRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new DwarfBlacksmithModel());
        this.shadowRadius = 0.8F;
    }

    @Override
    public RenderType getRenderType(DwarfBlacksmith animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexConsumer, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }

}
