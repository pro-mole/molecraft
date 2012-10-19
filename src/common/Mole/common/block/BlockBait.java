package Mole.common.block;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import Mole.common.Constants;
import Mole.common.Mole;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockBait extends Block {
	
	public BlockBait(boolean premium) {
		super(premium? Constants.MOLE_BLOCK_BAIT_PREMIUM: Constants.MOLE_BLOCK_BAIT, premium? 16: 0, Material.rock);
		this.setStepSound(soundGravelFootstep);
		this.setRequiresSelfNotify();
		this.setHardness(1F);
		this.setBlockName("bait"+(premium? "Premium ": ""));
		LanguageRegistry.addName(this, (premium? "Premium ": "")+"Bait");
		
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		world.scheduleBlockUpdate(x, y, z, this.blockID, 100);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		if (this.blockID == Constants.MOLE_BLOCK_BAIT)
			return 0+meta;
		
		if (this.blockID == Constants.MOLE_BLOCK_BAIT_PREMIUM)
			return 16+meta;
		
		//Default: we won't use this, but...
		return 0;
	}
	
	@Override
	public int idDropped(int meta, Random rand, int par3)
	{	
		return Constants.MOLE_BLOCK_BAIT;
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{	
		for(int i=0; i < meta; i++)
		{
			if (world.isBlockGettingPowered(x, y, z) && world.rand.nextInt(3) == 0)
			{
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Mole.grub, 1, 2)));
				continue;
			}
			
			if (this.blockID == Constants.MOLE_BLOCK_BAIT_PREMIUM && world.rand.nextInt(2) == 0)
			{
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Mole.grub, 1, 1)));
				continue;
			}

			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Mole.grub, 1, 0)));
		}
		
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		int grubs = world.getBlockMetadata(x, y, z);
		//System.out.println("TICK "+x+" "+y+" "+z+" "+grubs);
		if (grubs >= 4) return;

		if (world.rand.nextInt(10) == 0) world.setBlockMetadataWithNotify(x, y, z, ++grubs);
		world.scheduleBlockUpdate(x, y, z, this.blockID, 100);
	}
}
