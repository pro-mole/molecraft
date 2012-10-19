package Mole.common.item;

import net.minecraft.src.Block;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemSpade;

public class MoleClaw extends ItemSpade {
	
	public MoleClaw(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
	}
	
	public String getTextureFile()
	{
		return "/Mole/common/resources/items.png";
	}
	
	public boolean canHarvestBlock(Block par1)
	{
		return false;
	}
}
