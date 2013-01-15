package Mole.common.world;

import java.util.Random;

import Mole.common.Constants;

import net.minecraft.block.Block;
import net.minecraft.world.World;
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
			int X = chunkX*16 + random.nextInt(16);
			int Y = 128;
			int Z = chunkZ*16 + random.nextInt(16);
			
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
				isDirt = true;
				while(world.getBlockId(X, Y, Z) == Block.waterStill.blockID)
					Y--;
			}
			
			if (Y > 0 && isDirt)
			{
				Y += 1;
				System.out.println("STANDING STONE AT ["+X+","+Y+","+Z+"]");
				int w = random.nextInt(2) + 1,
					l = random.nextInt(2) + 1,
					h = random.nextInt(5) + 3;
				
				for (int i = 0; i < w; i++)
					for (int j = 0; j < l; j++)
					{
						int over = random.nextInt(2);
						for (int k = 0; k < h+over; k++)
							world.setBlockWithNotify(X+i, Y+k, Z+j, Constants.MOLE_BLOCK_STANDINGSTONE);
					}
			}
		}
	}

}
