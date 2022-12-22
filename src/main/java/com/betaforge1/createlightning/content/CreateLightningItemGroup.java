package com.betaforge1.createlightning.content;

import com.betaforge1.createlightning.CLItems;
import com.betaforge1.createlightning.CreateLightning;
import com.simibubi.create.content.AllSections;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class CreateLightningItemGroup extends CreativeModeTab {

	public CreateLightningItemGroup() {
		super(CreateLightning.MODID + ":" + "base");
	}

	protected EnumSet<AllSections> getSections() {
		return EnumSet.complementOf(EnumSet.of(AllSections.PALETTES));
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(CLItems.RESISTIVE_GEL.get());
	}

}
