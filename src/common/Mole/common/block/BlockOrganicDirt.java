package Mole.common.block;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import Mole.common.Constants;

public final class BlockOrganicDirt extends Block {
	
	public BlockOrganicDirt()
	{
		super(Constants.MOLE_BLOCK_ORGANIC_DIRT, 35, Material.ground);
		setHardness(0.5F);
		setStepSound(soundGravelFootstep);
		setBlockName("organicdirt");
		
		LanguageRegistry.addName(this,"Organic Dirt");
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
}
