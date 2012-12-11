package Mole.common.tools;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.RandomUtil;
import Mole.common.item.Clump;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemCloth;
import net.minecraft.src.ItemSpade;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.EnumHelper;

public class MoleClaw extends ItemSpade {
	
	public MoleClaw()
	{
		super(Constants.MOLE_ITEM_CLAWS, Constants.MOLECLAW);
		setIconIndex(31);
		
		setCreativeTab(CreativeTabs.tabTools);
		setItemName("mole_claws");
		LanguageRegistry.addName(this, "Mole Claw");
	}
	
	public String getTextureFile()
	{
		return "/Mole/common/resources/items.png";
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
	{
		World world = player.worldObj;
		
		if (world.isRemote)
			return false;
		
		int ID = world.getBlockId(x, y, z); 
		if (!(ID == Block.grass.blockID || ID == Block.dirt.blockID || ID == Block.gravel.blockID || ID == Block.sand.blockID || ID == Block.blockClay.blockID || ID == Block.netherrack.blockID ))
			return false;
		
		EntityItem drop = null;
		float R = world.rand.nextFloat()*0.5F + 0.25F;
		
		//Base odds
		Object[][] odds = new Object[][] {{5, "normal"}, {15, "bonemeal"}, {5, "bone"}, {5, "flint"}, {10, "clump"}, {60, "none"}};
		
		String result = (String) RandomUtil.randomize(odds, world.rand);
		System.out.println(result);
		
		if (!result.equals("normal"))
		{
			ItemStack stack = null;
			
			if (result.equals("bonemeal"))
				stack = new ItemStack(Item.dyePowder,1,15);
			else if (result.equals("bone"))
				stack = new ItemStack(Item.bone);
			else if (result.equals("flint"))
				stack = new ItemStack(Item.flint);
			else if (result.equals("clump"))
			{
				ArrayList<Integer> clumpOdds = new ArrayList<Integer>();
				for (int i=0; i < Clump.itemNames.length; i++)
				{
					clumpOdds.add(i);
				}
				
				for (int i=-1; i <= 1; i++)
				{		
					for (int j=-1; j <= 1; j++)
					{
						BiomeGenBase biome = world.getBiomeGenForCoords(x+i, z+j);
						if (biome == BiomeGenBase.taiga || biome == BiomeGenBase.taigaHills)
							clumpOdds.add(Constants.MOLE_CLUMP_ICE);
						if (biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills)
							clumpOdds.add(Constants.MOLE_CLUMP_SAND);
						if (biome == BiomeGenBase.ocean || biome == BiomeGenBase.swampland || biome == BiomeGenBase.river)
							clumpOdds.add(Constants.MOLE_CLUMP_WATER);
						
						int block = world.getBlockId(x+i, y, z+j);
						
						if (block == Block.waterStill.blockID)
							clumpOdds.add(Constants.MOLE_CLUMP_WATER);
						if (block == Block.sand.blockID)
							clumpOdds.add(Constants.MOLE_CLUMP_SAND);
						if (block == Block.dirt.blockID)
							clumpOdds.add(Constants.MOLE_CLUMP_EARTH);
						if (block == Block.netherrack.blockID)
							clumpOdds.add(Constants.MOLE_CLUMP_FIRE);
					}
				}
				
				int type = (Integer) RandomUtil.randomize(clumpOdds.toArray(), world.rand);
				stack = new ItemStack(Mole.dirtClump, 1, type);
			}
			
			if (stack != null)
			{
				EntityItem _item = new EntityItem(world, x+R, y+R, z+R, stack);
				_item.delayBeforeCanPickup = 10;
				world.spawnEntityInWorld(_item);
			}
			world.setBlockWithNotify(x, y, z, 0);
			return true;
		}
		
		return false;
	}
}