package dev.ripiters.rusted.common.network.packets;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.client.ParticleClientHelper;
import dev.ripiters.rusted.common.RustedParticles;
import dev.ripiters.rusted.config.Config;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record RustScrapePayload(BlockPos pos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<RustScrapePayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Rusted.MOD_ID, "rust_scrape"));

    public static final StreamCodec<ByteBuf, RustScrapePayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, RustScrapePayload::pos,
            RustScrapePayload::new
    );

    @Override
    public CustomPacketPayload.Type<RustScrapePayload> type() {
        return TYPE;
    }

    public static void handle(RustScrapePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            Level level = mc.level;
            if (level != null) {
                BlockPos pos = payload.pos();
                BlockState state = level.getBlockState(pos);

                ParticleClientHelper.spawnParticlesForBlock(level, pos, RustedParticles.RUST_SCRAPE.get());

                if (Config.ENABLE_PARTICLES.get()) {
                    mc.particleEngine.destroy(pos, state);
                }
            }
        });
    }
}