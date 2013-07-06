package common.molecraft.bugfarm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import common.molecraft.bugfarm.constants.Numbers;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PestleAndMortar extends Item {

	public PestleAndMortar() {
		super(Numbers.ITEM_PESTLEANDMORTAR);
		
		//Works as a tool, sort of
		this.setMaxDamage(32);
		this.setMaxStackSize(1);
		
		this.setUnlocalizedName("pestle_mortar");
		LanguageRegistry.addName(this, "Pestle & Mortar");
		this.setCreativeTab(CreativeTabs.tabTools);
	}
}