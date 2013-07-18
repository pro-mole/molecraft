package common.molecraft.bugfarm.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import common.molecraft.bugfarm.MolecraftBugfarm;
import common.molecraft.bugfarm.constants.Numbers;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class Cocoon extends BlockContainer {
	
	public static String[] names = {"Hard","Silk","Debris"},
		ids = {"hard","silk","debris"};
	
	public static Icon[] icons;
	
	int variety;
	
	public Cocoon(int type) {
		super(Numbers.BLOCK_COCOON+type, Material.leaves);
		
		this.variety = type;
		this.setHardness(0.05f);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setUnlocalizedName(ids[type]+"Cocoon");
		LanguageRegistry.addName(this, names[type]+" Cocoon");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TECocoon(variety);
	}

	@Override
	public boolean isBlockSolid(IBlockAccess access, int x,
			int y, int z, int side) {
		return false;
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y,
			int z, int metadata, int fortune) {
		//And now, the fun part :D 
		float rnd = world.rand.nextFloat();
		
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		
		switch (blockID - Numbers.BLOCK_COCOON)
		{
			case 0: //Hard Cocoon
				if (rnd < 0.1)
					drops.add(new ItemStack(MolecraftBugfarm.grubFat));
				else if (rnd < 0.2)
					drops.add(new ItemStack(MolecraftBugfarm.grubPoison));
				else if (rnd < 0.5)
					drops.add(new ItemStack(MolecraftBugfarm.grubTiny,world.rand.nextInt(4)+4));
				else
					drops.add(new ItemStack(MolecraftBugfarm.grubWhite));
				break;
			case 1: //Silk Cocoon
				if (rnd < 0.1)
					drops.add(new ItemStack(MolecraftBugfarm.grubFat));
				else if (rnd < 0.15)
					drops.add(new ItemStack(MolecraftBugfarm.grubPoison));
				else if (rnd < 0.6)
					drops.add(new ItemStack(MolecraftBugfarm.grubSilk));
				else
					drops.add(new ItemStack(MolecraftBugfarm.grubWhite));
				break;
			case 2: //Debris Cocoon
				if (rnd < 0.05)
					drops.add(new ItemStack(MolecraftBugfarm.grubShiny));
				else if (rnd < 0.15)
					drops.add(new ItemStack(MolecraftBugfarm.grubFat));
				else if (rnd < 0.3)
					drops.add(new ItemStack(MolecraftBugfarm.grubRed));
				else if (rnd < 0.5)
					drops.add(new ItemStack(MolecraftBugfarm.grubSlime));
				else if (rnd < 0.75)
					drops.add(new ItemStack(MolecraftBugfarm.grubDirt));
				else
					drops.add(new ItemStack(MolecraftBugfarm.grubWhite));
				break;
		}
		
		return drops;
	}
	
	//This function will be used in two occasions
	// - when it is placed, to set the side the cocoon attaches to
	// - when the block it is attached to is broken
	void checkTreeBark(World world, int x, int y, int z)
	{
		//Metadata == 0 means we just been created into the world
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0)
		{
			//Check all four sides
			//Here's a bit of cheating: first we check them for solid, then for bark
			//This way, treebark will be preferred, always
			if (world.isBlockSolidOnSide(x-1, y, z, ForgeDirection.WEST, false))
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
			if (world.isBlockSolidOnSide(x, y, z-1, ForgeDirection.NORTH, false))
				world.setBlockMetadataWithNotify(x, y, z, 2, 3);
			if (world.isBlockSolidOnSide(x+1, y, z, ForgeDirection.EAST, false))
				world.setBlockMetadataWithNotify(x, y, z, 3, 3);
			if (world.isBlockSolidOnSide(x, y, z+1, ForgeDirection.SOUTH, false))
				world.setBlockMetadataWithNotify(x, y, z, 4, 3);
			
			if (world.getBlockId(x-1, y, z) == Block.wood.blockID)
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
			if (world.getBlockId(x, y, z-1) == Block.wood.blockID)
				world.setBlockMetadataWithNotify(x, y, z, 2, 3);
			if (world.getBlockId(x+1, y, z) == Block.wood.blockID)
				world.setBlockMetadataWithNotify(x, y, z, 3, 3);
			if (world.getBlockId(x, y, z+1) == Block.wood.blockID)
				world.setBlockMetadataWithNotify(x, y, z, 4, 3);
			
			//no solid block whatsoever? So sorry
			if (world.getBlockMetadata(x, y, z) == 0)
				world.setBlock(x, y, z, 0);
		}
		else //Any other metadata, just check if the block under you was destroyed
		{
			int side = meta;
			switch(side)
			{
				case 1: if (!world.isAirBlock(x-1, y, z)) break;
				case 2: if (!world.isAirBlock(x, y, z-1)) break;
				case 3: if (!world.isAirBlock(x+1, y, z)) break;
				case 4: if (!world.isAirBlock(x, y, z+1)) break;
				default:
					world.destroyBlock(x, y, z, true);
			}
		}
	}
	
	//Set block direction based on where it is placed
	//Ideally, it'll always be placed on tree bark
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		checkTreeBark(world, x, y, z);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y,
			int z, int id) {
		checkTreeBark(world, x, y, z);
	}
	
	@Override
	public int getRenderType() {
		return -2;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}