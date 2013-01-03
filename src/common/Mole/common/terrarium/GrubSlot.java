package Mole.common.terrarium;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

//Terrarium's Grub Slot: Only one grub at a time

public class GrubSlot extends Slot {
	
	public GrubSlot(IInventory inv, int slot, int x, int y)
	{
		super(inv,slot,x,y);
	}
	
	public int getSlotStackLimit()
    {
        return 1;
    }
}
