package Mole.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BugBombyx extends Item implements BugProduce{

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
