package Mole.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BugHusk extends Item {

	public BugHusk()
	{
		super(Constants.MOLE_ITEM_BUG_HUSK);
		
		setItemName("emptyHusk");
		setIconIndex(32);
		LanguageRegistry.addName(this, "Empty Husk");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
