package common.molecraft.bugfarm.trap;

import common.molecraft.bugfarm.MolecraftBugfarm;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

//Abstract class to implement the different sorts of traps
public abstract class TETrap extends TileEntity implements IInventory {

	ItemStack baitSlots[];
	String trapName;
	
	int[] validBaitItems = {
		MolecraftBugfarm.dustWood.itemID,
		MolecraftBugfarm.dustMeat.itemID,
		MolecraftBugfarm.dustReed.itemID,
		MolecraftBugfarm.dustLeaf.itemID,
		MolecraftBugfarm.dustFungus.itemID,
		MolecraftBugfarm.dustFish.itemID,
		MolecraftBugfarm.dustInk.itemID,
		MolecraftBugfarm.dustGourd.itemID,
		MolecraftBugfarm.dustGrub.itemID,
	};
	
	public TETrap(int trapSlots, String name)
	{
		this.baitSlots = new ItemStack[trapSlots];
		for (int i=0; i < trapSlots; i++)
		{
			baitSlots[i] = null;
		}
		
		this.trapName = name;
	}
	
	@Override
	public int getSizeInventory() {
		return baitSlots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return baitSlots[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int n) {
		ItemStack stack = getStackInSlot(slot);
		
		if (stack != null)
		{
			if (n > stack.stackSize)
			{
				return null;
			}
			
			stack.stackSize -= n;
			return stack;
		}
		
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		//No dropping
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		//So far, no problem with this
	}

	@Override
	public String getInvName() {
		return trapName;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		for (int id: validBaitItems)
		{
			if (itemstack.itemID == id)
				return true;
		}
		
		return false;
	}
	
}