package Mole.common;

import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.MapColor;
import net.minecraft.src.Material;
import net.minecraftforge.common.EnumHelper;

public class Constants {
	
	//Block IDs [1025-1056]
	public static final int MOLE_BLOCK_DIRTSTONE = 1025;
	//PLACEHOLDER: BAKED_DIRTSTONE=1026
	public static final int MOLE_BLOCK_TERRARIUM = 1027;
	public static final int MOLE_BLOCK_SEEDSTONE = 1028;
	//PLACEHOLDER 8 Seedstone Blocks
	
	//Item IDs [4099-4162]
	public static final int MOLE_ITEM_SPADE = 4099;
	public static final int MOLE_ITEM_GRUB = 4100;
	public static final int MOLE_ITEM_GRUB_COOKED = 4101;
	public static final int MOLE_ITEM_BUGFOOD = 4102;
	public static final int MOLE_ITEM_BUGFOOD_PREMIUM = 4103;
	public static final int MOLE_ITEM_BUG_MEALWORM = 4104;
	public static final int MOLE_ITEM_BUG_STAGBEETLE = 4105;
	public static final int MOLE_ITEM_BUG_BOMBYX = 4106;
	public static final int MOLE_ITEM_BUG_COCCINEAL = 4107;
	public static final int MOLE_ITEM_CLAWS = 4108;
	public static final int MOLE_ITEM_CLUMP = 4109;
	public static final int MOLE_ITEM_SEEDSTONE = 4110;
	
	//Sub-Item IDs
	public static final int MOLE_GRUB_WHITE = 0;
	public static final int MOLE_GRUB_FAT = 1;
	public static final int MOLE_GRUB_RED = 2;
	
	public static final int MOLE_CLUMP_EARTH = 0;
	public static final int MOLE_CLUMP_FIRE = 1;
	public static final int MOLE_CLUMP_ICE = 2;
	public static final int MOLE_CLUMP_SAND = 3;
	public static final int MOLE_CLUMP_WATER = 4;
	
	
	//Texture Files
	public static final String MOLE_TEXT_TERRAIN = "/Mole/common/resources/terrain.png";
	public static final String MOLE_TEXT_ITEMS = "/Mole/common/resources/items.png";
	
	//Tool Materials
	public static final EnumToolMaterial MOLECLAW = EnumHelper.addToolMaterial("MOLECLAW", 3, 1024, 10F, 1, 15);
	
	//Other
}
