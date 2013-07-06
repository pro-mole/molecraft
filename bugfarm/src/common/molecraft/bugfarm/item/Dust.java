package common.molecraft.bugfarm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import common.molecraft.bugfarm.constants.Numbers;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class Dust extends Item {

	public static enum DustType {DUST_WOOD, DUST_REED, DUST_MEAT, DUST_LEAF, DUST_GRUB, DUST_GOURD, DUST_INK, DUST_FISH, DUST_FUNGUS, COUNT};

	public static String[]
		ids = {"woodDust", "reedDust", "meatDust", "leafDust", "grubDust", "gourdDust", "inkDust", "fishDust", "fungusDust"},
		names = {"Wood Dust", "Reed Dust", "Meat Dust", "Leaf Dust", "Grub Dust", "Gourd Dust", "Ink Dust", "Fish Dust", "Fungus Dust"};
	
	public Dust(DustType type) {
		super(Numbers.ITEM_DUST + type.ordinal());
		int _type = type.ordinal();
		
		this.setUnlocalizedName(ids[_type]);
		LanguageRegistry.addName(this, names[_type]);
		this.setCreativeTab(CreativeTabs.tabFood);
	}
		
}