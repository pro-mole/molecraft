package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class BugCoccineal extends Item {

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
