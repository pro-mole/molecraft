package Mole.common.seedstone;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Random;

import Mole.common.PacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public abstract class TileSeedstone extends TileEntity {

	public static enum Type {
		PILLAR, WALL, BRIDGE, STAIRS,				//Earth
		CAVE,		  								//Air
		WELL, LAKE,									//Water
		FIRE, LAVA,									//Fire
		DESERT, MARSH, JUNGLE, MOUNTAIN, ISLAND,	//Landscape
		MINER, DRILL, BLACKHOLE,					//Extractors
		BLAST										//Explosion
	}
	
	int charge;
	int tier;
	Type type;
	int block;
	int ticks = 0;
	boolean working = false;
	int sparkles = 0;
	
	public TileSeedstone(Type T, int bID, int t) {
		tier = t;
		charge = 0;
		block = bID;
	}
	
	abstract void initialize();
	
	abstract int getMaxCharge();
	
	public void chargeUp()
	{
		if (charge >= getMaxCharge())
		{
			initialize();
			working = true;
			work();
		}
		else
		{
			int _charge = getCharge(worldObj);
			sparkles = _charge/5;
			System.out.println("SPARKLE "+sparkles);
			charge += _charge;
			System.out.println(charge);
		}
	}
	
	abstract void work();

	@Override
	public void updateEntity() {
		if (working)
		{
			if (ticks++ >= 10)
			{
				work();
				ticks = 0;
			}
		}
	}
	
	public int getSparkles()
	{
		return (sparkles > 0)? sparkles--: 0;
	}
	
	public void readSeedstoneNBT(NBTTagCompound tag) {
		
		super.readFromNBT(tag);
		System.out.println("Loading TE Seedstone at "+xCoord+":"+yCoord+":"+zCoord+"["+FMLCommonHandler.instance().getEffectiveSide()+"]");
		charge = tag.getInteger("Charge");
		tier = tag.getInteger("Tier");
		block = tag.getInteger("BlockID");
		ticks = 0;
		working = tag.getBoolean("WorkingState");
	}
	
	@Override
	public abstract void readFromNBT(NBTTagCompound par1nbtTagCompound);

	public void writeSeedstoneNBT(NBTTagCompound tag) {
		
		super.writeToNBT(tag);
		tag.setInteger("Charge", charge);
		tag.setInteger("Tier", tier);
		tag.setInteger("BlockID", block);
		tag.setBoolean("WorkingState", working);
	}
	
	@Override
	public abstract void writeToNBT(NBTTagCompound par1nbtTagCompound);
	
	public abstract int getCharge(World world);
}
