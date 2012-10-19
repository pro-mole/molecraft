package Mole.common.machine;

import Mole.common.Constants;
import Mole.common.Mole;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;

public class TileTerrarium extends TileEntity implements IInventory {

	private ItemStack[] inv;
	private ItemStack bug;
	private ItemStack food; 
	
	private boolean startMetamorphosis = false;
	private boolean bugWorking = false;
	private int ticks = 0;
	private long metamorphosis = 0; //goes up to 100%;
	
	public TileTerrarium()
	{
		inv = new ItemStack[2];
		bug = inv[0];
		food = inv[1];
	}
	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		return inv[slot];
	}

	@Override
	public void updateEntity()
	{
		if (inv[0] != null)
		{
			if (inv[0].itemID - Constants.MOLE_ITEM_GRUB-256 == 0)
			{
				if (!startMetamorphosis)
				{
					ticks = 0;
					metamorphosis = 0;
					startMetamorphosis = true;
				}
			}
			
			if (inv[0].itemID == Mole.beetleStag.shiftedIndex)
			{
				if (!bugWorking)
				{
					ticks = 0;
					bugWorking = true;
					System.out.println("DMG: "+inv[0].getItemDamage());
				}
			}
		}
		else
		{
			if (startMetamorphosis)
			{
				startMetamorphosis = false;
				ticks = 0;
			}
			if (bugWorking)
			{
				bugWorking = false;
				ticks = 0;
			}
		}
		
		if (startMetamorphosis)
		{
			ticks += (inv[1] != null && ticks >= 200)? 0: 1;
			if (ticks >= 200)
			{
				if (inv[1] != null)
				{
					if (inv[1].itemID == Mole.bugFood.shiftedIndex || inv[1].itemID == Mole.bugFoodPremium.shiftedIndex)
					{
						metamorphosis += (inv[1].itemID == Mole.bugFood.shiftedIndex)? 10: 20;
						if (--inv[1].stackSize == 0) inv[1] = null;
					}
					
					if (metamorphosis >= 100)
					{
						inv[0] = new ItemStack(Mole.beetleStag,1);
						startMetamorphosis = false;
					}
				}
				ticks = 0;
			}
		}
		
		if (bugWorking && inv[0].getItemDamage() > 0)
		{
			ticks += (inv[1] != null && ticks >= 200)? 0: 1;
			if (ticks >= 200)
			{
				if (inv[1] != null)
				{
					if (inv[1].itemID == Mole.bugFood.shiftedIndex || inv[1].itemID == Mole.bugFoodPremium.shiftedIndex)
					{
						inv[0].setItemDamage(inv[0].getItemDamage()-((inv[1].itemID == Mole.bugFood.shiftedIndex)? 10: 20));
						if (inv[0].getItemDamage() < 0)
							inv[0].setItemDamage(0);
						
						if (--inv[1].stackSize == 0) inv[1] = null;
					}
					
					if (inv[0].getItemDamage() == 0)
					{
						bugWorking = false;
					}
				}
				ticks = 0;
			}
		}
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		// TODO Auto-generated method stub
		ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
                if (stack.stackSize <= amt) {
                        setInventorySlotContents(slot, null);
                } else {
                        stack = stack.splitStack(amt);
                        if (stack.stackSize == 0) {
                                setInventorySlotContents(slot, null);
                        }
                }
        }
        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		// TODO Auto-generated method stub
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			setInventorySlotContents(slot, stack);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		// TODO Auto-generated method stub
		inv[slot] = stack;
		if (stack != null)
		{
			if (slot == 0 && stack.stackSize > 1)
			{
				stack.stackSize = 1;
			}
			else if (slot == 1 && stack.stackSize > getInventoryStackLimit())
			{
				stack.stackSize = getInventoryStackLimit();
			}
		}
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord+0.5, yCoord+0.5, zCoord+0.5) < 64;
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
     public void readFromNBT(NBTTagCompound tagCompound) {
             super.readFromNBT(tagCompound);
             
             NBTTagList tagList = tagCompound.getTagList("Inventory");
             for (int i = 0; i < tagList.tagCount(); i++) {
                     NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                     byte slot = tag.getByte("Slot");
                     if (slot >= 0 && slot < inv.length) {
                             inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                     }
             }
     }
	 
	 @Override
     public void writeToNBT(NBTTagCompound tagCompound) {
             super.writeToNBT(tagCompound);
                             
             NBTTagList itemList = new NBTTagList();
             for (int i = 0; i < inv.length; i++) {
                     ItemStack stack = inv[i];
                     if (stack != null) {
                             NBTTagCompound tag = new NBTTagCompound();
                             tag.setByte("Slot", (byte) i);
                             stack.writeToNBT(tag);
                             itemList.appendTag(tag);
                     }
             }
             tagCompound.setTag("Inventory", itemList);
     }

}
