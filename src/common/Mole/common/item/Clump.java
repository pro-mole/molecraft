package Mole.common.item;

import java.util.List;

import Mole.common.Constants;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;

//Dirt clumps will be formed from here
public class Clump extends Item {
	
	public static String[] itemNames = {"earth", "fire", "ice", "sand", "water"};
	public static String[] uiNames = {"Earth", "Hot", "Cold", "Dust", "Mud"};
	
	public Clump()
	{
		super(Constants.MOLE_ITEM_CLUMP);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setItemName("clump");
		this.setHasSubtypes(true);
		this.setIconIndex(64);
        this.setMaxDamage(0);
		
        for (int i=0; i < itemNames.length; i++)
        {
        	ItemStack dirtClump = new ItemStack(this.shiftedIndex, 1, i);
        	LanguageRegistry.addName(dirtClump, uiNames[i]+" Clump");
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
        return "clump." + itemNames[val];
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
