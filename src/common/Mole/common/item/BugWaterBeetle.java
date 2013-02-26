package Mole.common.item;

import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BugWaterBeetle extends Item implements AdultBugTool {

	//Provides the player with air underwater
	
	public BugWaterBeetle() {
		super(Constants.MOLE_ITEM_BUG_WATERBEETLE);
		setItemName("beetleWater");
		setIconIndex(36);
		setMaxDamage(50);
		LanguageRegistry.addName(this, "Water Beetle");
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		int air = player.getAir();
		
		if (air >= 300) return stack;
		
		player.setAir(air + 30);
		stack.damageItem(1, player);
		return stack;
	}

	@Override
	public String getTextureFile() {
		return Constants.MOLE_TEXT_ITEMS;
	}

	@Override
	public int getFixAmount() {
		return 5;
	}

}
