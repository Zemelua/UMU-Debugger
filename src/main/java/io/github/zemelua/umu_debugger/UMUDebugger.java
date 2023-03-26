package io.github.zemelua.umu_debugger;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class UMUDebugger implements ModInitializer {
	@Override
	public void onInitialize() {

	}

	public static Identifier identifier(String path) {
		return new Identifier("umu_debugger", path);
	}
}
