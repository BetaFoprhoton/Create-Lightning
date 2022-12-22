package com.betaforge1.createlightning;

import com.betaforge1.createlightning.content.curiosities.armor.CopperLeydenHelmetItem;
import com.betaforge1.createlightning.context.items.ElectricStaffItem;
import com.betaforge1.createlightning.context.items.ResistiveGelItem;
import com.simibubi.create.content.contraptions.itemAssembly.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.simibubi.create.content.AllSections.MATERIALS;

public class CLItems {
    private static final CreateRegistrate REGISTRATE = CreateLightning.registrate()
            .creativeModeTab(() -> CreateLightning.BASE_CREATIVE_TAB);

    // Schematics

    static {
        REGISTRATE.startSection(MATERIALS);
    }

    public static final ItemEntry<Item> SLIMY_RESISTIVE_GEL = REGISTRATE.item("slimy_resistive_gel", Item::new)
            .register();

    public static final ItemEntry<ResistiveGelItem> RESISTIVE_GEL = REGISTRATE.item("resistive_gel", ResistiveGelItem::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static final ItemEntry<ElectricStaffItem> ELECTRIC_STAFF = REGISTRATE.item("electric_staff", ElectricStaffItem::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static final ItemEntry<CopperLeydenHelmetItem> LEYDEN_HELMET = REGISTRATE.item("leyden_helmet", CopperLeydenHelmetItem::new)
            .register();

    private static ItemEntry<SequencedAssemblyItem> sequencedIngredient(String name) {
        return REGISTRATE.item(name, SequencedAssemblyItem::new)
                .register();
    }

    public static void register() {}
}
