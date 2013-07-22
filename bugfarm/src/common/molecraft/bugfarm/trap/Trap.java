package common.molecraft.bugfarm.trap;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import common.molecraft.bugfarm.constants.Numbers;

//Abstract class that implements the Trap blocks
public abstract class Trap extends BlockContainer {
	
	public static String[] names = {"Basic Trap"};
	public static String[] ids = {"basic"};
	
	public Trap(int index)
	{
		super(Numbers.BLOCK_TRAP + index, Material.wood);
	}
	
	@Override
	public abstract TileEntity createNewTileEntity(World world);
	
}