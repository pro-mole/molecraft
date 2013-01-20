package Mole.common.terrarium;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.PacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileTerrarium extends TileEntity implements IInventory {

	static int ticksPerFood = 200;
	
	public ItemStack bug;
	public ItemStack food; 
	
	private boolean startMetamorphosis = false;
	private boolean bugWorking = false;
	private int ticks = 0;
	private long metamorphosis = 0; //goes up to 100%;
	
	public TileTerrarium()
	{
		bug = null;
		food = null;
	}
	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		return (slot == 0)? bug: food;
	}

	@Override
	public void updateEntity()
	{
		if (worldObj.isRemote) return;
		
		//Take bug out
		if (bug == null)
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
		
		//Grubs
		if (startMetamorphosis)
		{
			ticks += (food != null && ticks >= ticksPerFood)? 0: 1;
			if (ticks >= ticksPerFood)
			{
				if (food != null)
				{
					//Eat stuff to grow...
					if (food.itemID == Mole.bugFood.shiftedIndex || food.itemID == Mole.bugFoodPremium.shiftedIndex)
					{
						metamorphosis += (food.itemID == Mole.bugFood.shiftedIndex)? 10: 20;
						if (--food.stackSize == 0) food = null;
					}
					
					//Metamorphosis ho!
					if (metamorphosis >= 100)
					{	
						//Any bug can become a Mealworm with 60% chance
						if (worldObj.rand.nextInt(10) < 6)
						{
							//Mealworm
							bug = new ItemStack(Mole.emptyHusk,1);
						}
						else
						{
							//Check bug type, select metamorphosis
							
							int type = bug.getItemDamage();
							switch(type)
							{
								case Constants.MOLE_GRUB_WHITE:
									//Stag Beetle
									bug = new ItemStack(Mole.beetleStag,1);
									break;
								case Constants.MOLE_GRUB_FAT:
									//Bombyx mori
									bug = new ItemStack(Mole.bombyxMori,1);
									break;
								case Constants.MOLE_GRUB_RED:
									//Coccineal
									bug = new ItemStack(Mole.coccineal,1);
									break;
							}
						}
						startMetamorphosis = false;
						metamorphosis = 0;
					}
					PacketDispatcher.sendPacketToAllPlayers(PacketHandler.createTerrariumPackate(this));
				}
				ticks = 0;
			}
		}
		
		//Adult bugs
		if (bugWorking)
		{
			ticks += (food != null && ticks >= ticksPerFood)? 0: 1;
			if (ticks >= ticksPerFood)
			{
				if (food != null)
				{
					if (food.itemID == Mole.bugFood.shiftedIndex || food.itemID == Mole.bugFoodPremium.shiftedIndex)
					{
						//Does whatever this bug does
						if (bug.itemID == Mole.beetleStag.shiftedIndex)
						{
							//--Stag Beetles: FIX
							if (bug.getItemDamage() <= 0)
							{
								bugWorking = false;
								ticks = 0;
								return;
							}
							
							bug.setItemDamage(bug.getItemDamage()-((food.itemID == Mole.bugFood.shiftedIndex)? 10: 20));
							
							//Eat food
							if (bug.getItemDamage() < 0)
								bug.setItemDamage(0);
						}
						else if (bug.itemID == Mole.bombyxMori.shiftedIndex)
						{
							//--Bombyx mori: Spawn Silk
							bug.setItemDamage(bug.getItemDamage() + 5);
							if (bug.getItemDamage() >= bug.getMaxDamage())
								bug = null;
							
							int n = (food.itemID == Mole.bugFood.shiftedIndex)? 1: 2;
							for (int i=0; i<n; i++)
							{
								generateItem(Item.silk);
							}
						}
						
						if (--food.stackSize == 0) food = null;
						PacketDispatcher.sendPacketToAllPlayers(PacketHandler.createTerrariumPackate(this));
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
		if (slot == 0)
			bug = stack;
		else
			food = stack;
		
		if (stack != null)
		{
			if (stack.stackSize > getInventoryStackLimit())
			{
				stack.stackSize = getInventoryStackLimit();
			}
		}
		
		//If both slots are filled, start working
		if (bug != null && food != null)
		{
			if (food.itemID == Mole.bugFood.shiftedIndex ||	food.itemID == Mole.bugFoodPremium.shiftedIndex)
			{
				//Grubs
				if (bug.itemID == Mole.grub.shiftedIndex)
				{
					if (!startMetamorphosis)
					{
						ticks = 0;
						metamorphosis = 0;
						startMetamorphosis = true;
					}
				}
				
				//Adults
				if (bug.itemID == Mole.beetleStag.shiftedIndex || bug.itemID == Mole.bombyxMori.shiftedIndex)
				{
					if (!bugWorking)
					{
						ticks = 0;
						bugWorking = true;
					}
				}
			}
		}
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "Terrarium";
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
		 System.out.println("Loading TE Terrarium at "+xCoord+":"+yCoord+":"+zCoord+"["+FMLCommonHandler.instance().getEffectiveSide()+"]");
         super.readFromNBT(tagCompound);
         
         NBTTagList tagList = tagCompound.getTagList("Terrarium");
         for (int i = 0; i < tagList.tagCount(); i++) {
                 NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                 byte slot = tag.getByte("Slot");
                 if (slot == 0) bug = ItemStack.loadItemStackFromNBT(tag); 
                 else if (slot == 1) food = ItemStack.loadItemStackFromNBT(tag); 
         }
         
         if (bug != null)
         {
	         if (bug.itemID == Mole.grub.shiftedIndex)
	        	 startMetamorphosis = tagCompound.getBoolean("Working");
	         else
	        	 bugWorking = tagCompound.getBoolean("Working");
         }
         
         metamorphosis = tagCompound.getLong("Metamorphosis");
         ticks = tagCompound.getInteger("Ticks");
     }
	 
	 @Override
     public void writeToNBT(NBTTagCompound tagCompound) {
		 System.out.println("Saving TE Terrarium at "+xCoord+":"+yCoord+":"+zCoord+"["+FMLCommonHandler.instance().getEffectiveSide()+"]");
         super.writeToNBT(tagCompound);
                         
         NBTTagList itemList = new NBTTagList();
         for (int i = 0; i < this.getSizeInventory(); i++) {
                 ItemStack stack = this.getStackInSlot(i);
                 if (stack != null) {
                         NBTTagCompound tag = new NBTTagCompound();
                         tag.setByte("Slot", (byte) i);
                         stack.writeToNBT(tag);
                         itemList.appendTag(tag);
                 }
         }
         tagCompound.setTag("Terrarium", itemList);
         tagCompound.setBoolean("Working", bugWorking || startMetamorphosis);
         tagCompound.setLong("Metamorphosis", metamorphosis);
         tagCompound.setInteger("Ticks", ticks);
     }
	 
	 private void generateItem(Item item)
	 {
		 TileEntityChest chest = null;
		 int slotPos = -1;
		 
		 for (int i=-1; i <= 1; i++)
		 {
			 if (chest != null) break;
			 
			 for (int j=-1; j <= 1; j++)
			 {
				 if (chest != null) break;
				 
				 for (int k=-1; k <= 1; k++)
				 {
					 if (chest != null) break;
					 
					 TileEntity container = worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k);
					 if (container != null)
					 {
						 if (container instanceof TileEntityChest)
						 {
							 chest = (TileEntityChest) container;
							 for (int s = chest.getSizeInventory(); s > 0; s--)
							 {
								 ItemStack stack = chest.getStackInSlot(s-1);
								 if (stack == null || stack.stackSize == 0 || stack.getItem().equals(item))
								 {
									 slotPos = s-1;
									 break;
								 }
							 }
							 
							 if (slotPos == -1)
								 chest = null;
						 }
					 }
				 }
			 }
		 }
		 
		 if (chest != null)
		 {
			 ItemStack theStack = chest.getStackInSlot(slotPos);
			 System.err.println("Put Item in slot "+slotPos);
			 if (theStack == null || theStack.stackSize <= 0)
				 chest.setInventorySlotContents(slotPos, new ItemStack(item));
			 else
				 theStack.stackSize++;
		 }
		 else
		 {
			 worldObj.spawnEntityInWorld(
					new EntityItem(worldObj,
						xCoord + worldObj.rand.nextFloat()*0.5+0.25,
						yCoord + 1 + worldObj.rand.nextFloat()*0.5+0.25,
						zCoord + worldObj.rand.nextFloat()*0.5+0.25,
						new ItemStack(item)
						));
		 }
	 }

}
