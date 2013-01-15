package Mole.common.world;

import java.util.Random;

import Mole.common.Mole;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StandingStoneGenerator extends WorldGenerator {

	@Override
	//Create standing stones only on ground tiles above level 6
	public boolean generate(World world, Random var2, int x, int y,
			int z) {
		while (world.isAirBlock(x, y, z) && y > 16)
        {
            --y;
        }
		
		if (y >= 16)
		{
			Material mat = world.getBlockMaterial(x, y, z);
			
			if (mat == Material.ground || mat == Material.grass)
			{
				System.out.println("STANDING STONE AT ["+x+","+y+","+z+"]");
				
				int height = 3 + var2.nextInt(3);
				int X,Y,Z;
				for(X=0; X<2; X++)
					for (Z=0; Z<2; Z++)
					{
						int surplus = var2.nextInt(5)-2;
						for (Y=0; Y<height+surplus; Y++)
							setBlock(world,x+X,y+Y,z+Z,Mole.standStone.blockID);
					}
			}
		}
		
		return false;
	}

}
