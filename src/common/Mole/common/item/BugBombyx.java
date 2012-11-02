package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class BugBombyx extends Item {

	public BugBombyx()
	{
		super(Constants.MOLE_ITEM_BUG_BOMBYX);
		
		setItemName("bombyx");
		setIconIndex(34);
		setMaxDamage(100);
		LanguageRegistry.addName(this, "Bombyx Mori");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
