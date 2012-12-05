package Mole.common.tools;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemPickaxe;

public class ChitinPickaxe extends ItemPickaxe {

	public ChitinPickaxe() {
		super(Constants.MOLE_ITEM_CHITIN_TOOLS+2, Constants.CHITIN_TOOL);
		setIconIndex(61);

		setItemName("pickChitin");
		LanguageRegistry.addName(this, "Chitin Pickaxe");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
}
