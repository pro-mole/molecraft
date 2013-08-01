package common.molecraft.bugfarm.world;

import java.util.Random;

import common.molecraft.bugfarm.MolecraftBugfarm;
import common.molecraft.bugfarm.constants.Numbers;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.IWorldGenerator;

public class GrubGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		generateCocoons(random, world, chunkX, chunkZ, 2);
		generateBurrows(random, world, chunkX, chunkZ, 1);
	}
	
	private void generateBurrows(Random random, World world, int chunkX,
			int chunkZ, int burrowDensity) {
		//Let's lay down how many burrows we have to put in this chunk
		int max = random.nextInt(burrowDensity+1);
		
		//Try a bunch of times
		for(int t = 0; t < 25 && max > 0; t++)
		{
			int x = chunkX*16 + random.nextInt(16);
			int z = chunkZ*16 + random.nextInt(16);
			int y = world.getHeightValue(x, z)-1;
			
			int block = world.getBlockId(x, y, z);
			
			while(block == 0 || block == Block.leaves.blockID || block == Block.waterStill.blockID || block == Block.waterMoving.blockID)
			{
				block = world.getBlockId(x, --y, z);
			}
			
			//For burrows it's a lot easier
			if (block == Block.dirt.blockID || block == Block.grass.blockID)
			{
				world.setBlock(x, y, z, Numbers.BLOCK_BURROW);
				max--;
			}
			
			if (block == Block.sand.blockID)
			{
				world.setBlock(x, y, z, Numbers.BLOCK_BURROW+1);
				max--;
			}
			
			if (block == Block.blockClay.blockID)
			{
				world.setBlock(x, y, z, Numbers.BLOCK_BURROW+2);
				max--;
			}
			
			if (block == Block.netherrack.blockID)
			{
				world.setBlock(x, y, z, Numbers.BLOCK_BURROW+3);
				max--;
			}
		}
	}

	public void generateCocoons(Random random, World world, int chunkX, int chunkZ, int cocoonDensity)
	{
		//Let's lay down how many cocoons we have to put in this chunk
		int max = random.nextInt(cocoonDensity+1);
		
		//Try a bunch of times
		for(int t = 0; t < 25 && max > 0; t++)
		{
			int x = chunkX*16 + random.nextInt(16);
			int z = chunkZ*16 + random.nextInt(16);
			int y = world.getHeightValue(x, z)-1;
			if (y == 0) System.err.println("What the hell is going on?");
			
			//System.out.println("Try "+t+":"+x+","+y+","+z);
			int block = world.getBlockId(x, y, z);
			
			//Let's change this and only bother if we find wood right away
			//If this is a forest, it can't be that hard
			while (block == Block.leaves.blockID)
			{
				block = world.getBlockId(x, --y, z);
			}
			
			if (block == Block.wood.blockID)
			{
				int dy = 0;
				while (world.getBlockId(x, y, z) == Block.wood.blockID)
				{
					dy++;
					y--;
				}
				
				y += random.nextInt(dy);
				
				int direction = random.nextInt(4); 
				while (world.getBlockId(x, y, z) == Block.wood.blockID)
				{
					switch(direction)
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
				}
				
				world.setBlock(x, y, z, MolecraftBugfarm.cocoon[random.nextInt(3)].blockID);
				max--;
			}
		}
	}

}
