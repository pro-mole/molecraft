package Mole.common.seedstone;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import Mole.common.PacketHandler;
import Mole.common.item.Seedstone.EnumSeedstoneType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileSeedstoneWell extends TileSeedstone implements SeedstoneTerraform
{
	boolean started = false;
	int index;
	
	//Starts building a House structure from (x,y,z), with block of type blockID
	public TileSeedstoneWell()
	{
		setType(EnumSeedstoneType.WELL);
		start();
	}
	
	public void start()
	{
		started = true;
	}
	
	public void updateEntity()
	{
		if (started)
		{
			if (ticks++ >= 10)
			{
				int i = index++;
				
				int x,y,z,block;
				
				//Walls
				if (i < 32)
				{
					x = (new int[] {2,2,2,2,2,1,1,0,0,-1,-1,-2,-2,-2,-2,-2})[i % 16];
					z = (new int[] {2,1,0,-1,-2,2,-2,2,-2,2,-2,2,1,0,-1,-2})[i % 16];
					y = (int)(i / 16) - 1;
					block = Block.stoneBrick.blockID;
				}
				else
				{ 
					int j = i-32;
					if (j < 9)
					{ //First layer of water
						x = (int)(j / 3) - 1;
						z = (j % 3) - 1;
						y = -1;
						block = Block.waterStill.blockID;
					}
					else
					{
						int k = j-9;
						if (k < 8)
						{ //Second layer of water
							x = (new int[] {1,1,1,0,0,-1,-1,-1})[k];
							z = (new int[] {-1,0,1,-1,1,-1,0,1})[k];
							y = 0;
							block = Block.waterStill.blockID;
						}
						else
						{ //Pack it up!
							x = y = z = 0;
							block = Block.waterStill.blockID;
						}
					}
				}
				
				//Finish
				worldObj.setBlockWithNotify(xCoord+x, yCoord+y, zCoord+z, block);
				if (x == 0 && y == 0 && z == 0)
					worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
				
				ticks -= 100;
			}
		}
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		System.out.println("Loading TE Seedstone at "+"["+xCoord+","+yCoord+","+zCoord+"]"+FMLCommonHandler.instance().getEffectiveSide());
		
		index = tagCompound.getInteger("Pointer");
		ticks = tagCompound.getInteger("Ticks");
		started = true;
    }
	 
	 @Override
    public void writeToNBT(NBTTagCompound tagCompound)
	{       
		System.out.println("Saving TE Seedstone at "+"["+xCoord+","+yCoord+","+zCoord+"]"+FMLCommonHandler.instance().getEffectiveSide());
		
		TileEntity TE = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
		
	 	super.writeToNBT(tagCompound);
	 	
        tagCompound.setInteger("Pointer", index);
        tagCompound.setInteger("Ticks", ticks);
        
        try {
			PacketDispatcher.sendPacketToAllPlayers(PacketHandler.createSeedstonePackage(xCoord, yCoord, zCoord, new int[] {index, ticks} ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
