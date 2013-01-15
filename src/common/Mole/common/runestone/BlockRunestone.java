package Mole.common.runestone;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import Mole.common.terrarium.TileTerrarium;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRunestone extends BlockContainer {

	public BlockRunestone()
	{
		super(Constants.MOLE_BLOCK_RUNESTONE, Material.rock);
		setHardness(1.5F);
		setResistance(15F);
		
		this.setBlockName("runestone");
		LanguageRegistry.addName(this, "Runestone");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileRunestone();
	}
	
	@Override
	public int getBlockTextureFromSide(int side)
	{
		return 3;
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
}
