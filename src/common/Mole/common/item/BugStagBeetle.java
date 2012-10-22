package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemShears;
import net.minecraft.src.ItemStack;

public class BugStagBeetle extends ItemAxe {

	public BugStagBeetle()
	{
		super(Constants.MOLE_ITEM_BUG_STAGBEETLE, EnumToolMaterial.IRON);
		setItemName("beetleStag");
		setIconIndex(32);
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
		if (player.worldObj.getBlockId(x,y,z) == Block.leaves.blockID)
		{
			EntityItem sheared = new EntityItem(player.worldObj, x, y, z, new ItemStack(Block.blocksList[Block.leaves.blockID], 1, player.worldObj.getBlockMetadata(x, y, z)&3));
			sheared.delayBeforeCanPickup = 10;
			//player.worldObj.setBlock(x, y, z, 0);
			player.worldObj.spawnEntityInWorld(sheared);
		}
		
		return false;
	}
}
