package Mole.common.terrarium;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import Mole.common.Mole;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class MachineTerrarium extends BlockContainer {

	public MachineTerrarium()
	{
		super(Constants.MOLE_BLOCK_TERRARIUM, Material.glass);
		this.setBlockName("terrarium");
		LanguageRegistry.addName(this, "Terrarium");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int getBlockTextureFromSide(int side)
	{
		switch (side)
		{
			//Top and Bottom
			case 1:
			case 0:
				return 15;
			//Sides
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				return 14;
		}
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_TERRAIN;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		TileEntity TE = world.getBlockTileEntity(x, y, z);
		
		player.openGui(Mole.instance, 0, world, x, y, z);
		return !(TE == null || player.isSneaking());
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	private void dropItems(World world, int x, int y, int z)
	{
		Random rand = new Random();
		
		TileEntity TE = world.getBlockTileEntity(x, y, z);
		if (!(TE instanceof IInventory)) return;
		
		IInventory inv = (IInventory) TE;
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
	        ItemStack item = inv.getStackInSlot(i);
	
	        if (item != null && item.stackSize > 0)
	        {
	            float rx = rand.nextFloat() * 0.8F + 0.1F;
	            float ry = rand.nextFloat() * 0.8F + 0.1F;
	            float rz = rand.nextFloat() * 0.8F + 0.1F;
	
	            EntityItem entityItem = new EntityItem(world,
	                x + rx, y + ry, z + rz,
	                new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
	
	            if (item.hasTagCompound())
	            {
	                entityItem.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
	            }
	
	            float factor = 0.05F;
	            entityItem.motionX = rand.nextGaussian() * factor;
	            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
	            entityItem.motionZ = rand.nextGaussian() * factor;
	            world.spawnEntityInWorld(entityItem);
	            item.stackSize = 0;
	        }
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		// TODO Auto-generated method stub
		return new TileTerrarium();
	}

}