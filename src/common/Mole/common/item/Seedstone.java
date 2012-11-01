package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.seedstone.TileSeedstoneHouse;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class Seedstone extends Item {

	public static enum EnumSeedstoneType
	{
		HOUSE
	};
	
	public static String[] itemNames = {"seedstoneHouse"};
	public static String[] uiNames = {"House Seedstone"};
	
	int seedType;
	
	public Seedstone(EnumSeedstoneType type)
	{
		this(type.ordinal());
	}
	
	public Seedstone(int type)
	{
		super(Constants.MOLE_ITEM_SEEDSTONE+type);
		setIconIndex(80+type);
		seedType = type;
		
		setItemName(itemNames[type]);
		LanguageRegistry.addName(this, uiNames[type]);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz)
    {
		if (world.isRemote) return false;
		
		System.out.println("USE SEEDSTONE["+x+","+y+","+z+","+side+","+px+","+py+","+pz+"]");
		
		int blockId = world.getBlockId(x, y, z);
		//Little trick: Dirtstone can be planted
		if (Mole.isDirtLike(blockId) || blockId == Constants.MOLE_BLOCK_DIRTSTONE)
		{
			if (seedType == 0)
			{
				if (blockId == Block.grass.blockID)
				{
					world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, Block.dirt.blockID));
				}
				else if (blockId == Block.sand.blockID)
				{
					world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, Block.sandStone.blockID));
				}
				else if (blockId == Block.gravel.blockID)
				{
					world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, Block.cobblestone.blockID));
				}
				else
				{
					world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, blockId));
				}
			}
			
			stack.stackSize--;
			return true;
		}
		
		return false;
    }
}
