package Mole.common.tools;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemSword;

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
