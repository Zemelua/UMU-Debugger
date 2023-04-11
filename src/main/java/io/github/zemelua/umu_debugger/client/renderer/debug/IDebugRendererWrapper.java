package io.github.zemelua.umu_debugger.client.renderer.debug;

import net.minecraft.client.render.debug.DebugRenderer;

public interface IDebugRendererWrapper extends DebugRenderer.Renderer {
	void toggle();
}
