package Mole.common.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ChitinHoe extends ItemHoe {

	public ChitinHoe() {
		super(Constants.MOLE_ITEM_CHITIN_TOOLS+3, Constants.CHITIN_TOOL);
		setIconIndex(62);

		setItemName("hoeChitin");
		LanguageRegistry.addName(this, "Chitin Hoe");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
