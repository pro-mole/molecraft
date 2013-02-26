package Mole.common.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BugStagBeetle extends ItemAxe implements AdultBugTool {

	public BugStagBeetle()
	{
		super(Constants.MOLE_ITEM_BUG_STAGBEETLE, EnumToolMaterial.IRON);
		setItemName("beetleStag");
		setIconIndex(33);
		setMaxDamage(50);
		LanguageRegistry.addName(this, "Stag Beetle");
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
	{	
		if (player.worldObj.isRemote)
			return false;
		
		//System.out.println("Cutting "+player.worldObj.getBlockId(x,y,z));
		int cut_block = player.worldObj.getBlockId(x,y,z);
		if (cut_block == Block.leaves.blockID || cut_block == Block.tallGrass.blockID || cut_block == Block.vine.blockID)
		{
			EntityItem sheared = new EntityItem(player.worldObj, x, y, z, new ItemStack(player.worldObj.getBlockId(x, y, z), 1, player.worldObj.getBlockMetadata(x, y, z)));
			sheared.delayBeforeCanPickup = 10;
			player.worldObj.setBlockWithNotify(x, y, z, 0);
			player.worldObj.spawnEntityInWorld(sheared);
			return true;
		}
		
		return false;
	}

	@Override
	public int getFixAmount() {
		return 5;
	}
}
