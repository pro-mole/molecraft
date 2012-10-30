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
		
		//Little trick: Dirtstone can be planted
		if (Mole.isDirtLike(world.getBlockId(x, y, z)) || world.getBlockId(x, y, z) == Constants.MOLE_BLOCK_DIRTSTONE)
		{
			if (seedType == 0)
			{
				switch(world.getBlockId(x, y, z))
				{
					case Block.grass.blockID:
						world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, x, y, z, Block.dirt.blockID)
						break;
					case Block.sand.blockID:
						world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, x, y, z, Block.sandStone.blockID)
						break;
					case Block.gravel.blockID:
						world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, x, y, z, Block.cobbleStone.blockID)
						break;
					default:
						world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, x, y, z, world.getBlockId(x, y, z)));
						break;
				}
			}
			
			stack.stackSize--;
			return true;
		}
		
		return false;
    }
}
