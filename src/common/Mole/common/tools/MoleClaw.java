package Mole.common.tools;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.RandomUtil;
import Mole.common.item.Clump;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MoleClaw extends ItemSpade {
	
	//Odd modifiers for each biome
	static HashMap<BiomeGenBase, Integer> clumpForBiome = new HashMap() {{
		put (BiomeGenBase.plains, Constants.MOLE_CLUMP_EARTH);
		put (BiomeGenBase.beach, Constants.MOLE_CLUMP_WATER);
		put (BiomeGenBase.river, Constants.MOLE_CLUMP_WATER);
		put (BiomeGenBase.ocean, Constants.MOLE_CLUMP_WATER);
		put (BiomeGenBase.taiga, Constants.MOLE_CLUMP_ICE);
		put (BiomeGenBase.frozenRiver, Constants.MOLE_CLUMP_ICE);
		put (BiomeGenBase.frozenOcean, Constants.MOLE_CLUMP_ICE);
		put (BiomeGenBase.taigaHills, Constants.MOLE_CLUMP_ICE);
		put (BiomeGenBase.forest, Constants.MOLE_CLUMP_EARTH);
		put (BiomeGenBase.forestHills, Constants.MOLE_CLUMP_EARTH);
		put (BiomeGenBase.desert, Constants.MOLE_CLUMP_SAND);
		put (BiomeGenBase.desertHills, Constants.MOLE_CLUMP_SAND);
		put (BiomeGenBase.swampland, Constants.MOLE_CLUMP_WATER);
		put (BiomeGenBase.extremeHills, Constants.MOLE_CLUMP_EARTH);
		put (BiomeGenBase.extremeHillsEdge, Constants.MOLE_CLUMP_EARTH);
		put (BiomeGenBase.iceMountains, Constants.MOLE_CLUMP_ICE);
		put (BiomeGenBase.icePlains, Constants.MOLE_CLUMP_ICE);
		put (BiomeGenBase.jungle, Constants.MOLE_CLUMP_PLANT);
		put (BiomeGenBase.jungleHills, Constants.MOLE_CLUMP_PLANT);
		put (BiomeGenBase.mushroomIsland, Constants.MOLE_CLUMP_PLANT);
		put (BiomeGenBase.mushroomIslandShore, Constants.MOLE_CLUMP_PLANT);
		put (BiomeGenBase.hell, Constants.MOLE_CLUMP_FIRE);
	}}; 
	
	static HashMap<Integer, Integer> clumpForBlock = new HashMap() {{
		put (Block.dirt.blockID, Constants.MOLE_CLUMP_EARTH);
		put (Block.grass.blockID, Constants.MOLE_CLUMP_PLANT);
		put (Block.sand.blockID, Constants.MOLE_CLUMP_SAND);
		put (Block.blockClay.blockID, Constants.MOLE_CLUMP_WATER);
		put (Block.netherrack.blockID, Constants.MOLE_CLUMP_FIRE);
		put (Block.gravel.blockID, Constants.MOLE_CLUMP_EARTH);
	}};
	
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
		HashMap<Object, Integer> odds = new HashMap() {{
			put ("normal", 5);
			put (new ItemStack(Item.dyePowder,1,15), 15);
			put (new ItemStack(Item.bone), 5);
			put (new ItemStack(Item.flint), 5);
			put ("clump", 10);
			put (null, 60);
		}};
		
		Object result = RandomUtil.randomize(odds, world.rand);
		System.out.println(result);
		
		if (result == null)
		{
			world.setBlockWithNotify(x, y, z, 0);
			return true;
		}
		
		if (!result.equals("normal"))
		{
			ItemStack stack;
			
			if (result.equals("clump"))
			{
				ArrayList<Integer> clumpOdds = new ArrayList<Integer>();
				for (int i=0; i < Clump.itemNames.length; i++)
				{
					clumpOdds.add(i);
				}
				
				for (int i=-3; i <= 3; i++)
				{		
					for (int j=-3; j <= 3; j++)
					{
						BiomeGenBase biome = world.getBiomeGenForCoords(x+i, z+j);
						for (int k=-3; k <= 3; k++)
						{
							int block = world.getBlockId(x+i, y+k, z+j);
							//Air blocks should not influence clump odds
							//Since at least the block you're breaking shouldn't be air
							//There'll be at least a slight change in odds
							if (block == 0) continue;
							
							//Add clump chance for biome; if biome is unknown, Earth
							if (clumpForBiome.containsKey(biome))
								clumpOdds.add(clumpForBiome.get(biome));
							
							if (clumpForBiome.containsKey(block))
								clumpOdds.add(clumpForBlock.get(block));
						}
					}
				}
				
				int type = (Integer) RandomUtil.randomize(clumpOdds.toArray(), world.rand);
				stack = new ItemStack(Mole.dirtClump, 1, type);
			}
			else
			{
				stack = (ItemStack) result;
			}
			
			EntityItem _item = new EntityItem(world, x+R, y+R, z+R, stack);
			_item.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(_item);
			world.setBlockWithNotify(x, y, z, 0);
			
			return true;
		}

		return false;
	}
	
	//API stuff for other modders, if they so wish, add their blocks and biomes to the clump odds stuff
	public static void addClumpBiomeOdds(BiomeGenBase biome, int clumpType)
	{
		clumpForBiome.put(biome, clumpType);
	}
	
	public static void addClumpBlockOdds(int blockID, int clumpType)
	{
		clumpForBlock.put(blockID, clumpType);
	}
}