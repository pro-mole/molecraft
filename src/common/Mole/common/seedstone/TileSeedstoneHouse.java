package Mole.common.seedstone;

import Mole.common.Constants;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;
import net.minecraft.src.WorldInfo;

public class TileSeedstoneHouse extends TileEntity {
	
	int index = 0;
	int width, height;
	int blockID;
	int ticks = 0;
	boolean started = false;
	
	//Starts building a House structure from (x,y,z), with block of type blockID
	public TileSeedstoneHouse(int level, int type)
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
		
		setType(type);
		start();
	}
	
	public void setType(int type)
	{
		blockID = type;
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
			if (ticks++ >= 20)
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
					return;
				}
				
				int x,y,z;
				x = y = z = 0;
				//Pillars
				if (i < 4*height)
				{
					x = ((int)(i / height) / 2 == 0)? -(width-1)/2: (width-1)/2;
					y = i % height;
					z = ((int)(i / height) % 2 == 0)? x: -x;
				}
				else
				{
					int j = i - 4*height;
					//Start with bottom, then top, finish with sides
					if (j < width*width)
					{
						y = 0;
						x = j % width - (width-1)/2;
						z = (int)(j / width) - (width-1)/2;
					}
					else
					{
						int k = j - width*width;
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
				
				if (worldObj.getBlockId(xCoord+x, yCoord+y, zCoord+z) != this.blockID && worldObj.getBlockId(xCoord+x, yCoord+y, zCoord+z) != Constants.MOLE_BLOCK_SEEDSTONE && !worldObj.isRemote)
				{
					worldObj.setBlock(xCoord+x, yCoord+y, zCoord+z, this.blockID);
				}
				
				worldObj.notifyBlockChange(xCoord, yCoord, zCoord, blockID);
				
				ticks -= 20;
			}
		}
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound)
	{
		System.out.println("Loading TE Seedstone at "+xCoord+":"+yCoord+":"+zCoord);
		super.readFromNBT(tagCompound);
		index = tagCompound.getInteger("Pointer");
		blockID = tagCompound.getInteger("BlockID");
		height = tagCompound.getInteger("H");
		width = tagCompound.getInteger("W");
    }
	 
	 @Override
    public void writeToNBT(NBTTagCompound tagCompound)
	{       
		System.out.println("Saving TE Seedstone at "+xCoord+":"+yCoord+":"+zCoord);
	 	super.writeToNBT(tagCompound);
	 	tagCompound.setInteger("Pointer", index);
        tagCompound.setInteger("BlockID", blockID);
        tagCompound.setInteger("H", height);
        tagCompound.setInteger("W", width);
    }
}
