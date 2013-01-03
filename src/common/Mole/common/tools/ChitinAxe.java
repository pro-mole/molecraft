package Mole.common.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ChitinAxe extends ItemAxe {

	public ChitinAxe() {
		super(Constants.MOLE_ITEM_CHITIN_TOOLS, Constants.CHITIN_TOOL);
		setIconIndex(59);
		
		setItemName("axeChitin");
		LanguageRegistry.addName(this, "Chitin Axe");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
