package me.neptune.api.util.render;

import com.mojang.blaze3d.systems.RenderSystem;
import me.neptune.api.interfaces.Minecraftable;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;

public class RenderMethods implements Minecraftable {

    public static void enable2D() {
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.depthMask(true);
    }

    public static void disable2D() {
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(false);
    }

    public static void color(int hex) {
        float alpha = (hex >> 24 & 255) / 255.0F;
        float red = (hex >> 16 & 255) / 255.0F;
        float green = (hex >> 8 & 255) / 255.0F;
        float blue = (hex & 255) / 255.0F;
        RenderSystem.setShaderColor(red, green, blue, alpha);
    }

    private static void draw(DrawContext context, float x, float y, float width, float height, int color) {
        Matrix4f matrix4f = context.getMatrices().peek().getPositionMatrix();
        float i;
        if (x < width) {
            i = x;
            x = width;
            width = i;
        }

        if (y < height) {
            i = y;
            y = height;
            height = i;
        }

        float alpha = (float) ColorHelper.Argb.getAlpha(color) / 255.0F;
        float red = (float) ColorHelper.Argb.getRed(color) / 255.0F;
        float green = (float) ColorHelper.Argb.getGreen(color) / 255.0F;
        float blue = (float) ColorHelper.Argb.getBlue(color) / 255.0F;
        VertexConsumer vertexConsumer = context.getVertexConsumers().getBuffer(RenderLayer.getGui());
        vertexConsumer.vertex(matrix4f, x, y, 0).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, x, height, 0).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, width, height, 0).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, width, y, 0).color(red, green, blue, alpha).next();
        context.getVertexConsumers().draw();
    }

    public static void drawRect(DrawContext context, float x, float y, float width, float height, int color) {
        enable2D();
        draw(context, x, y, width, height, color);
        disable2D();
    }

    public static void drawBorderedRect(DrawContext context, float x, float y, float width, float height, int color, int outlineColor) {
        enable2D();
        x *= 2;
        width *= 2;
        y *= 2;
        height *= 2;
        context.getMatrices().scale(0.5F, 0.5F, 0.5F);
        drawVerticalLine(context, x, y, height - 1, outlineColor);
        drawVerticalLine(context, width - 1, y, height, outlineColor);
        drawHorizontalLine(context, x, width - 1, y, outlineColor);
        drawHorizontalLine(context, x, width - 2, height - 1, outlineColor);
        draw(context, x + 1, y + 1, width - 1, height - 1, color);
        context.getMatrices().scale(2.0F, 2.0F, 2.0F);
        disable2D();
    }

    public static void drawHorizontalLine(DrawContext context, float x, float x2, float y, int color) {
        if (x2 < x) {
            float fixedX = x;
            x = x2;
            x2 = fixedX;
        }

        draw(context, x, y, x2 + 1, y + 1, color);
    }

    public static void drawVerticalLine(DrawContext context, float x, float y, float y2, int color) {
        if (y2 < y) {
            float fixedY = y;
            y = y2;
            y2 = fixedY;
        }

        draw(context, x, y + 1, x + 1, y2, color);
    }
}
