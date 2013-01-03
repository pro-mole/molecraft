package Mole.common.terrarium;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import Mole.common.Constants;

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
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		ItemStack stack = null;
		Slot slotObj = (Slot) inventorySlots.get(slot);
		
		if (slotObj != null && slotObj.getHasStack())
		{
			ItemStack stackInSlot = slotObj.getStack();
			stack = stackInSlot.copy();
			
			//Merge the item into the player inventory
			if (slot < 2)
			{
				if (!mergeItemStack(stackInSlot, 2, inventorySlots.size(), true))
					return null;
			}
			else
			{
				if (stackInSlot.itemID == Constants.MOLE_ITEM_GRUB+256)
				{
					Slot dst = (Slot) inventorySlots.get(0);
					if (dst.getHasStack() && dst.getStack().stackSize > 0)
						return null;
					
					stackInSlot.stackSize--;
					ItemStack _stack = new ItemStack(stackInSlot.itemID,1,stackInSlot.getItemDamage());
					if (!mergeItemStack(_stack, 0, 1, false))
					{
						stackInSlot.stackSize++;
						return null;
					}
				}
				else if (stackInSlot.itemID == Constants.MOLE_ITEM_BUGFOOD+256 || stackInSlot.itemID == Constants.MOLE_ITEM_BUGFOOD_PREMIUM+256)
				{
					if (!mergeItemStack(stackInSlot, 1, 2, false))
						return null;
				}
				else return null;
			}
			
			if (stackInSlot.stackSize == 0)
				slotObj.putStack(null);
			else
				slotObj.onSlotChanged();
		}
		
		return stack;
	}
}
