package Mole.common.machine;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import Mole.common.Constants;
import Mole.common.item.Seedstone.EnumSeedstoneType;
import Mole.common.seedstone.TileSeedstoneHouse;

import com.jcraft.jorbis.Block;

public class MachineSeedstone extends BlockContainer {
	
	EnumSeedstoneType type;
	
	public MachineSeedstone(EnumSeedstoneType type)
	{
		super(Constants.MOLE_BLOCK_SEEDSTONE + type.ordinal(), Material.ground);
		
		this.type = type;
		
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
	
	@Override
	public int getBlockTextureFromSide(int side)
	{
		//0 for Dirtstone, 1 for Baked Dirtstone
		return 16+type.ordinal();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		switch(type)
		{
			case HOUSE:
				return new TileSeedstoneHouse(1, 0);
		}
		
		return null;
	}
}
