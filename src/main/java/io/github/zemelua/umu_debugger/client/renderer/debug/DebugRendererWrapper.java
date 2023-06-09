package io.github.zemelua.umu_debugger.client.renderer.debug;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class DebugRendererWrapper implements IDebugRendererWrapper {
	private final DebugRenderer.Renderer renderer;
	private boolean active;

	public DebugRendererWrapper(DebugRenderer.Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, double cameraX, double cameraY, double cameraZ) {
		if (this.active) {
			this.renderer.render(matrices, vertexConsumers, cameraX, cameraY, cameraZ);
		}
	}

	@Override
	public void toggle() {
		this.active = !this.active;
	}
}
