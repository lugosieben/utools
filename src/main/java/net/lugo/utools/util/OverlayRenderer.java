package net.lugo.utools.util;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import net.lugo.utools.UTools;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.*;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class OverlayRenderer {
    private static final Identifier shaderTexture = Identifier.of(UTools.MOD_ID, "textures/cross.png");
    private static final VertexConsumerProvider.Immediate vcp = VertexConsumerProvider.immediate(new BufferAllocator(8192));
    private static final MatrixStack matrixStack = new MatrixStack();
    private static VertexConsumer vertexConsumer;
    private static boolean batchStarted = false;

    private static final RenderPipeline LIGHT_OVERLAY_PIPELINE = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.POSITION_TEX_COLOR_SNIPPET)
                    .withLocation(Identifier.of(UTools.MOD_ID, "pipeline/light_overlay"))
                    .withCull(true)
                    .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
                    .withDepthWrite(true)
                    .build()
    );

    private static final RenderLayer LIGHT_OVERLAY_RENDERLAYER = RenderLayer.of(
            "utools/light_overlay",
            RenderSetup.builder(LIGHT_OVERLAY_PIPELINE)
                    .texture("Sampler0", shaderTexture)
                    .crumbling().outlineMode(RenderSetup.OutlineMode.AFFECTS_OUTLINE)
                    .build()
    );

    public static void startBatch() {
        if (batchStarted) return;

        vertexConsumer = vcp.getBuffer(LIGHT_OVERLAY_RENDERLAYER);
        batchStarted = true;
    }

    public static void addBlock(Camera camera, Vec3d pos, int r, int g, int b, float offsetY) {
        if (!batchStarted) return;

        Vec3d transformedPos = pos.subtract(camera.getCameraPos());

        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180F));
        matrixStack.translate(transformedPos.x, transformedPos.y + offsetY, transformedPos.z);

        Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();

        float rf = r / 255f;
        float gf = g / 255f;
        float bf = b / 255f;

        vertexConsumer.vertex(positionMatrix, 0, 1, 0).color(rf, gf, bf, 1f).texture(0f, 0f).light(0, 0);
        vertexConsumer.vertex(positionMatrix, 0, 1, 1).color(rf, gf, bf, 1f).texture(0f, 1f).light(0, 0);
        vertexConsumer.vertex(positionMatrix, 1, 1, 1).color(rf, gf, bf, 1f).texture(1f, 1f).light(0, 0);
        vertexConsumer.vertex(positionMatrix, 1, 1, 0).color(rf, gf, bf, 1f).texture(1f, 0f).light(0, 0);

        matrixStack.pop();
    }

    public static void endBatch() {
        if (!batchStarted) return;

        vcp.draw();
        batchStarted = false;
    }
}