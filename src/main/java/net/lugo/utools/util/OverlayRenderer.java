package net.lugo.utools.util;

import com.mojang.blaze3d.buffers.BufferType;
import com.mojang.blaze3d.buffers.BufferUsage;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.lugo.utools.UTools;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

//public class OverlayRenderer {
//    public static void draw(WorldRenderContext context, Vec3d pos, int r, int g, int b, float offsetY) {
        //Camera camera = context.camera();
        //Vec3d transformedPos = pos.subtract(camera.getPos());
//
        //// Set up the matrix stack for transformations
        //MatrixStack matrixStack = new MatrixStack();
        //matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        //matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180F));
        //matrixStack.translate(transformedPos.x, transformedPos.y + offsetY, transformedPos.z);
//
        //Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
//
        //GpuDevice device = RenderSystem.getDevice();
        //ScoreboardCriterion.RenderType renderType = Renderers.getRenderer().getRenderType();
        //GpuTexture texture = context.getTextureManager().getTexture(Identifier.tryParse(UTools.MOD_ID, "textures/cross.png")).getTexture();
//
        //RenderPipeline pipeline = renderType.getRenderPipeline(); // Use RenderPipeline as in Compute class
//
        //// Begin rendering using the pipeline
        //try (RenderPass pass = device.createCommandEncoder().createRenderPass(renderType.getRenderTarget().getColorTexture(),
        //    OptionalInt.empty(), renderType.getRenderTarget().getDepthTexture(), OptionalDouble.empty())) {
//
        //    pass.setPipeline(pipeline);
        //    pass.bindSampler("Sampler0", texture);
//
        //    // Set shader color
        //    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
//
        //    // Create vertex buffer
        //    Tessellator tessellator = Tessellator.getInstance();
        //    BufferBuilder builder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        //    builder.vertex(positionMatrix, 0, 1, 0).color(r / 255f, g / 255f, b / 255f, 1f).texture(0f, 0f).light(0, 0);
        //    builder.vertex(positionMatrix, 0, 1, 1).color(r / 255f, g / 255f, b / 255f, 1f).texture(0f, 1f).light(0, 0);
        //    builder.vertex(positionMatrix, 1, 1, 1).color(r / 255f, g / 255f, b / 255f, 1f).texture(1f, 1f).light(0, 0);
        //    builder.vertex(positionMatrix, 1, 1, 0).color(r / 255f, g / 255f, b / 255f, 1f).texture(1f, 0f).light(0, 0);
//
        //    BufferHolder buffer = new BufferHolder();
        //    buffer.upload(builder.buildOrThrow());
//
        //    // Draw using the buffer
        //    pass.draw(buffer.getGpuBuffers().get(0), 0, buffer.getGpuBuffers().get(0).size(), uniformUploader -> {
        //        uniformUploader.upload("ModelOffset", (float) transformedPos.x, (float) transformedPos.y, (float) transformedPos.z);
        //    });
//
        //} finally {
        //    RenderSystem.setShaderColor(1f, 1f, 1f, 1f); // Reset shader color to default
        //}
//    }
//
    //private static class BufferHolder {
    //    private final List<GpuBuffer> gpuBuffers;
//
    //    BufferHolder() {
    //        gpuBuffers = new ArrayList<>();
    //    }
//
    //    boolean isValid() {
    //        return !gpuBuffers.isEmpty();
    //    }
//
    //    void close() {
    //        for (var buffer : gpuBuffers) {
    //            buffer.close();
    //        }
    //        gpuBuffers.clear();
    //    }
//
    //    void upload(BuiltBuffer buffer) {
    //        gpuBuffers.add(RenderSystem.getDevice().createBuffer("utools overlay renderer", BufferType.VERTICES, BufferUsage.STATIC_WRITE, buffer.vertexBuffer()));
    //        buffer.close();
    //    }
//
    //    List<GpuBuffer> getGpuBuffers() {
    //        return gpuBuffers;
    //    }
    //}
//}