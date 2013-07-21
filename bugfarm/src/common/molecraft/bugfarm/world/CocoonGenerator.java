package common.molecraft.bugfarm.world;

import java.util.Random;

import common.molecraft.bugfarm.MolecraftBugfarm;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.IWorldGenerator;

public class CocoonGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
		//System.out.println("["+chunk.xPosition+","+chunk.zPosition+"]");
		
		//Let's lay down how many cocoons we have to put in this chunk
		int max = random.nextInt(4);
		
		//Try a bunch of times
		for(int t = 0; t < 20 && max > 0; t++)
		{
			int x = chunkX*16 + random.nextInt(16);
			int z = chunkZ*16 + random.nextInt(16);
			int y = random.nextInt(64) + 64;
			
			//System.out.println("Try "+t+":"+x+","+y+","+z);
			int block = world.getBlockId(x, y, z);
			
			//If we find air, go down
			while (block == 0)
			{
				block = world.getBlockId(x, --y, z);
			}
			
			//If we find leaves, let's try and find a tree!
			if (block == Block.leaves.blockID)
			{
				//Again, go down
				while (block == Block.leaves.blockID)
				{
					block = world.getBlockId(x, --y, z);
				}
				
				//if we didn't find the trunk, let's try looking around
				if (block != Block.wood.blockID)
				{
					for (ForgeDirection dir: new ForgeDirection[] {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST})
					{
						if (world.getBlockId(x + dir.offsetX, y, z + dir.offsetZ) == Block.wood.blockID)
						{
							x += dir.offsetX;
							z += dir.offsetZ;
							block = world.getBlockId(x, y, z);
							break;
						}
					}
				}
			}
			
			//If we find a trunk, then we get to shine :D
			if (block == Block.wood.blockID)
			{
				switch(random.nextInt(4))
				{
					case 0:
						x -= 1; break;
					case 1:
						x += 1; break;
					case 2:
						z -= 1; break;
					case 3:
						z += 1; break;
				}
				
				System.out.println("[CHUNK "+chunkX+","+chunkZ+"]Cocoon placed at ("+x+","+y+","+z+") on try "+t+"!");
				world.setBlock(x, y, z, MolecraftBugfarm.cocoon[random.nextInt(3)].blockID);
				max--;
			}
			
			//Otherwise, don't bother
		}
		
	}

}
