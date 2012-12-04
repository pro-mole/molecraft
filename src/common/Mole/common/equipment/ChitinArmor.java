package Mole.common.equipment;

import Mole.common.Constants;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;

public class ChitinArmor extends ItemArmor {

	public ChitinArmor(int type) {
		super(Constants.MOLE_ITEM_CHITIN_ARMOR+type, Constants.CHITIN_ARMOR, 15, type);
	}

}
