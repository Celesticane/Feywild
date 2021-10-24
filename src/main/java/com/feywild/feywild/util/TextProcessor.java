package com.feywild.feywild.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.text.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TextComponent;

public class TextProcessor {
    
    private static final Pattern CONTROL_PATTERN = Pattern.compile("\\$\\(((?:(?:\\w+|#[0-9A-Fa-f]{6})(?:;(?:\\w+|#[0-9A-Fa-f]{6}))*)?)\\)");
    
    public static List<Component> process(Component text) {
        return process(text.getString());
    }
    
    public static List<Component> process(String text) {
        //noinspection UnstableApiUsage
        return Arrays.stream(text.split("\\$\\((?:n|newline)\\)"))
                .map(String::trim)
                .map(TextProcessor::processLine)
                .collect(ImmutableList.toImmutableList());
    }
    
    public static Component processLine(Component line) {
        return processLine(line.getString());
    }
    
    public static Component processLine(String line) {
        if (line.isEmpty()) return new TextComponent("");
        Matcher m = CONTROL_PATTERN.matcher(line);
        MutableComponent tc = new TextComponent("");
        int idx = 0;
        Style style = Style.EMPTY;
        while (m.find()) {
            if (idx < m.start()) {
                tc.append(new TextComponent(line.substring(idx, m.start())).withStyle(style));
            }
            idx = m.end();
            String cmd = m.group(1).trim();
            if (cmd.isEmpty()) {
                style = Style.EMPTY;
            } else {
                for (String part : cmd.split(";")) {
                    if (part.startsWith("#")) {
                        try {
                            int color = Integer.parseInt(part.substring(1), 16);
                            style = style.withColor(TextColor.fromRgb(color));
                        } catch (NumberFormatException e) {
                            //
                        }
                    } else if ("b".equalsIgnoreCase(part.trim()) || "bold".equalsIgnoreCase(part.trim())) {
                        style = style.withBold(true);
                    } else if ("i".equalsIgnoreCase(part.trim()) || "italic".equalsIgnoreCase(part.trim())) {
                        style = style.withItalic(true);
                    } else if ("u".equalsIgnoreCase(part.trim()) || "underline".equalsIgnoreCase(part.trim())) {
                        style = style.withUnderlined(true);
                    } else if ("s".equalsIgnoreCase(part.trim()) || "strikethrough".equalsIgnoreCase(part.trim())) {
                        style = style.setStrikethrough(true);
                    } else if ("spring".equalsIgnoreCase(part.trim())) {
                        style = style.withColor(TextColor.fromRgb(0x66cc99));
                    } else if ("summer".equalsIgnoreCase(part.trim())) {
                        style = style.withColor(TextColor.fromRgb(0xffcc00));
                    } else if ("autumn".equalsIgnoreCase(part.trim())) {
                        style = style.withColor(TextColor.fromRgb(0xcc3333));
                    } else if ("winter".equalsIgnoreCase(part.trim())) {
                        style = style.withColor(TextColor.fromRgb(0x66ccff));
                    } else {
                        for (ChatFormatting tf : ChatFormatting.values()) {
                            if (tf.getName().equalsIgnoreCase(part.trim())) {
                                style = style.withColor(tf);
                            }
                        }
                    }
                }
            }
        }
        if (idx < line.length()) {
            tc.append(new TextComponent(line.substring(idx)).withStyle(style));
        }
        return tc;
    }
}
