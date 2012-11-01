package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemFood;

public class BugMealWorm extends ItemFood {

	public BugMealWorm()
	{
		super(Constants.MOLE_ITEM_BUG_MEALWORM, 4, false);
		
		setItemName("mealWorm");
		setIconIndex(33);
		LanguageRegistry.addName(this, "Mealworm");
		setCreativeTab(CreativeTabs.tabFood);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
