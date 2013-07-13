package common.molecraft.bugfarm.grub;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

import common.molecraft.bugfarm.constants.Numbers;

import cpw.mods.fml.common.registry.LanguageRegistry;

//Abstract class that implements the Grub items
public abstract class Grub extends ItemFood {
	public static enum GrubType {GRUB_WHITE, GRUB_RED, GRUB_FAT, GRUB_TINY, GRUB_WATER, GRUB_DIRT, GRUB_POISON, GRUB_SILK, GRUB_GLOW, GRUB_TRAP, GRUB_SLIME, GRUB_HELL, GRUB_SHINY, GRUB_GOLD, GRUB_MYSTIC;
	
		int getFoodValue()
		{
			switch(this)
			{
				case GRUB_FAT: return 4;
				case GRUB_TINY: return 1;
				default: return 2;
			}
		}
	};
	
	public static String[] names = {"White", "Red", "Fat", "Tiny", "Water", "Dirt", "Spiny", "Silky", "Lantern", "Trap", "Slime", "Hell", "Shiny", "Gold", "Mystic"},
		localNames = {"white", "red", "fat", "tiny", "water", "dirt", "poison", "silk", "glow", "trap", "slime", "hell", "shiny", "gold", "mystic"};
	
	GrubType type;
	
	public Grub(GrubType type)
	{			
		super(Numbers.ITEM_GRUB + type.ordinal(), type.getFoodValue(), false);
		
		this.type = type;
		this.setMaxStackSize(8);
		
		this.setAlwaysEdible();
		
		this.setUnlocalizedName("grub_"+localNames[type.ordinal()]);
		LanguageRegistry.addName(this, names[type.ordinal()]+" Grub");
		this.setCreativeTab(CreativeTabs.tabFood);
	}
	
	/* Returns residuals from metamorphosis */
	public ItemStack getResiduals()
	{
		return null;
	}
}