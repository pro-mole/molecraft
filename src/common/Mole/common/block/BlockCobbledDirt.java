package Mole.common.block;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import Mole.common.Constants;

public final class BlockCobbledDirt extends Block {
	
	public BlockCobbledDirt()
	{
		super(Constants.MOLE_BLOCK_COBBLEDIRT, 32, Material.rock);
		setHardness(1.8F);
		setResistance(9F);
		setStepSound(soundStoneFootstep);
		setBlockName("cobbledirt");
		
		LanguageRegistry.addName(this,"Cobbled Dirt");
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
}
