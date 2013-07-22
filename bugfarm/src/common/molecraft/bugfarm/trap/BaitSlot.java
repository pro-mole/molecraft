package common.molecraft.bugfarm.trap;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class BaitSlot extends Slot {

	public BaitSlot(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}
}
