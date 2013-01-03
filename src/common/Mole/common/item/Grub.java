package Mole.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import Mole.common.Constants;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Grub extends ItemFood {
	
	public static String[] itemNames = {"white", "premium", "red"};
	public static String[] uiNames = {"White", "Fat", "Red"};
	
	public Grub()
	{
		super(Constants.MOLE_ITEM_GRUB, 1, false);
		this.maxStackSize = 8;
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setItemName("grub");
		this.setHasSubtypes(true);
		this.setIconIndex(0);
        this.setMaxDamage(0);
		
        for (int i=0; i < itemNames.length; i++)
        {
        	ItemStack grubType = new ItemStack(this.shiftedIndex, 1, i);
        	LanguageRegistry.addName(grubType, uiNames[i]+" Grub");
        }
	}
	
	public String getTextureFile()
	{
		return Constants.MOLE_TEXT_ITEMS;
	}
	
	@SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value
     */
    public int getIconFromDamage(int dmg)
    {
        int val = MathHelper.clamp_int(dmg, 0, 15);
        return this.iconIndex + val;
    }
	
	public String getItemNameIS(ItemStack itemStack)
    {
        int val = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 15);
        return "grub." + itemNames[val];
    }
	
	@SideOnly(Side.CLIENT)
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int id, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < itemNames.length; ++i)
        {
        	list.add(new ItemStack(id, 1, i));
        }
    }
}
