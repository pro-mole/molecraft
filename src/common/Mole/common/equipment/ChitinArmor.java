package Mole.common.equipment;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import Mole.common.Mole;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ChitinArmor extends ItemArmor implements IArmorTextureProvider {

	static String[] itemNames = {"chitinHelm", "chitinPlate", "chitinLegs", "chitinBoots"};
	static String[] displayNames = {"Chitin Helmet", "Chitin Plate", "Chitin Legging", "Chitin Boots"};
	
	public ChitinArmor(int type) {
		super(Constants.MOLE_ITEM_CHITIN_ARMOR+type, Constants.CHITIN_ARMOR, 0, type);
		
		setIconIndex(44 + type);
		setItemName(itemNames[type]);
		LanguageRegistry.addName(this, displayNames[type]);
		setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public String getArmorTextureFile(ItemStack armor)
    {
	    if(armor.itemID == Mole.armorChitin[Constants.MOLE_ARMOR_LEGS].shiftedIndex)
            return "/Mole/common/resources/chitin_2.png";
	    else
	    	return "/Mole/common/resources/chitin_1.png";
	    	
    }
	
	@Override
	public String getTextureFile()
	{
		return "/Mole/common/resources/items.png";
	}

}
