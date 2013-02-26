package Mole.common.terrarium;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import Mole.common.PacketHandler;
import Mole.common.RandomUtil;
import Mole.common.item.AdultBug;
import Mole.common.item.AdultBugProduce;
import Mole.common.item.AdultBugTool;
import Mole.common.item.BugFood;
import Mole.common.item.Grub;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileTerrarium extends TileEntity implements IInventory {

	enum terrariumStateMachine {IDLE, METAMORPHOSIS, WORK};
	
	static int ticksPerFood = 200;
	
	public ItemStack bug;
	public ItemStack food; 
	
	private int ticks = 0;
	terrariumStateMachine workingState;
	private long metamorphosis = 0; //goes up to 100%;
	
	public TileTerrarium()
	{
		bug = null;
		food = null;
		workingState = terrariumStateMachine.IDLE;
		
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
		
		switch (workingState)
		{
		case IDLE:
			if (bug != null && food != null)
			{
				//Food's in the right slot
				if (food.getItem() instanceof BugFood)
				{
					//Grub, start metamorphosing!
					if (bug.getItem() instanceof Grub)
					{
						workingState = terrariumStateMachine.METAMORPHOSIS;
						break;
					}
					
					if (bug.getItem() instanceof AdultBug)
					{
						workingState = terrariumStateMachine.WORK;
						break;
					}
				}
			}
			break;
		case METAMORPHOSIS:
			if (bug == null || food == null ||
			(bug != null && !(bug.getItem() instanceof Grub)) ||
			(food != null && !(food.getItem() instanceof BugFood)))
			{
				workingState = terrariumStateMachine.IDLE;
				break;
			}
			
			if (ticks++ >= ticksPerFood)
			{
				Grub _grub = (Grub) bug.getItem();
				BugFood _food = (BugFood) food.getItem();
				metamorphosis += _food.getFoodValue(_grub, bug.getItemDamage());
				if (metamorphosis >= 100)
				{
					setInventorySlotContents(0, new ItemStack((Item)RandomUtil.randomize(_grub.metamorphosisOdds(bug.getItemDamage()), worldObj.rand)));
					metamorphosis = 0;
				}
				decrStackSize(1, 1);
				ticks = 0;
				PacketDispatcher.sendPacketToAllPlayers(PacketHandler.createTerrariumPackate(this));
			}
			break;
		case WORK:
			if (bug == null || food == null ||
			(bug != null && !(bug.getItem() instanceof AdultBug)) ||
			(food != null && !(food.getItem() instanceof BugFood)))
			{
				workingState = terrariumStateMachine.IDLE;
				break;
			}
			
			if (ticks++ >= ticksPerFood)
			{
				AdultBug _bug = (AdultBug) bug.getItem();
				BugFood _food = (BugFood) food.getItem();
				if (bug.getItem() instanceof AdultBugTool)
				{
					AdultBugTool _toolbug = (AdultBugTool) bug.getItem();
					bug.damageItem(_food.getFoodValue(_toolbug) * _toolbug.getFixAmount(), (EntityLiving) worldObj.playerEntities.get(0));
				}
				else if (bug.getItem() instanceof AdultBugProduce)
				{
					AdultBugProduce _prodbug = (AdultBugProduce) bug.getItem();
					for (int i=0; i < _food.getFoodValue(_prodbug); i++)
					{
						generateItem((ItemStack) RandomUtil.randomize(_prodbug.getProduceList(), worldObj.rand));
					}
					bug.damageItem(1, (EntityLiving) worldObj.playerEntities.get(0));
				}
				decrStackSize(1, 1);
				ticks = 0;
				PacketDispatcher.sendPacketToAllPlayers(PacketHandler.createTerrariumPackate(this));
			}
			break;
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
		//Get initial state
		ItemStack _stack = (slot == 0)? bug: food;
		
		if (slot == 0)
			bug = stack;
		
		else if (slot == 1)
			food = stack;

		if (stack != null)
		{
			if (stack.stackSize > getInventoryStackLimit())
			{
				stack.stackSize = getInventoryStackLimit();
			}
		}
		
		//If the bug slot is set to empty, restart metamorphosis
		if (slot == 0 && stack == null)
			metamorphosis = 0;
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
         
         metamorphosis = tagCompound.getLong("Metamorphosis");
         workingState = terrariumStateMachine.values()[tagCompound.getInteger("State")];
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
         tagCompound.setLong("Metamorphosis", metamorphosis);
         tagCompound.setInteger("State", workingState.ordinal());
         tagCompound.setInteger("Ticks", ticks);
     }
	 
	 private void generateItem(ItemStack stack)
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
								 ItemStack _stack = chest.getStackInSlot(s-1);
								 if (_stack == null || _stack.stackSize == 0 || _stack.getItem().equals(stack.getItem()))
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
				 chest.setInventorySlotContents(slotPos,stack);
			 else
				 theStack.stackSize += stack.stackSize;
		 }
		 else
		 {
			 worldObj.spawnEntityInWorld(
					new EntityItem(worldObj,
						xCoord + worldObj.rand.nextFloat()*0.5+0.25,
						yCoord + 1 + worldObj.rand.nextFloat()*0.5+0.25,
						zCoord + worldObj.rand.nextFloat()*0.5+0.25,
						stack
						));
		 }
	 }

}
