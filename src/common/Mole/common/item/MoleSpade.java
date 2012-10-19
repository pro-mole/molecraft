package Mole.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemSpade;
import Mole.common.Constants;

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
	public boolean canHarvestBlock(Block block)
	{
		return block.blockID == Constants.MOLE_BLOCK_BAIT || block.blockID == Constants.MOLE_BLOCK_BAIT_PREMIUM;
	}
}
