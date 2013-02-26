package Mole.common.item;

import java.util.HashMap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BugCoccineal extends Item implements AdultBugProduce {

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

	@Override
	public HashMap<Object, Integer> getProduceList() {
		return new HashMap<Object, Integer>() {{ 
			put(new ItemStack(Item.dyePowder,1,1), 9);
			put(new ItemStack(Item.redstone), 1);
		}};
	}
}
