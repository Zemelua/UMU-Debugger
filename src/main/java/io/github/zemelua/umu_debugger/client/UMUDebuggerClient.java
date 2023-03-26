package io.github.zemelua.umu_debugger.client;

import io.github.zemelua.umu_debugger.UMUDebugger;
import io.github.zemelua.umu_debugger.client.command.DebugRendererCommand;
import io.github.zemelua.umu_debugger.client.command.argument.DebugRendererArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;

@Environment(EnvType.CLIENT)
public class UMUDebuggerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ArgumentTypeRegistry.registerArgumentType(UMUDebugger.identifier("debug_renderer"), DebugRendererArgumentType.class, ConstantArgumentSerializer.of(DebugRendererArgumentType::new));

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registry) -> DebugRendererCommand.register(dispatcher));
	}
}
