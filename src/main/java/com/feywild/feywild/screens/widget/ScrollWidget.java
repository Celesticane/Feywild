package com.feywild.feywild.screens.widget;

import com.feywild.feywild.FeywildMod;
import com.feywild.feywild.network.RequestItemSerializer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.noeppi_noeppi.libx.render.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ScrollWidget extends BookWidget {

    public ScrollWidget(Screen screen, int x, int y, int idx, ItemStack stack) {
        super(screen, x, y, idx, stack);
        WIDTH = 64;
        HEIGHT = 64;
    }

    @Override
    public void onPress() {
        FeywildMod.getNetwork().channel.sendToServer(new RequestItemSerializer.Message(this.idx, RequestItemSerializer.State.scrolls));
        this.screen.onClose();
    }


    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(FeywildMod.getInstance().modid, "textures/gui/begin_atlas.png");
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShaderTexture(0, getTexture());
        RenderHelper.resetColor();
        this.blit(poseStack, this.x - 20, this.y - 16, idx * WIDTH, 0, 64, 64);
        if (this.isHovered(mouseX, mouseY)) {
            this.setBlitOffset(this.getBlitOffset() + 10);
            this.blit(poseStack, this.x - 20, this.y - 16, idx * WIDTH, 64, 32, 32);
            this.setBlitOffset(this.getBlitOffset() - 10);
        }else
            Minecraft.getInstance().getItemRenderer().renderGuiItem(this.stack,this.x + 4,this.y + 4);
        }
}