package Mole.common.seedstone;

import net.minecraft.src.TileEntity;

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
				size = 3; break;
		}
		
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
			if (ticks++ >= 200)
			{
				//Check each position in a particular order
				//Until either finished with the house or an empty spot is found
				int i;
				int x,y,z;
				for (i = 0; i < 4*height; i++)
				{
					//Pillars
					if (i < 4*height)
					{
						x = ((int)(i / height) / 2 == 0)? -5: 5;
						y = i % height;
						z = ((int)(i / height) % 2 == 0)? -5: 5;
					}
					else
					{
						i -= 4*height;
						//Start with bottom, go over sides, finish with top
						if()
					}
				}
				
				//If everything is one, deactivate
				
				ticks -= 200;
			}
		}
	}
}
