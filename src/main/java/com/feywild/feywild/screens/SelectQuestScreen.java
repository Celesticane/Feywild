package com.feywild.feywild.screens;

import com.feywild.feywild.FeywildMod;
import com.feywild.feywild.network.quest.ConfirmQuestSerializer;
import com.feywild.feywild.quest.Alignment;
import com.feywild.feywild.quest.QuestDisplay;
import com.feywild.feywild.quest.util.SelectableQuest;
import com.feywild.feywild.screens.widget.QuestWidget;
import com.feywild.feywild.util.TextProcessor;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class SelectQuestScreen extends Screen {

    private final Alignment alignment;
    private final List<SelectableQuest> quests;

    public SelectQuestScreen(ITextComponent name, Alignment alignment, List<SelectableQuest> quests) {
        super(name);
        this.alignment = alignment;
        this.quests = ImmutableList.copyOf(quests);
    }

    @Override
    protected void init() {
        super.init();
        this.buttons.clear();
        for (int i = 0; i < quests.size(); i++) {
            this.addButton(new QuestWidget(20, 40 + ((QuestWidget.HEIGHT + 4) * i), this.alignment, this.quests.get(i)));
        }
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        drawTextLines(matrixStack, mouseX, mouseY);
    }

    private void drawTextLines(MatrixStack matrixStack, int mouseX, int mouseY) {
        if (minecraft != null) {
            drawString(matrixStack, minecraft.font, title, this.width / 2 - (minecraft.font.width(title) / 2), 10, 0xFFFFFF);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}