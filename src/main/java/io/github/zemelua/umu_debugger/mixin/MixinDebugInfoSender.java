package io.github.zemelua.umu_debugger.mixin;

import io.github.zemelua.umu_debugger.server.DebugInfoSenderManager;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.ai.pathing.TargetPathNode;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.DebugInfoSender;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(DebugInfoSender.class)
public abstract class MixinDebugInfoSender {
	@Shadow private static void sendToAll(ServerWorld world, PacketByteBuf packet, Identifier channel) {}

	@Inject(at = @At("HEAD"), method = "sendPathfindingData")
	private static void sendPathfindingData(World world, MobEntity mob, @Nullable Path path, float nodeReachProximity, CallbackInfo callback) {
		if (!world.isClient() && path != null) {
			List<PathNode> openNodes = new ArrayList<>();
			List<PathNode> closedNodes = new ArrayList<>();

			for (int i = 0; i < path.getLength(); i++) {
				PathNode node = path.getNode(i);
				if (node.visited) {
					closedNodes.add(node);
				} else {
					openNodes.add(node);
				}
			}

			final TargetPathNode DUMMY = new TargetPathNode(0, 0, 0);
			path.setDebugInfo(openNodes.toArray(new PathNode[0]), closedNodes.toArray(new PathNode[0]), Collections.singleton(DUMMY));

			PacketByteBuf packet = PacketByteBufs.create();
			packet.writeInt(DebugInfoSenderManager.PATHS.registerAndGetID(path));
			packet.writeFloat(nodeReachProximity);
			path.toBuffer(packet);
			sendToAll((ServerWorld) world, packet, CustomPayloadS2CPacket.DEBUG_PATH);
		}
	}
}
