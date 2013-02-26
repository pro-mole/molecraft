package Mole.common.tools;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.RandomUtil;
import Mole.common.item.Grub;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MoleSpade extends ItemSpade {
	
	public MoleSpade()
	{
		super(Constants.MOLE_ITEM_SPADE, EnumToolMaterial.STONE);
		setItemName("molespade");
		setIconIndex(15);
		setMaxDamage(100);
		LanguageRegistry.addName(this, "Mole Spade");
	}
	
	@Override
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
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
		
		float R = world.rand.nextFloat()*0.5F + 0.25F;
		
		//20% of chance: Grubs
		if (world.rand.nextInt(5) == 0)
		{
			EntityItem _grub = null;
			//Grubs have their own chances of dropping; define them here
			if (ID == Block.grass.blockID || ID == Block.dirt.blockID)
			{
				ArrayList<Integer> grubOdds = new ArrayList<Integer>();
				
				for (int i=0; i < Grub.itemNames.length; i++)
				{
					grubOdds.add(i);
				}
				
				for (int i = -2; i <= 2; i++)
				{
					for (int j = -2; j <= 3; j++)
					{
						BiomeGenBase biome = world.getBiomeGenForCoords(x+i, z+j);
						for (int k = -2; k <= 2; k++)
						{
							int block = world.getBlockId(x+i, y+k, z+j);
							//Air blocks should not influence grub odds either
							if (block == 0) continue;
							
							//Add grub chance for biome;
							if (Constants.grubForBiome.containsKey(biome))
								grubOdds.add(Constants.grubForBiome.get(biome));
							//Add grub chance for block;
							if (Constants.grubForBlock.containsKey(block))
								grubOdds.add(Constants.grubForBlock.get(block));
						}
					}
				}
				
				int type = (Integer) RandomUtil.randomize(grubOdds.toArray(), world.rand);
				System.out.println("Grub type: "+type);
				_grub = new EntityItem(world, x+R, y+R, z+R, new ItemStack(Mole.grub,1, type));
			}
					
			if (_grub != null)
			{
				world.setBlockWithNotify(x, y, z, 0);
				_grub.delayBeforeCanPickup = 10;
				world.spawnEntityInWorld(_grub);
				return true;
			}
		}
		
		//DEFAULT: normal drops
		return false;
	}
}
