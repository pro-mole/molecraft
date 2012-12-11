package Mole.common.tools;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.item.Clump;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
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
		int odds = world.rand.nextInt(200);
		float R = world.rand.nextFloat()*0.5F + 0.25F;
		
		//5% of chance: normal droppings
		if (odds < 10)
			return false;
		
		//15% of chance: bone meal
		if (odds-10 < 30)
		{
			drop = new EntityItem(world, x+R, y+R, z+R, new ItemStack(Item.dyePowder, 1, 15));
			drop.delayBeforeCanPickup = 10;
			world.setBlockWithNotify(x, y, z, 0);
			world.spawnEntityInWorld(drop);
			return true;
		}
		
		//5% of chance: bone
		if (odds-40 < 10)
		{
			drop = new EntityItem(world, x+R, y+R, z+R, new ItemStack(Item.bone, 1, 1));
			drop.delayBeforeCanPickup = 10;
			world.setBlockWithNotify(x, y, z, 0);
			world.spawnEntityInWorld(drop);
			return true;
		}
				
		//5% of chance: normal flint
		if (odds-50 < 10)
		{
			drop = new EntityItem(world, x+R, y+R, z+R, new ItemStack(Item.flint));
			drop.delayBeforeCanPickup = 10;
			world.setBlockWithNotify(x, y, z, 0);
			world.spawnEntityInWorld(drop);
			return true;
		}
		
		//10% of chance: clumps
		if (odds-60 < 20)
		{
			int clumpType = world.rand.nextInt(Clump.itemNames.length);
			if (odds-60 >= 2)
			{
				if (ID == Block.dirt.blockID || ID == Block.grass.blockID)  
					clumpType = Constants.MOLE_CLUMP_EARTH;
				else if (ID == Block.blockClay.blockID)  
					clumpType = Constants.MOLE_CLUMP_WATER;
				else if (ID == Block.sand.blockID)
					clumpType = Constants.MOLE_CLUMP_SAND;
				else if (ID == Block.netherrack.blockID)  
					clumpType = Constants.MOLE_CLUMP_FIRE;
			}
			drop = new EntityItem(world, x+R, y+R, z+R, new ItemStack(Mole.dirtClump, 1, clumpType));
			drop.delayBeforeCanPickup = 10;
			world.setBlockWithNotify(x, y, z, 0);
			world.spawnEntityInWorld(drop);
			return true;
		}
		
		//60% of chance: NOTHING
		world.setBlockWithNotify(x, y, z, 0);
		return true;
	}
}