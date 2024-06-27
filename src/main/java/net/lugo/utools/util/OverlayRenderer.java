package net.lugo.utools.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.lugo.utools.UTools;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class OverlayRenderer {
    public static void draw(WorldRenderContext context, Vec3d pos, int r, int g, int b, float offsetY) {
        Camera camera = context.camera();
        Vec3d transformedPos = pos.subtract(camera.getPos());


        MatrixStack matrixStack = new MatrixStack();
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180F));
        matrixStack.translate(transformedPos.x, transformedPos.y + offsetY, transformedPos.z);

        Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);

        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderTexture(0, Identifier.tryParse(UTools.MOD_ID, "textures/cross.png"));
        RenderSystem.enableDepthTest();

        RenderSystem.setShaderColor(1f,1f,1f,1f);

        buffer.vertex(positionMatrix, 0,1,0).color(1f,1f,1f,1f).texture(0f,0f).light(0, 0);
        buffer.vertex(positionMatrix, 0,1,1).color(1f,1f,1f,1f).texture(0f,1f).light(0, 0);
        buffer.vertex(positionMatrix, 1,1,1).color(1f,1f,1f,1f).texture(1f,1f).light(0, 0);
        buffer.vertex(positionMatrix, 1,1,0).color(1f,1f,1f,1f).texture(1f,0f).light(0, 0);

        RenderSystem.setShaderColor(r, g, b, 1f);
        BufferRenderer.drawWithGlobalProgram(buffer.end());

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }
}
