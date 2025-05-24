package net.lugo.utools.util;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.lugo.utools.UTools;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.*;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class OverlayRenderer {
    private static final RenderPipeline LINES_DEPTH_TEST_PIPELINE = RenderPipelines.register(
        RenderPipeline.builder(RenderPipelines.POSITION_TEX_COLOR_SNIPPET)
            .withLocation(Identifier.of(UTools.MOD_ID, "pipeline/light_overlay"))
            .withCull(true)
            .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
            .withDepthWrite(true)
            .build()
    );

    private static final RenderLayer LINES_DEPTH_TEST = RenderLayer.of(
        "utools/light_overlay",
        1024,
        false,
        true,
        LINES_DEPTH_TEST_PIPELINE,
        RenderLayer.MultiPhaseParameters.builder()
            .build(false)
    );


    public static void draw(WorldRenderContext context, Vec3d pos, int r, int g, int b, float offsetY) {
        VertexConsumerProvider.Immediate vcp = VertexConsumerProvider.immediate(new BufferAllocator(1024));
        VertexConsumer vertexConsumer = vcp.getBuffer(LINES_DEPTH_TEST);

        Camera camera = context.camera();
        Vec3d transformedPos = pos.subtract(camera.getPos());

        MatrixStack matrixStack = new MatrixStack();
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180F));
        matrixStack.translate(transformedPos.x, transformedPos.y + offsetY, transformedPos.z);

        Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();

        RenderSystem.setShaderTexture(0, MinecraftClient.getInstance().getTextureManager().getTexture(Identifier.of(UTools.MOD_ID, "textures/cross.png")).getGlTexture());

        vertexConsumer.vertex(positionMatrix, 0,1,0).color(1f,1f,1f,1f).texture(0f,0f).light(0, 0);
        vertexConsumer.vertex(positionMatrix, 0,1,1).color(1f,1f,1f,1f).texture(0f,1f).light(0, 0);
        vertexConsumer.vertex(positionMatrix, 1,1,1).color(1f,1f,1f,1f).texture(1f,1f).light(0, 0);
        vertexConsumer.vertex(positionMatrix, 1,1,0).color(1f,1f,1f,1f).texture(1f,0f).light(0, 0);

        RenderSystem.setShaderColor(r, g, b, 1f);

        vcp.draw();

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }
}