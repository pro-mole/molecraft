package Mole.common.machine;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import Mole.common.Constants;
import Mole.common.item.Seedstone.EnumSeedstoneType;
import Mole.common.seedstone.TileSeedstoneHouse;

public class MachineSeedstone extends Block {
	
	EnumSeedstoneType type;
	
	public MachineSeedstone(EnumSeedstoneType type)
	{
		super(Constants.MOLE_BLOCK_SEEDSTONE, Material.ground);
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
	
	@Override
	public int getBlockTextureFromSide(int side)
	{
		return 16;
	}
}
