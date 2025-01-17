package com.feywild.feywild.screens.widget;

import com.feywild.feywild.FeywildMod;
import com.feywild.feywild.network.RequestItemMessage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.moddingx.libx.render.RenderHelper;

import javax.annotation.Nonnull;

public class BookWidget extends Button {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;

    protected final Screen screen;
    protected final int idx;
    protected final ItemStack stack;

    public BookWidget(Screen screen, int x, int y, int idx, ItemStack stack) {
        super(x, y, WIDTH, HEIGHT, stack.getDisplayName(), b -> {});
        this.screen = screen;
        this.idx = idx;
        this.stack = stack;
    }

    public ItemStack getStack() {
        return this.stack;
    }

    @Override
    public void onPress() {
        super.onPress();
        FeywildMod.getNetwork().channel.sendToServer(new RequestItemMessage(RequestItemMessage.ScreenType.BOOKS, this.idx));
        this.screen.onClose();
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation(FeywildMod.getInstance().modid, "textures/gui/librarian_gui.png");
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        RenderHelper.resetColor();
        RenderSystem.setShaderTexture(0, getTexture());
        this.blit(poseStack, this.x, this.y, 0, 0, 25, 25);
        if (this.isHovered(mouseX, mouseY)) {
            this.setBlitOffset(this.getBlitOffset() + 10);
            this.blit(poseStack, this.x, this.y, 25, 0, 25, 25);
            this.setBlitOffset(this.getBlitOffset() - 10);
        }
        Minecraft.getInstance().getItemRenderer().renderGuiItem(this.stack, this.x + 4, this.y + 4);
    }

    public boolean isHovered(int x, int y) {
        return this.x <= x && this.x + WIDTH >= x && this.y <= y && this.y + HEIGHT >= y;
    }
}
