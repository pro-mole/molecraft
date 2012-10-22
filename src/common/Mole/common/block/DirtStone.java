package Mole.common.block;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

//Implements Dirtstone and Baked Dirtstone
public class DirtStone extends Block {
	
	public DirtStone(boolean baked)
	{
		super(Constants.MOLE_BLOCK_DIRTSTONE+(baked? 1: 0), Material.rock);
		setHardness(baked? 2F: 1.5F);
		setResistance(10F);
		
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockName("dirtstone"+(baked? "_baked": ""));
		LanguageRegistry.addName(this, baked? "Dirtstone": "Baked Dirtstone");
	}
	
	@Override
	public int getBlockTextureFromSide(int side)
	{
		//0 for Dirtstone, 1 for Baked Dirtstone
		return blockID - Constants.MOLE_BLOCK_DIRTSTONE;
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
}
