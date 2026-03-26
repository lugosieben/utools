package net.lugo.utools.util;

import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.lugo.utools.UTools;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class OverlayRenderer {
    private static final Identifier shaderTexture = Identifier.fromNamespaceAndPath(UTools.MOD_ID, "textures/cross.png");
    private static final MultiBufferSource.BufferSource vcp = MultiBufferSource.immediate(new ByteBufferBuilder(8192));
    private static final PoseStack matrixStack = new PoseStack();
    private static VertexConsumer vertexConsumer;
    private static boolean batchStarted = false;

    private static final RenderPipeline LIGHT_OVERLAY_PIPELINE = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.GUI_TEXTURED_SNIPPET)
                    .withLocation(Identifier.fromNamespaceAndPath(UTools.MOD_ID, "pipeline/light_overlay"))
                    .withCull(true)
                    .withDepthStencilState(DepthStencilState.DEFAULT)
                    .build()
    );

    private static final RenderType LIGHT_OVERLAY_RENDERLAYER = RenderType.create(
            "utools/light_overlay",
            RenderSetup.builder(LIGHT_OVERLAY_PIPELINE)
                    .withTexture("Sampler0", shaderTexture)
                    .affectsCrumbling().setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                    .createRenderSetup()
    );

    public static void startBatch() {
        if (batchStarted) return;

        vertexConsumer = vcp.getBuffer(LIGHT_OVERLAY_RENDERLAYER);
        batchStarted = true;
    }

    public static void addBlock(Camera camera, Vec3 pos, int r, int g, int b, float offsetY) {
        if (!batchStarted) return;

        Vec3 transformedPos = pos.subtract(camera.position());

        matrixStack.pushPose();
        matrixStack.mulPose(Axis.XP.rotationDegrees(camera.xRot()));
        matrixStack.mulPose(Axis.YP.rotationDegrees(camera.yRot() + 180F));
        matrixStack.translate(transformedPos.x, transformedPos.y + offsetY, transformedPos.z);

        Matrix4f positionMatrix = matrixStack.last().pose();

        float rf = r / 255f;
        float gf = g / 255f;
        float bf = b / 255f;

        vertexConsumer.addVertex(positionMatrix, 0, 1, 0).setColor(rf, gf, bf, 1f).setUv(0f, 0f).setUv2(0, 0);
        vertexConsumer.addVertex(positionMatrix, 0, 1, 1).setColor(rf, gf, bf, 1f).setUv(0f, 1f).setUv2(0, 0);
        vertexConsumer.addVertex(positionMatrix, 1, 1, 1).setColor(rf, gf, bf, 1f).setUv(1f, 1f).setUv2(0, 0);
        vertexConsumer.addVertex(positionMatrix, 1, 1, 0).setColor(rf, gf, bf, 1f).setUv(1f, 0f).setUv2(0, 0);

        matrixStack.popPose();
    }

    public static void endBatch() {
        if (!batchStarted) return;

        vcp.endBatch();
        batchStarted = false;
    }
}