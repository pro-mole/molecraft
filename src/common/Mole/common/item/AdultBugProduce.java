package Mole.common.item;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface AdultBugProduce extends AdultBug {

	public abstract HashMap<Object, Integer> getProduceList();
}
