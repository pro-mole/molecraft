package Mole.common.tools;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemSpade;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import Mole.common.Constants;
import Mole.common.Mole;

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
		
		//10% of chance: Grubs
		if (world.rand.nextInt(10) == 0)
		{
			EntityItem _grub = null;
			//Grubs have their own chances of dropping; define them here
			if (ID == Block.grass.blockID || ID == Block.dirt.blockID)
			{
				if (world.rand.nextInt(20) == 0) //10% Red grubs
					_grub = new EntityItem(world, x+R, y+R, z+R, new ItemStack(Mole.grub,1,Constants.MOLE_GRUB_RED));
				else if (world.rand.nextInt(19) == 0) //10% Fat grubs 
					_grub = new EntityItem(world, x+R, y+R, z+R, new ItemStack(Mole.grub,1,Constants.MOLE_GRUB_FAT));
				else //80% White grubs
					_grub = new EntityItem(world, x+R, y+R, z+R, new ItemStack(Mole.grub,1,Constants.MOLE_GRUB_WHITE));
			}
					
			if (_grub != null)
			{
				world.setBlockWithNotify(x, y, z, 0);
				_grub.delayBeforeCanPickup = 10;
				world.spawnEntityInWorld(_grub);
			}
			return true;
		}
		
		//DEFAULT: normal drops
		return false;
	}
}