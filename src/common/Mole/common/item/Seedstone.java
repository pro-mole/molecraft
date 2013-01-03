package Mole.common.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.seedstone.TileSeedstoneHouse;
import Mole.common.seedstone.TileSeedstoneWell;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Seedstone extends Item {

	public static enum EnumSeedstoneType
	{
		HOUSE, WELL
	};
	
	public static String[] itemNames = {"seedstoneHouse", "seedstoneWell"};
	public static String[] uiNames = {"House Seedstone", "Well Seedstone"};
	
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
		//Chunk chunk = world.getChunkFromBlockCoords(x, z);
		//ChunkPosition pos = new ChunkPosition(x & 15, y, y & 15);
		//Little trick: Dirtstone can be planted
		if (Mole.isDirtLike(blockId) || blockId == Constants.MOLE_BLOCK_DIRTSTONE)
		{
			world.setBlock(x, y, z, Constants.MOLE_BLOCK_SEEDSTONE);
			if (seedType == EnumSeedstoneType.HOUSE.ordinal())
			{
				if (world.getBlockTileEntity(x, y, z) != null)
				{
					System.out.println("Already occupied; oops");
					return false;
				}
				
				TileSeedstoneHouse _TE;
				if (blockId == Block.grass.blockID)
				{
					_TE = new TileSeedstoneHouse(1, Block.dirt.blockID);
					world.setBlockTileEntity(x, y, z, _TE);
//					chunk.chunkTileEntityMap.put(pos, _TE);
				}
				else if (blockId == Block.sand.blockID)
				{
					_TE = new TileSeedstoneHouse(1, Block.sandStone.blockID);
					world.setBlockTileEntity(x, y, z, _TE);
//					chunk.chunkTileEntityMap.put(pos, _TE);
				}
				else if (blockId == Block.gravel.blockID)
				{
					_TE = new TileSeedstoneHouse(1, Block.cobblestone.blockID);
					world.setBlockTileEntity(x, y, z, _TE);
//					chunk.chunkTileEntityMap.put(pos, _TE);
				}
				else
				{
					_TE = new TileSeedstoneHouse(1, blockId);
					world.setBlockTileEntity(x, y, z, _TE);
//					chunk.chunkTileEntityMap.put(pos, _TE);
				}
			}
			
			if (seedType == EnumSeedstoneType.WELL.ordinal())
			{
				if (world.getBlockTileEntity(x, y, z) != null)
				{
					System.out.println("Already occupied; oops");
					return false;
				}
				
				TileSeedstoneWell _TE;
				_TE = new TileSeedstoneWell();
				world.setBlockTileEntity(x, y, z, _TE);
			}
			
			stack.stackSize--;
			return true;
		}
		
		return false;
    }
}
