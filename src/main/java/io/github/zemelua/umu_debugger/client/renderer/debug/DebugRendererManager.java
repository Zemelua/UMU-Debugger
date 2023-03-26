package io.github.zemelua.umu_debugger.client.renderer.debug;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class DebugRendererManager {
	private static DebugRendererManager INSTANCE;

	private final MinecraftClient client;
	private final DebugRenderer debugRenderer;
	private final Map<Identifier, IDebugRendererWrapper> renderers = new HashMap<>();

	public static void init(MinecraftClient client) {
		INSTANCE = new DebugRendererManager(client);
		INSTANCE.addVanillaRenderers();
	}

	private DebugRendererManager(MinecraftClient client) {
		this.client = client;
		this.debugRenderer = client.debugRenderer;
	}

	private void addVanillaRenderers() {
		this.renderers.put(new Identifier("path_finding"), new DebugRendererWrapper(this.debugRenderer.pathfindingDebugRenderer));
	}

	public Optional<IDebugRendererWrapper> get(Identifier id) {
		return Optional.ofNullable(this.renderers.get(id));
	}

	public Stream<Identifier> getAllID() {
		return this.renderers.keySet().stream();
	}

	public static DebugRendererManager getInstance() {
		return INSTANCE;
	}
}
