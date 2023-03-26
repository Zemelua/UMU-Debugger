package io.github.zemelua.umu_debugger.mixin;

import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.network.DebugInfoSender;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugInfoSender.class)
public abstract class MixinDebugInfoSender {
	@Inject(at = @At("HEAD"), method = "sendPathfindingData")
	private static void sendPathfindingData(World world, MobEntity mob, @Nullable Path path, float nodeReachProximity, CallbackInfo callback) {
//		if (!world.isClient() && path != null) {
//			PacketByteBuf packet = PacketByteBufs.create();
//			packet.writeInt()
//		}
	}
}
