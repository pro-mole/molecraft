package Mole.common.block;

import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockDirtStone extends Block {
	
	public BlockDirtStone() 
	{
		super(Constants.MOLE_BLOCK_DIRTSTONE, 33, Material.rock);
		setHardness(1.2F);
		setResistance(9F);
		setStepSound(soundStoneFootstep);
		setBlockName("dirtstone");
		
		LanguageRegistry.addName(this,"Packed Dirt");
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
}
