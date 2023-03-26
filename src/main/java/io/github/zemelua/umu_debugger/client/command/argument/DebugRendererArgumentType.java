package io.github.zemelua.umu_debugger.client.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.zemelua.umu_debugger.client.renderer.debug.DebugRendererManager;
import io.github.zemelua.umu_debugger.client.renderer.debug.IDebugRendererWrapper;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class DebugRendererArgumentType implements ArgumentType<IDebugRendererWrapper> {
	private static final DynamicCommandExceptionType EXCEPTION_INVALID_DEBUG_RENDERER = new DynamicCommandExceptionType(id
			-> Text.translatable("command.umu_little_maid.error.invalid_debug_renderer", id));

	public static IDebugRendererWrapper getRenderer(CommandContext<FabricClientCommandSource> context, String name) {
		return context.getArgument(name, IDebugRendererWrapper.class);
	}

	@Override
	public IDebugRendererWrapper parse(StringReader reader) throws CommandSyntaxException {
		Identifier id = Identifier.fromCommandInput(reader);
		return DebugRendererManager.getInstance().get(id).orElseThrow(() -> EXCEPTION_INVALID_DEBUG_RENDERER.create(id));
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		return CommandSource.suggestIdentifiers(DebugRendererManager.getInstance().getAllID(), builder);
	}
}
