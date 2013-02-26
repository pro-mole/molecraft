package Mole.common.seedstone;

import cpw.mods.fml.common.registry.LanguageRegistry;
import Mole.common.Constants;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSeeded extends ItemBlock {
	
	private final static String[] subNames = {
		"dirt", "sand", "gravel", "clay", "netherrack", "soulSand"
	};
	
	private final static String[] blockNames = {
			"Dirt", "Sand", "Gravel", "Clay", "Netherrack", "Soul Sand"
	};
	
	public ItemBlockSeeded(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setItemName("seededBlock");
	}

	@Override
	public String getItemNameIS(ItemStack stack) {
		
		return this.getItemName() + "." + subNames[stack.getItemDamage()];
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	
}
