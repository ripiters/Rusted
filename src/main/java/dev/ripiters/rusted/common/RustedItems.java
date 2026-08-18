package dev.ripiters.rusted.common;

import dev.ripiters.rusted.Rusted;

import net.minecraft.world.item.Item;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RustedItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Rusted.MOD_ID);

    public static final DeferredItem<Item> RUST_POWDER = Rusted.Mods.isCreateLoaded() ? ITEMS.registerSimpleItem("rust_powder") : null;
}