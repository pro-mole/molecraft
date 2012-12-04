package Mole.common.block;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import Mole.common.Constants;
import Mole.common.item.Seedstone.EnumSeedstoneType;
import Mole.common.seedstone.TileSeedstoneHouse;

public class BlockSeedstone extends BlockContainer {
	
	EnumSeedstoneType type;
	
	public BlockSeedstone(EnumSeedstoneType type)
	{
		super(Constants.MOLE_BLOCK_SEEDSTONE, Material.ground);
		this.setLightValue(0.8F);
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

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return null;
	}
}
