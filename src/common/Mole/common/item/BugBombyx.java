package Mole.common.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BugBombyx extends Item implements AdultBugProduce{

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

	@Override
	public HashMap<Object, Integer> getProduceList() {
		// TODO Auto-generated method stub
		return new HashMap<Object, Integer>() {{ put(new ItemStack(Item.silk), 1); }};
	}
}
