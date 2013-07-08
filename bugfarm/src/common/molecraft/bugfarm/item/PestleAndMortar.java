package common.molecraft.bugfarm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import common.molecraft.bugfarm.constants.Numbers;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PestleAndMortar extends Item {

	int[] tierDamage = {32,128};
	String[] tierLocalName = {"wood","stone"};
	
	public PestleAndMortar(int tier) {
		super(Numbers.ITEM_PESTLEANDMORTAR+tier);
		
		//Works as a tool, sort of
		this.setMaxDamage(tierDamage[tier]);
		this.setMaxStackSize(1);
		
		this.setUnlocalizedName("pestle_mortar_"+tierLocalName[tier]);
		LanguageRegistry.addName(this, "Pestle & Mortar");
		this.setCreativeTab(CreativeTabs.tabTools);
	}
}