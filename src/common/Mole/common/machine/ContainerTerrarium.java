package Mole.common.machine;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class ContainerTerrarium extends Container {

	protected TileTerrarium TE;
	
	public ContainerTerrarium(InventoryPlayer invPlayer, TileTerrarium te)
	{
		TE = te;
		
		//the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        addSlotToContainer(new GrubSlot(TE, 0, 80, 25));
        addSlotToContainer(new Slot(TE, 1, 80, 49));

        //commonly used vanilla code that adds the player's inventory
        bindPlayerInventory(invPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return TE.isUseableByPlayer(player);
	}
	
	protected void bindPlayerInventory(InventoryPlayer invPlayer)
	{
		for (int i=0; i < 3; i++)
		{
			for (int j=0; j < 9; j++)
			{
				addSlotToContainer(new Slot(invPlayer, j + i*9 + 9, 8 + j*18, 84 + i*18));
			}
		}
		
		for (int i=0; i<9; i++)
		{
			addSlotToContainer(new Slot(invPlayer, i, 8+i*18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(int slot)
	{
		ItemStack stack = null;
		Slot slotObj = (Slot) inventorySlots.get(slot);
		
		if (slotObj != null && slotObj.getHasStack())
		{
			ItemStack stackInSlot = slotObj.getStack();
			stack = stackInSlot.copy();
			
			//Merge the item into the player inventory
			if (slot < 1)
			{
				if (!mergeItemStack(stackInSlot, 2, inventorySlots.size(), true))
					return null;
			}
			else
			{
				if (!mergeItemStack(stackInSlot, 0, 1, false))
				return null;
			}
			
			if (stackInSlot.stackSize == 0)
				slotObj.putStack(null);
			else
				slotObj.onSlotChanged();
		}
		
		return stack;
	}
}
