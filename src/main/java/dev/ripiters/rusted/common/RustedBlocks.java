package dev.ripiters.rusted.common;

import dev.ripiters.rusted.Rusted;
import dev.ripiters.rusted.common.block.RustableBlock;
import dev.ripiters.rusted.config.Config;
import dev.ripiters.rusted.integration.create.CreateCompat;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class RustedBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Rusted.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Rusted.MOD_ID);

    public static final Map<Block, Block> RUSTING_MAP = new HashMap<>();
    public static final Map<Block, Block> UNWAXING_MAP = new HashMap<>();
    public static final Map<Block, Block> WAXING_MAP = new HashMap<>();

    private static <T extends Block> DeferredBlock<T> registerWithItem(String name, Supplier<T> blockSupplier) {
        DeferredBlock<T> block = BLOCKS.register(name, blockSupplier);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static DeferredBlock<Block> registerClean(String name) {
        return registerWithItem(name, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL)));
    }

    private static DeferredBlock<Block> registerRustable(String name) {
        return registerWithItem(name, () -> new RustableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL).randomTicks()));
    }

    private static DeferredBlock<StairBlock> registerStairs(String name) {
        return registerWithItem(name, () -> new StairBlock(Blocks.IRON_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL)));
    }

    private static DeferredBlock<StairBlock> registerRustableStairs(String name) {
        return registerWithItem(name, () -> new StairBlock(Blocks.IRON_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL).randomTicks()));
    }

    private static DeferredBlock<SlabBlock> registerSlab(String name) {
        return registerWithItem(name, () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL)));
    }

    private static DeferredBlock<SlabBlock> registerRustableSlab(String name) {
        return registerWithItem(name, () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL).randomTicks()));
    }

    private static DeferredBlock<Block> registerCreateBlockCopy(String name, String createPath) {
        if (!Config.isCreateCompatEnabled()) return null;
        return registerWithItem(name, () -> {
            Block target = getBlock("create", createPath);
            BlockBehaviour.Properties props = (target != Blocks.AIR)
                    ? BlockBehaviour.Properties.ofFullCopy(target)
                    : BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL);
            return new Block(props);
        });
    }

    private static DeferredBlock<Block> registerCreateWindowCopy(String name, String createPath) {
        if (!Config.isCreateCompatEnabled()) return null;
        return registerWithItem(name, () -> {
            Block target = getBlock("create", createPath);
            BlockBehaviour.Properties props = (target != Blocks.AIR)
                    ? BlockBehaviour.Properties.ofFullCopy(target)
                    : BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).sound(SoundType.GLASS).noOcclusion();

            return CreateCompat.createGlassBlock(props);
        });
    }

    private static DeferredBlock<Block> registerCreateWindowPaneCopy(String name, String createPath) {
        if (!Config.isCreateCompatEnabled()) return null;
        return registerWithItem(name, () -> {
            Block target = getBlock("create", createPath);
            BlockBehaviour.Properties props = (target != Blocks.AIR)
                    ? BlockBehaviour.Properties.ofFullCopy(target)
                    : BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE).sound(SoundType.GLASS).noOcclusion();

            return CreateCompat.createGlassPaneBlock(props);
        });
    }

    // --- Standard Iron Blocks ---
    public static final DeferredBlock<Block> EXPOSED_IRON_BLOCK = registerRustable("exposed_iron_block");
    public static final DeferredBlock<Block> WEATHERED_IRON_BLOCK = registerRustable("weathered_iron_block");
    public static final DeferredBlock<Block> RUSTED_IRON_BLOCK = registerClean("rusted_iron_block");

    public static final DeferredBlock<Block> WAXED_IRON_BLOCK = registerClean("waxed_iron_block");
    public static final DeferredBlock<Block> WAXED_EXPOSED_IRON_BLOCK = registerClean("waxed_exposed_iron_block");
    public static final DeferredBlock<Block> WAXED_WEATHERED_IRON_BLOCK = registerClean("waxed_weathered_iron_block");
    public static final DeferredBlock<Block> WAXED_RUSTED_IRON_BLOCK = registerClean("waxed_rusted_iron_block");

    // --- Cut Iron Blocks ---
    public static final DeferredBlock<Block> CUT_IRON_BLOCK = registerClean("cut_iron_block");
    public static final DeferredBlock<Block> EXPOSED_CUT_IRON_BLOCK = registerRustable("exposed_cut_iron_block");
    public static final DeferredBlock<Block> WEATHERED_CUT_IRON_BLOCK = registerRustable("weathered_cut_iron_block");
    public static final DeferredBlock<Block> RUSTED_CUT_IRON_BLOCK = registerClean("rusted_cut_iron_block");

    public static final DeferredBlock<Block> WAXED_CUT_IRON_BLOCK = registerClean("waxed_cut_iron_block");
    public static final DeferredBlock<Block> WAXED_EXPOSED_CUT_IRON_BLOCK = registerClean("waxed_exposed_cut_iron_block");
    public static final DeferredBlock<Block> WAXED_WEATHERED_CUT_IRON_BLOCK = registerClean("waxed_weathered_cut_iron_block");
    public static final DeferredBlock<Block> WAXED_RUSTED_CUT_IRON_BLOCK = registerClean("waxed_rusted_cut_iron_block");

    // --- Cut Iron Stairs ---
    public static final DeferredBlock<StairBlock> CUT_IRON_STAIRS = registerStairs("cut_iron_stairs");
    public static final DeferredBlock<StairBlock> EXPOSED_CUT_IRON_STAIRS = registerRustableStairs("exposed_cut_iron_stairs");
    public static final DeferredBlock<StairBlock> WEATHERED_CUT_IRON_STAIRS = registerRustableStairs("weathered_cut_iron_stairs");
    public static final DeferredBlock<StairBlock> RUSTED_CUT_IRON_STAIRS = registerStairs("rusted_cut_iron_stairs");

    public static final DeferredBlock<StairBlock> WAXED_CUT_IRON_STAIRS = registerStairs("waxed_cut_iron_stairs");
    public static final DeferredBlock<StairBlock> WAXED_EXPOSED_CUT_IRON_STAIRS = registerStairs("waxed_exposed_cut_iron_stairs");
    public static final DeferredBlock<StairBlock> WAXED_WEATHERED_CUT_IRON_STAIRS = registerStairs("waxed_weathered_cut_iron_stairs");
    public static final DeferredBlock<StairBlock> WAXED_RUSTED_CUT_IRON_STAIRS = registerStairs("waxed_rusted_cut_iron_stairs");

    // --- Cut Iron Slabs ---
    public static final DeferredBlock<SlabBlock> CUT_IRON_SLAB = registerSlab("cut_iron_slab");
    public static final DeferredBlock<SlabBlock> EXPOSED_CUT_IRON_SLAB = registerRustableSlab("exposed_cut_iron_slab");
    public static final DeferredBlock<SlabBlock> WEATHERED_CUT_IRON_SLAB = registerRustableSlab("weathered_cut_iron_slab");
    public static final DeferredBlock<SlabBlock> RUSTED_CUT_IRON_SLAB = registerSlab("rusted_cut_iron_slab");

    public static final DeferredBlock<SlabBlock> WAXED_CUT_IRON_SLAB = registerSlab("waxed_cut_iron_slab");
    public static final DeferredBlock<SlabBlock> WAXED_EXPOSED_CUT_IRON_SLAB = registerSlab("waxed_exposed_cut_iron_slab");
    public static final DeferredBlock<SlabBlock> WAXED_WEATHERED_CUT_IRON_SLAB = registerSlab("waxed_weathered_cut_iron_slab");
    public static final DeferredBlock<SlabBlock> WAXED_RUSTED_CUT_IRON_SLAB = registerSlab("waxed_rusted_cut_iron_slab");

    // --- WAXED CREATE VARIANTS (Będą miały wartość null, jeśli brak moda Create) ---
    public static final DeferredBlock<Block> WAXED_INDUSTRIAL_IRON_BLOCK =
            registerCreateBlockCopy("waxed_industrial_iron_block", "industrial_iron_block");
    public static final DeferredBlock<Block> WAXED_WEATHERED_INDUSTRIAL_IRON_BLOCK =
            registerCreateBlockCopy("waxed_weathered_industrial_iron_block", "weathered_iron_block");

    public static final DeferredBlock<Block> WAXED_INDUSTRIAL_IRON_WINDOW =
            registerCreateWindowCopy("waxed_industrial_iron_window", "industrial_iron_window");
    public static final DeferredBlock<Block> WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW =
            registerCreateWindowCopy("waxed_weathered_industrial_iron_window", "weathered_iron_window");

    public static final DeferredBlock<Block> WAXED_INDUSTRIAL_IRON_WINDOW_PANE =
            registerCreateWindowPaneCopy("waxed_industrial_iron_window_pane", "industrial_iron_window_pane");
    public static final DeferredBlock<Block> WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW_PANE =
            registerCreateWindowPaneCopy("waxed_weathered_industrial_iron_window_pane", "weathered_iron_window_pane");

    public static void setupMaps() {
        RUSTING_MAP.clear();
        UNWAXING_MAP.clear();
        WAXING_MAP.clear();

        addRustingCycle(Blocks.IRON_BLOCK, EXPOSED_IRON_BLOCK.get(), WEATHERED_IRON_BLOCK.get(), RUSTED_IRON_BLOCK.get());
        addWaxingCycle(Blocks.IRON_BLOCK, EXPOSED_IRON_BLOCK.get(), WEATHERED_IRON_BLOCK.get(), RUSTED_IRON_BLOCK.get(),
                WAXED_IRON_BLOCK.get(), WAXED_EXPOSED_IRON_BLOCK.get(), WAXED_WEATHERED_IRON_BLOCK.get(), WAXED_RUSTED_IRON_BLOCK.get());

        addRustingCycle(CUT_IRON_BLOCK.get(), EXPOSED_CUT_IRON_BLOCK.get(), WEATHERED_CUT_IRON_BLOCK.get(), RUSTED_CUT_IRON_BLOCK.get());
        addWaxingCycle(CUT_IRON_BLOCK.get(), EXPOSED_CUT_IRON_BLOCK.get(), WEATHERED_CUT_IRON_BLOCK.get(), RUSTED_CUT_IRON_BLOCK.get(),
                WAXED_CUT_IRON_BLOCK.get(), WAXED_EXPOSED_CUT_IRON_BLOCK.get(), WAXED_WEATHERED_CUT_IRON_BLOCK.get(), WAXED_RUSTED_CUT_IRON_BLOCK.get());

        addRustingCycle(CUT_IRON_STAIRS.get(), EXPOSED_CUT_IRON_STAIRS.get(), WEATHERED_CUT_IRON_STAIRS.get(), RUSTED_CUT_IRON_STAIRS.get());
        addWaxingCycle(CUT_IRON_STAIRS.get(), EXPOSED_CUT_IRON_STAIRS.get(), WEATHERED_CUT_IRON_STAIRS.get(), RUSTED_CUT_IRON_STAIRS.get(),
                WAXED_CUT_IRON_STAIRS.get(), WAXED_EXPOSED_CUT_IRON_STAIRS.get(), WAXED_WEATHERED_CUT_IRON_STAIRS.get(), WAXED_RUSTED_CUT_IRON_STAIRS.get());

        addRustingCycle(CUT_IRON_SLAB.get(), EXPOSED_CUT_IRON_SLAB.get(), WEATHERED_CUT_IRON_SLAB.get(), RUSTED_CUT_IRON_SLAB.get());
        addWaxingCycle(CUT_IRON_SLAB.get(), EXPOSED_CUT_IRON_SLAB.get(), WEATHERED_CUT_IRON_SLAB.get(), RUSTED_CUT_IRON_SLAB.get(),
                WAXED_CUT_IRON_SLAB.get(), WAXED_EXPOSED_CUT_IRON_SLAB.get(), WAXED_WEATHERED_CUT_IRON_SLAB.get(), WAXED_RUSTED_CUT_IRON_SLAB.get());

        if (Config.isCreateCompatEnabled()) {
            setupCreateCompatMaps();
        }
    }

    private static void setupCreateCompatMaps() {
        Block industrialIronBlock = getBlock("create", "industrial_iron_block");
        Block weatheredIronBlock = getBlock("create", "weathered_iron_block");

        Block industrialIronWindow = getBlock("create", "industrial_iron_window");
        Block weatheredIronWindow = getBlock("create", "weathered_iron_window");

        Block industrialIronWindowPane = getBlock("create", "industrial_iron_window_pane");
        Block weatheredIronWindowPane = getBlock("create", "weathered_iron_window_pane");

        if (industrialIronBlock != Blocks.AIR && weatheredIronBlock != Blocks.AIR) {
            RUSTING_MAP.put(industrialIronBlock, weatheredIronBlock);
            UNWAXING_MAP.put(weatheredIronBlock, industrialIronBlock);

            WAXING_MAP.put(industrialIronBlock, WAXED_INDUSTRIAL_IRON_BLOCK.get());
            UNWAXING_MAP.put(WAXED_INDUSTRIAL_IRON_BLOCK.get(), industrialIronBlock);

            WAXING_MAP.put(weatheredIronBlock, WAXED_WEATHERED_INDUSTRIAL_IRON_BLOCK.get());
            UNWAXING_MAP.put(WAXED_WEATHERED_INDUSTRIAL_IRON_BLOCK.get(), weatheredIronBlock);
        }

        if (industrialIronWindow != Blocks.AIR && weatheredIronWindow != Blocks.AIR) {
            RUSTING_MAP.put(industrialIronWindow, weatheredIronWindow);
            UNWAXING_MAP.put(weatheredIronWindow, industrialIronWindow);

            WAXING_MAP.put(industrialIronWindow, WAXED_INDUSTRIAL_IRON_WINDOW.get());
            UNWAXING_MAP.put(WAXED_INDUSTRIAL_IRON_WINDOW.get(), industrialIronWindow);

            WAXING_MAP.put(weatheredIronWindow, WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW.get());
            UNWAXING_MAP.put(WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW.get(), weatheredIronWindow);
        }

        if (industrialIronWindowPane != Blocks.AIR && weatheredIronWindowPane != Blocks.AIR) {
            RUSTING_MAP.put(industrialIronWindowPane, weatheredIronWindowPane);
            UNWAXING_MAP.put(weatheredIronWindowPane, industrialIronWindowPane);

            WAXING_MAP.put(industrialIronWindowPane, WAXED_INDUSTRIAL_IRON_WINDOW_PANE.get());
            UNWAXING_MAP.put(WAXED_INDUSTRIAL_IRON_WINDOW_PANE.get(), industrialIronWindowPane);

            WAXING_MAP.put(weatheredIronWindowPane, WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW_PANE.get());
            UNWAXING_MAP.put(WAXED_WEATHERED_INDUSTRIAL_IRON_WINDOW_PANE.get(), weatheredIronWindowPane);
        }
    }

    public static Block getBlock(String namespace, String path) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    private static void addRustingCycle(Block clean, Block exposed, Block weathered, Block rusted) {
        RUSTING_MAP.put(clean, exposed);
        RUSTING_MAP.put(exposed, weathered);
        RUSTING_MAP.put(weathered, rusted);

        UNWAXING_MAP.put(exposed, clean);
        UNWAXING_MAP.put(weathered, exposed);
        UNWAXING_MAP.put(rusted, weathered);
    }

    private static void addWaxingCycle(Block clean, Block exposed, Block weathered, Block rusted, Block wClean, Block wExposed, Block wWeathered, Block wRusted) {
        WAXING_MAP.put(clean, wClean);
        WAXING_MAP.put(exposed, wExposed);
        WAXING_MAP.put(weathered, wWeathered);
        WAXING_MAP.put(rusted, wRusted);

        UNWAXING_MAP.put(wClean, clean);
        UNWAXING_MAP.put(wExposed, exposed);
        UNWAXING_MAP.put(wWeathered, weathered);
        UNWAXING_MAP.put(wRusted, rusted);
    }

    public static Optional<Block> getNextRustState(Block block) {
        return Optional.ofNullable(RUSTING_MAP.get(block));
    }
    public static Optional<Block> getUnwaxedState(Block block) {
        return Optional.ofNullable(UNWAXING_MAP.get(block));
    }
    public static Optional<Block> getWaxedState(Block block) {
        return Optional.ofNullable(WAXING_MAP.get(block));
    }
}