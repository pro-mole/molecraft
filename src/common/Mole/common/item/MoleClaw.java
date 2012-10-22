package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
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
		int odds = world.rand.nextInt(100);
		
		//10% of chance: normal droppings
		if (odds < 10)
			return false;
		
		//30% of chance: bone meal
		if (odds-10 < 30)
		{
			drop = new EntityItem(world, x, y, z, new ItemStack(Item.dyePowder, 1, 1));
			drop.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(drop);
		}
		
		//10% of chance: bone
		if (odds-40 < 10)
		{
			drop = new EntityItem(world, x, y, z, new ItemStack(Item.bone, 1, 1));
			drop.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(drop);
		}
				
		//5% of chance: normal flint
		if (odds-50 < 5)
		{
			drop = new EntityItem(world, x, y, z, new ItemStack(Item.flint, 1, 1));
			drop.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(drop);
		}
		
		//5% of chance: special flint
				
		//5% of chance: clumps
		
		//35% of chance: NOTHING
		world.setBlock(x, y, z, 0);
		return true;
	}
}