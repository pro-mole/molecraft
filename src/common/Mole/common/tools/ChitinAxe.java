package Mole.common.tools;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemAxe;

public class ChitinAxe extends ItemAxe {

	public ChitinAxe() {
		super(Constants.MOLE_ITEM_CHITIN_TOOLS, Constants.CHITIN_TOOL);
		setIconIndex(59);
		
		setItemName("axeChitin");
		LanguageRegistry.addName(this, "Chitin Axe");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
