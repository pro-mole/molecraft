package Mole.common.seedstone;

import net.minecraft.src.TileEntity;

public class TileSeedstoneHouse extends TileEntity {
	
	int frameX, frameY, frameZ;
	int size;
	int blockID;
	int ticks = 0;
	boolean started = true;
	
	//Starts building a House structure from (x,y,z), with block of type blockID
	public TileSeedstoneHouse(int level, int x, int y, int z, int type)
	{
		switch (level)
		{
			case 1:
				size = 3; break;
			case 2:
				size = 4; break;
			case 3:
				size = 4; break;
			default:
				size = 3; break;
		}
		
		frameX = x - size;
		frameY = y;
		frameZ = z - size;
	}
	
	//Every 5 seconds, put a block to form the walls
	@Override
	public void updateEntity()
	{
		if (started)
		{
			if (ticks++ >= 200)
			{
				//Insert block at position, if not already there
				worldObj.setBlock(frameX, frameY, frameZ, blockID);
				
				//Move to next viable position, in order:
				// 1 - Pillars
				if (frameY == 0)
				{
					if (frameX == -size)
					{
						
					}
					else if (frameX == size)
					{
						
					}
				}
				// 2 - Floor
				// 3 - Ceiling
				// 4 - Walls
				
				//If everything is one, deactivate
				ticks -= 200;
			}
		}
	}
}
