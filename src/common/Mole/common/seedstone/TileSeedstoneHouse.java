package Mole.common.seedstone;

import java.io.IOException;

import javax.sound.midi.Soundbank;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

import Mole.common.Constants;
import Mole.common.PacketHandler;
import Mole.common.item.Seedstone.EnumSeedstoneType;
import Mole.common.machine.TileSeedstone;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.StepSound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.WorldInfo;

public class TileSeedstoneHouse extends TileSeedstone {
	
	int index = 0;
	int width, height;
	int blockID;
	int ticks = 0;
	boolean started = false;
	
	//Starts building a House structure from (x,y,z), with block of type blockID
	public TileSeedstoneHouse(int level, int type)
	{
		setType(EnumSeedstoneType.HOUSE);
		switch (level)
		{
			case 1:
				width = 7;
				height = 5; break;
			case 2:
				width = 7;
				height = 6; break;
			case 3:
				width = 9;
				height = 6; break;
			default:
				width = 7;
				height = 5; break;
		}
		
		setType(type);
		start();
	}
	
	//Constructor for initialization
	public TileSeedstoneHouse()
	{
		setType(EnumSeedstoneType.HOUSE);
		System.out.println("Initialized Seedstone at "+xCoord+":"+yCoord+":"+zCoord);
		started = true;
	}
	
	public void setType(int type)
	{
		blockID = type;
	}
	
	public void setDimensions(int level)
	{
		switch (level)
		{
			case 1:
				width = 7;
				height = 5; break;
			case 2:
				width = 7;
				height = 6; break;
			case 3:
				width = 9;
				height = 6; break;
			default:
				width = 7;
				height = 5; break;
		}
	}
	
	public void setCycleData(int index, int ticks)
	{
		this.ticks = ticks;
		this.index = index;
	}
	
	public void start()
	{
		started = true;
		System.out.println("Start the Seed!");
	}
	
	//Every 5 seconds, put a block to form the walls
	@Override
	public void updateEntity()
	{	
		if (started)
		{
			if (ticks++ >= 100)
			{
				//Check each position in a particular order
				//Until either finished with the house or an empty spot is found
				int i = index++;
				
				//If everything is done, deactivate
				if (i >= 4*height + 2*width*width + 4 * height*width)
				{
					started = false;
					worldObj.setBlock(xCoord, yCoord, zCoord, this.blockID);
					ticks = 0;
					worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
					return;
				}
				
				int x,y,z;
				x = y = z = 0;
				//Pillars
				if (i < width*width)
				{
					y = 0;
					x = i % width - (width-1)/2;
					z = (int)(i / width) - (width-1)/2;
				}
				else
				{
					int j = i - width*width;
					//Start with bottom, then top, finish with sides
					if (j < 4*height)
					{
						x = ((int)(j / height) / 2 == 0)? -(width-1)/2: (width-1)/2;
						y = j % height;
						z = ((int)(j / height) % 2 == 0)? x: -x;
					}
					else
					{
						int k = j - 4*height;
						//Do the top, yo
						if (k < width*width)
						{
							y = height-1;
							x = k % width - (width-1)/2;
							z = (int)(k / width) - (width-1)/2;
						}
						else
						{
							int l = k - width*width;
							//4 Sides. Go go go!
							if (l < 4*width*height)
							{
								y = l % height;
								switch(l / (width*height))
								{
									case 0:  x = -(width-1)/2;
										z = (int)(l / height) - (width-1)/2;
										break;
									case 1:  z = (width-1)/2;
										x = (int)(l / height) % width - (width-1)/2;
										break;
									case 2:  x = (width-1)/2;
										z = (int)-(l / height) % width + (width-1)/2;
										break;
									case 3:  z = -(width-1)/2;
										x = (int)-(l / height) % width + (width-1)/2;
										break;
								}
							}
						}
					}
				}
				
				System.out.println("["+x+","+y+","+z+"]");
				System.out.println(FMLCommonHandler.instance().getEffectiveSide());
				
				if (worldObj.getBlockId(xCoord+x, yCoord+y, zCoord+z) != this.blockID && worldObj.getBlockId(xCoord+x, yCoord+y, zCoord+z) != Constants.MOLE_BLOCK_SEEDSTONE && !worldObj.isRemote)
				{
					worldObj.playSound(x, y, z, "step.grass", 100f, 0f);
					worldObj.setBlock(xCoord+x, yCoord+y, zCoord+z, this.blockID);
				}
				else
				{
					updateEntity();
					return;
				}
			
				
				//worldObj.notifyBlockChange(xCoord, yCoord, zCoord, blockID);
				
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
		height = tagCompound.getInteger("H");
		width = tagCompound.getInteger("W");
		blockID = tagCompound.getInteger("Block");
		ticks = tagCompound.getInteger("Ticks");
    }
	 
	 @Override
    public void writeToNBT(NBTTagCompound tagCompound)
	{       
		System.out.println("Saving TE Seedstone at "+"["+xCoord+","+yCoord+","+zCoord+"]"+FMLCommonHandler.instance().getEffectiveSide());
		
		TileEntity TE = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
		
	 	super.writeToNBT(tagCompound);
	 	
        tagCompound.setInteger("Pointer", index);
        tagCompound.setInteger("H", height);
        tagCompound.setInteger("W", width);
        tagCompound.setInteger("Block", blockID);
        tagCompound.setInteger("Ticks", ticks);
        
        try {
			PacketDispatcher.sendPacketToAllPlayers(PacketHandler.createSeedstonePackage(xCoord, yCoord, zCoord, new int[] {index, ticks, blockID, (int)Math.ceil((height+width-10)/2.0)}));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
