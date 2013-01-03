package Mole.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BugCoccineal extends Item implements BugResource {

	public BugCoccineal() {
		super(Constants.MOLE_ITEM_BUG_COCCINEAL);
		
		setItemName("coccineal");
		setIconIndex(35);
		LanguageRegistry.addName(this, "Coccineal");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
