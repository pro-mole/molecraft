package Mole.common.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ChitinSword extends ItemSword {

	public ChitinSword() {
		super(Constants.MOLE_ITEM_CHITIN_TOOLS+4, Constants.CHITIN_TOOL);
		setIconIndex(63);
		
		setItemName("swordChitin");
		LanguageRegistry.addName(this, "Chitin Sword");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
