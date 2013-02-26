package Mole.common.seedstone;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Mole.common.seedstone.TileSeedstone.Type;

//This class exists to add GUI particles to the seedstone block
//It does nothing except dispense particles
public class TileSeedstoneSparkler extends TileEntity {

	public TileSeedstoneSparkler() {
		
	}

	public void sparkle(World world, int block)
	{
		EntityFX sparkle = new EntityReddustFX(world, xCoord + 0.1 + world.rand.nextFloat()*0.8, yCoord + 1.2, zCoord + 0.1 + world.rand.nextFloat()*0.8, 0, 0, 0);
		sparkle.setRBGColorF(
			70/255f, 46/255f, 31/255f
		);
		
		ModLoader.getMinecraftInstance().effectRenderer.addEffect(sparkle);
	}
	
	public void chargeSparkle(World world, Type type)
	{
		
	}
}
