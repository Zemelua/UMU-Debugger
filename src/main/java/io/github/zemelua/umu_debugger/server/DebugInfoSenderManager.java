package io.github.zemelua.umu_debugger.server;

import net.minecraft.entity.ai.pathing.Path;

import java.util.ArrayList;
import java.util.List;

public final class DebugInfoSenderManager {
	public static final IDMapper<Path> PATHS = new IDMapper<>();

	public static class IDMapper<T> {
		private final List<T> objects = new ArrayList<>();

		public int registerAndGetID(T object) {
			if (!this.objects.contains(object)) {
				this.objects.add(object);
			}

			return this.objects.indexOf(object);
		}
	}

	private DebugInfoSenderManager() {}
}
