package Mole.common.block;

import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class StandingStone extends Block {

	public StandingStone() {
		super(Constants.MOLE_BLOCK_STANDINGSTONE, Material.rock);
		setHardness(1.5F);
		setResistance(15F);

		setCreativeTab(CreativeTabs.tabBlock);
		setBlockName("standstone");
		LanguageRegistry.addName(this, "Standing Stone Block");
	}
	
	@Override
	public int getBlockTextureFromSide(int side)
	{
		return 2;
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
	
	@Override
	public int idDropped(int par1, Random random, int par3)
    {
		if (random.nextInt(4) == 0)
			return this.blockID;
		else
			return 0;
    } 
}
