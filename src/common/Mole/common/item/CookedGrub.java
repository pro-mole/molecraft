package Mole.common.item;

import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemFood;

public class CookedGrub extends ItemFood {
	
	public CookedGrub()
	{
		super(Constants.MOLE_ITEM_GRUB_COOKED, 2, false);
		this.maxStackSize = 64;
		this.setTabToDisplayOn(CreativeTabs.tabFood);
		this.setIconIndex(16);
		this.setItemName("grubCook");

		LanguageRegistry.addName(this, "Cooked Grub");
	}
	
	public String getTextureFile()
	{
		return "/Mole/common/resources/items.png";
	}
}
