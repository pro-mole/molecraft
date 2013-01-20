package Mole.common.world;

import java.util.Random;

import Mole.common.Constants;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class MolecraftWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		//Super fine debugging here
		//System.out.println("Changing world in chunk "+chunkX+";"+chunkZ);
		
		//Standing stones, hut hut
		if (world.provider.dimensionId == 0 && random.nextInt(25) == 0)
		{
			int X = chunkX*16 + (6 + random.nextInt(4));
			int Y = 128;
			int Z = chunkZ*16 + (6 + random.nextInt(4));
			boolean treeStone = false, waterStone = false;
			
			while (world.isAirBlock(X, Y, Z) && Y > 0)
			{
				Y--;
			}
			
			boolean isDirt = false;
			for (Block bID: new Block[] {Block.dirt,
					Block.grass,
					Block.sand,
					Block.tallGrass,
					Block.plantRed,
					Block.plantYellow,
					Block.gravel})
			{
				if (world.getBlockId(X,Y,Z) == bID.blockID)
				{
					isDirt = true;
					break;
				}
			}
			
			if (world.getBlockId(X, Y, Z) == Block.waterStill.blockID && random.nextInt(5) == 0)
			{
				while(world.getBlockId(X, Y, Z) == Block.waterStill.blockID)
					Y--;
				waterStone = true;
				System.out.println("WATER STONE AT ["+X+","+Y+","+Z+"]");
			}
			
			if (world.getBlockId(X, Y, Z) == Block.leaves.blockID && world.getBiomeGenForCoords(X, Z) == BiomeGenBase.jungle && random.nextInt(5) == 0)
			{
				while(world.getBlockId(X, Y, Z) == Block.leaves.blockID)
					Y--;
				treeStone = true;
				System.out.println("TREE STONE AT ["+X+","+Y+","+Z+"]");
			}
			
			if (Y > 0 && (isDirt || waterStone || treeStone))
			{
				int n=1;
				//incrementally increase number of stones in circle by chance
				for (int i=1; i<4; i++)
					if (random.nextFloat() < 0.25) n++;
				
				switch(n)
				{
					case 1:
						generateStandingStone(world, X, Y, Z, random);
						break;
					case 2:
						if (random.nextInt(2) == 0)
						{
							generateStandingStone(world, X, Y, Z+1, random);
							generateStandingStone(world, X, Y, Z-2, random);
						}
						else
						{
							generateStandingStone(world, X+1, Y, Z, random);
							generateStandingStone(world, X-2, Y, Z, random);
						}
						break;
					case 3:
						generateStandingStone(world, X+1, Y, Z+1, random);
						generateStandingStone(world, X+1, Y, Z-3, random);
						generateStandingStone(world, X-2, Y, Z-1, random);
						break;
					case 4:
						generateStandingStone(world, X-2, Y, Z-2, random);
						generateStandingStone(world, X-2, Y, Z+1, random);
						generateStandingStone(world, X+1, Y, Z-2, random);
						generateStandingStone(world, X+1, Y, Z+1, random);
						break;
					default:
						System.err.println("Standing Stone circles should not have "+n+" stones!");
				}
				generateStoneCave(world, X, Y, Z, n, random);
			}
		}
	}

	private void generateStandingStone(World world, int X, int Y, int Z, Random random)
	{
		int block = world.getBlockId(X, Y-1, Z);
		
		System.out.println("STANDING STONE AT ["+X+","+Y+","+Z+"]");
		int w = random.nextInt(2) + 1,
			l = random.nextInt(2) + 1,
			h = random.nextInt(5) + 3;
		
		for (int i=0; i<2; i++)
			for (int j=0; j<2; j++)
				world.setBlockWithNotify(X+i, Y-1, Z+j, block);
		
		for (int i = 0; i < w; i++)
			for (int j = 0; j < l; j++)
			{
				int over = random.nextInt(2);
				for (int k = 0; k < h+over; k++)
					world.setBlockWithNotify(X+i, Y+k+1, Z+j, Constants.MOLE_BLOCK_STANDINGSTONE);
			}
	}
	
	private void generateStoneCave(World world, int X, int Y, int Z, int stones, Random random)
	{
		int tries = stones;
		boolean cave = false;
		while (tries > 0 && !cave)
		{
			cave = random.nextFloat() < 0.15;
			tries--;
		}
		
		if (!cave) return;
			
		int depth = 2 + random.nextInt(3);
		System.out.println("STONE CAVE AT ["+X+","+Y+","+Z+"]");
		
		int w1 = 1+random.nextInt(3),
			w2 = 1+random.nextInt(3),
			l1 = 1+random.nextInt(3),
			l2 = 1+random.nextInt(3),
			h = 3+random.nextInt(3);
		
		for (int i = 0; i < h; i++)
		{
			for (int j = -w1; j <= w2; j++)
				for (int k = -l1; k <= l2; k++)
				{
					if (world.getBlockId(X+j, Y-i-depth+1, Z+k) == Block.sand.blockID)
						world.setBlockWithNotify(X+j, Y-i-depth+1, Z+k, Block.sandStone.blockID);
					if (world.getBlockId(X+j, Y-i-depth+1, Z+k) == Block.gravel.blockID)
						world.setBlockWithNotify(X+j, Y-i-depth+1, Z+k, Block.dirt.blockID);
					world.setBlockWithNotify(X+j, Y-i-depth, Z+k, 0);
				}
			w1 += random.nextInt(2);
			w2 += random.nextInt(2);
			l1 += random.nextInt(2);
			l2 += random.nextInt(2);
		}
	}
}
