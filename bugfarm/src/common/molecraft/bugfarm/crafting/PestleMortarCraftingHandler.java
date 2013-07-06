package common.molecraft.bugfarm.crafting;

import java.util.Random;

import common.molecraft.bugfarm.MolecraftBugfarm;
import common.molecraft.bugfarm.item.PestleAndMortar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class PestleMortarCraftingHandler implements ICraftingHandler {

	public PestleMortarCraftingHandler() {
		super();
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {
		
		System.out.println("Crafting Handler Checking...");
		
		for (int i=0; i < craftMatrix.getSizeInventory(); i++)
		{
			ItemStack pm = craftMatrix.getStackInSlot(i); 
			if (pm != null && pm.getItem() instanceof PestleAndMortar)
			{
				System.out.println("Pestle & Mortar:" + pm.getItemDamage());
				if (!pm.attemptDamageItem(1, new Random()))
				{
					pm.stackSize++;
				}
				
			}
		}

	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {

	}

}
