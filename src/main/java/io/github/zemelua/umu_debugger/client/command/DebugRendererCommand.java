package io.github.zemelua.umu_debugger.client.command;

import com.mojang.brigadier.CommandDispatcher;
import io.github.zemelua.umu_debugger.client.command.argument.DebugRendererArgumentType;
import io.github.zemelua.umu_debugger.client.renderer.debug.IDebugRendererWrapper;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public final class DebugRendererCommand {
	public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(literal("debug_renderer")
				.then(argument("renderer", new DebugRendererArgumentType())
						.executes(context -> toggleDebugRenderer(context.getSource(), DebugRendererArgumentType.getRenderer(context, "renderer")))));
	}

	public static int toggleDebugRenderer(FabricClientCommandSource source, IDebugRendererWrapper renderer) {
		renderer.toggle();

		return 1;
	}

	private DebugRendererCommand() {}
}
