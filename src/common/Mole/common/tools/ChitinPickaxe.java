package Mole.common.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ChitinPickaxe extends ItemPickaxe {

	public ChitinPickaxe() {
		super(Constants.MOLE_ITEM_CHITIN_TOOLS+2, Constants.CHITIN_TOOL);
		setIconIndex(61);

		setItemName("pickChitin");
		LanguageRegistry.addName(this, "Chitin Pickaxe");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
