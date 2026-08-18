package dev.ripiters.rusted.client;

import dev.ripiters.rusted.common.RustedBlocks;
import dev.ripiters.rusted.common.RustedParticles;
import dev.ripiters.rusted.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ParticleClientHelper {

    public static void spawnParticlesForBlock(Level level, BlockPos pos, ParticleOptions defaultParticle) {
        Block block = level.getBlockState(pos).getBlock();
        ParticleOptions particleToSpawn = defaultParticle;

        if (isCreateWeatheredBlock(block)) {
            particleToSpawn = RustedParticles.CREATE_WEATHERED_SCRAPE.get();
        }

        spawnParticlesForShape(level, pos, particleToSpawn);
    }

    public static void spawnParticlesForShape(Level level, BlockPos pos, ParticleOptions particle) {
        VoxelShape shape = level.getBlockState(pos).getShape(level, pos);
        if (shape.isEmpty()) return;

        for (AABB aabb : shape.toAabbs()) {
            for (Direction direction : Direction.values()) {
                for (int i = 0; i < 2; i++) {
                    spawnParticleOnAABBFace(level, pos, aabb, particle, direction);
                }
            }
        }
    }

    private static void spawnParticleOnAABBFace(Level level, BlockPos pos, AABB aabb, ParticleOptions particle, Direction direction) {
        Vec3 center = new Vec3(
                pos.getX() + (aabb.minX + aabb.maxX) / 2.0,
                pos.getY() + (aabb.minY + aabb.maxY) / 2.0,
                pos.getZ() + (aabb.minZ + aabb.maxZ) / 2.0
        );

        double hx = (aabb.maxX - aabb.minX) / 2.0;
        double hy = (aabb.maxY - aabb.minY) / 2.0;
        double hz = (aabb.maxZ - aabb.minZ) / 2.0;

        int i = direction.getStepX();
        int j = direction.getStepY();
        int k = direction.getStepZ();

        double x = center.x + (i == 0 ? Mth.nextDouble(level.random, -hx, hx) : (i > 0 ? hx : -hx) + (i * 0.08));
        double y = center.y + (j == 0 ? Mth.nextDouble(level.random, -hy, hy) : (j > 0 ? hy : -hy) + (j * 0.08));
        double z = center.z + (k == 0 ? Mth.nextDouble(level.random, -hz, hz) : (k > 0 ? hz : -hz) + (k * 0.08));

        level.addParticle(particle, x, y, z, 0.0, 0.0, 0.0);
    }

    private static boolean isCreateWeatheredBlock(Block block) {
        if (block == RustedBlocks.getBlock("create", "weathered_iron_block") ||
                block == RustedBlocks.getBlock("create", "weathered_iron_window") ||
                block == RustedBlocks.getBlock("create", "weathered_iron_window_pane")) {
            return true;
        }

        if (Config.isCreateCompatEnabled() && RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_BLOCK != null) {
            return block == RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_BLOCK.get() ||
                    block == RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW.get() ||
                    block == RustedBlocks.WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW_PANE.get();
        }
        return false;
    }
}