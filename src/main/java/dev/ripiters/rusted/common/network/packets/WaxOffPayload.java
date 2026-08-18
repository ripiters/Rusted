package dev.ripiters.rusted.common.network.packets;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.client.ParticleClientHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record WaxOffPayload(BlockPos pos) implements CustomPacketPayload {
    public static final Type<WaxOffPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "wax_off"));
    public static final StreamCodec<ByteBuf, WaxOffPayload> STREAM_CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, WaxOffPayload::pos, WaxOffPayload::new);

    @Override public Type<WaxOffPayload> type() { return TYPE; }

    public static void handle(WaxOffPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level != null) {
                ParticleClientHelper.spawnParticlesForShape(Minecraft.getInstance().level, payload.pos(), ParticleTypes.WAX_OFF);
            }
        });
    }
}