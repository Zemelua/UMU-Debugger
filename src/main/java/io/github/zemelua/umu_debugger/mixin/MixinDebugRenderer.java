package io.github.zemelua.umu_debugger.mixin;

import io.github.zemelua.umu_debugger.client.renderer.debug.DebugRendererManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugRenderer.class)
public abstract class MixinDebugRenderer {
	@SuppressWarnings("SpellCheckingInspection")
	@Inject(at = @At("HEAD"),
			method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;DDD)V")
	public void render(MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, double cameraX, double cameraY, double cameraZ, CallbackInfo info) {
		DebugRendererManager.getInstance().getAll().forEach(r -> r.render(matrices, vertexConsumers, cameraX, cameraY, cameraZ));
	}
}
