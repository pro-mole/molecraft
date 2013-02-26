package Mole.common.seedstone;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import Mole.common.Constants;
import Mole.common.Mole;

public class SeedstonePillar extends TileSeedstone {

	int height;
	int marker = 0;
	
	//Empty loader
	public SeedstonePillar() {
		super(Type.PILLAR, 0, 0);
	}
	
	public SeedstonePillar(int bID, int tier) {
		super(Type.PILLAR, bID, tier);
	}
	
	@Override
	void initialize() {
		height = tier + 3 + worldObj.rand.nextInt(1);
	}

	@Override
	int getMaxCharge() {
		return tier * 50;
	}

	@Override
	void work() {
		if (marker++ < height && worldObj.isAirBlock(xCoord, yCoord+marker, zCoord))
		{
			worldObj.playSound(xCoord, yCoord+marker, zCoord+marker, "gravel", 1, 1, true);
			worldObj.setBlockWithNotify(xCoord, yCoord+marker, zCoord, block);
			ticks = 0;
		}
		else
		{
			worldObj.setBlockWithNotify(xCoord, yCoord, zCoord, block);
			worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
			working = false;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readSeedstoneNBT(tagCompound);
		System.out.println("Loading TE Pillar Seedstone at "+xCoord+":"+yCoord+":"+zCoord+"["+FMLCommonHandler.instance().getEffectiveSide()+"]");
		tagCompound.setInteger("Height", height);
		tagCompound.setInteger("Marker", marker);
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeSeedstoneNBT(tagCompound);
		height = tagCompound.getInteger("Height");
		marker = tagCompound.getInteger("Marker");
	}

	@Override
	public int getCharge(World world) {
		
		int charge = 0;
		
		for (int x=-1;x<=1;x++)
			for (int z=-1;z<=1;z++)
			{
				int block = world.getBlockId(xCoord+x, yCoord, zCoord+z); 
				if (block == super.block) charge += 10;
				else if (Mole.isDirtLike(block)) charge += 5;
				else if (block == Constants.MOLE_BLOCK_SEEDSTONE) charge -= 5;
				else if (world.isAirBlock(xCoord+x, yCoord, zCoord+z) || block == Block.waterStill.blockID || block == Block.waterMoving.blockID) charge += 0;
				else charge += 1;
			}
	
		System.out.println(charge);
		
		return (charge >= 0)? charge: 0;
	}
}
