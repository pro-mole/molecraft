package Mole.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BugFood extends Item {
	
	public BugFood(boolean premium)
	{
		super(premium? Constants.MOLE_ITEM_BUGFOOD_PREMIUM: Constants.MOLE_ITEM_BUGFOOD);
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setIconIndex(13+(premium?1:0));
		this.setItemName("bugFood"+(premium?"Premium":""));

		LanguageRegistry.addName(this, (premium?"Premium ":"")+"Bug Food");
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
