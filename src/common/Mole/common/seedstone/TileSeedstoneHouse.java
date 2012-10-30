package Mole.common.seedstone;

import net.minecraft.src.TileEntity;
import net.minecraft.src.WorldInfo;

public class TileSeedstoneHouse extends TileEntity {
	
	int X, Y, Z;
	int width, height;
	int blockID;
	int ticks = 0;
	boolean started = true;
	
	//Starts building a House structure from (x,y,z), with block of type blockID
	public TileSeedstoneHouse(int level, int x, int y, int z, int type)
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
		
		blockID = type;
		X = x;
		Y = y;
		Z = z;
	}
	
	//Every 5 seconds, put a block to form the walls
	@Override
	public void updateEntity()
	{	
		if (started)
		{
			if (ticks++ >= 20)
			{
				boolean place_block = false;
				//Check each position in a particular order
				//Until either finished with the house or an empty spot is found
				int i;
				int x,y,z;
				
				x = y = z = 0;
				for (i = 0; i < (4*height)+(2*width*width)+(4*width*height); i++)
				{
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
								if (k < 4*width*height)
								{
									y = l % height;
									switch(k / (width*height))
									{
										case 0:  x = -(width-1)/2;
											z = (int)(l / height) - (width-1)/2;
											break;
										case 1:  z = (width-1)/2;
											x = (int)(l / height % width) - (width-1)/2;
											break;
										case 2:  x = (width-1)/2;
											z = (int)-(l / height % width) + (width-1)/2;
											break;
										case 3:  z = -(width-1)/2;
											x = (int)-(l / height % width) + (width-1)/2;
											break;
									}
								}
								
							}
						}
					}
					
					if (worldObj.getBlockId(X+x, Y+y, Z+z) != this.blockID && !worldObj.isRemote)
					{
						worldObj.setBlock(X+x, Y+y, Z+z, this.blockID);
						place_block = true;
						break;
					}
				}
				
				//If everything is done, deactivate
				if (!place_block)
				{
					worldObj.removeBlockTileEntity(X, Y, Z);
				}
				
				ticks -= 20;
			}
		}
	}
}
