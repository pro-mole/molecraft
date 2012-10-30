package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.seedstone.TileSeedstoneHouse;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class Seedstone extends Item {

	public static enum EnumSeedstoneType
	{
		HOUSE
	};
	
	public static String[] itemNames = {"seedstoneHouse"};
	public static String[] uiNames = {"House Seedstone"};
	
	public Seedstone(int type)
	{
		super(Constants.MOLE_ITEM_SEEDSTONE+type);
		setIconIndex(80+type);
		
		setItemName(itemNames[type]);
		LanguageRegistry.addName(this, uiNames[type]);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz)
    {
		if (world.isRemote) return false;
		
		System.out.println("USE SEEDSTONE["+x+","+y+","+z+","+side+","+px+","+py+","+pz+"]");
		
		if (Mole.isDirtLike(world.getBlockId(x, y, z)))
		{
			System.out.println("SEEDSTONE HOUSE START");
			if (world.getBlockId(x, y, z) == Block.grass.blockID)
				world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, x, y, z, Block.dirt.blockID));
			else
				world.setBlockTileEntity(x, y, z, new TileSeedstoneHouse(1, x, y, z, world.getBlockId(x, y, z)));
			stack.stackSize = 0;
			return true;
		}
		
		return false;
    }
}