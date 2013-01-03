package Mole.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Mole.common.Constants;
import Mole.common.item.Seedstone.EnumSeedstoneType;

public class BlockSeedstone extends BlockContainer {
	
	EnumSeedstoneType type;
	
	public BlockSeedstone(EnumSeedstoneType type)
	{
		super(Constants.MOLE_BLOCK_SEEDSTONE, Material.ground);
		this.setLightValue(0.8F);
		this.setHardness(50);
		this.setResistance(2000);
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
	
	@Override
	public boolean canHarvestBlock(EntityPlayer player, int metadata)
	{
		return false;
	}
}
