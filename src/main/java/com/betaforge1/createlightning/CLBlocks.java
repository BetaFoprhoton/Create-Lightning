package com.betaforge1.createlightning;

import com.betaforge1.createlightning.context.blocks.ResistiveGelBlock;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.Tags;

import static com.simibubi.create.AllTags.tagBlockAndItem;

public class CLBlocks {
    private static final CreateRegistrate REGISTRATE = CreateLightning.registrate()
            .creativeModeTab(() -> CreateLightning.BASE_CREATIVE_TAB);

    public static final BlockEntry<ResistiveGelBlock> RESISTIVE_GEL_BLOCK = REGISTRATE.block("resistive_gel_block", ResistiveGelBlock::new)
            .initialProperties(() -> Blocks.SLIME_BLOCK)
            .properties(p -> p.color(MaterialColor.COLOR_GRAY))
            .properties(p -> p.requiresCorrectToolForDrops())
            .tag(Tags.Blocks.STORAGE_BLOCKS)
            .tag(BlockTags.FALL_DAMAGE_RESETTING)
            .lang("Resistive Gel Block")
            .transform(tagBlockAndItem("storage_blocks/resistive_gel_block"))
            .tag(Tags.Items.STORAGE_BLOCKS)
            .build()
            .register();

    public static void register() {}
}
