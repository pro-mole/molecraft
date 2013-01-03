package Mole.common.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ChitinSpade extends ItemSpade {

	public ChitinSpade() {
		super(Constants.MOLE_ITEM_CHITIN_TOOLS+1, Constants.CHITIN_TOOL);
		setIconIndex(60);
		
		setItemName("spadeChitin");
		LanguageRegistry.addName(this, "Chitin Shovel");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
