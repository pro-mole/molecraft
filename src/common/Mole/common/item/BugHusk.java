package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

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
