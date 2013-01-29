package Mole.common;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Constants {
	
	Configuration config;
	
	//Block IDs [1025-1056]
	public static int MOLE_BLOCK_DIRTSTONE = 1025; //Dirt stone
	public static int MOLE_BLOCK_TERRARIUM = 1027; //Simple Terrarium
	public static int MOLE_BLOCK_SEEDSTONE = 1028; //Seeded block
	public static int MOLE_BLOCK_STANDINGSTONE = 1029; //Standing stone block
	public static int MOLE_BLOCK_RUNESTONE = 1030; //Runestone
	
	//Item IDs [4099-4162]
	public static int MOLE_ITEM_SPADE = 4099;
	public static int MOLE_ITEM_GRUB = 4100;
	public static int MOLE_ITEM_GRUB_COOKED = 4101;
	public static int MOLE_ITEM_BUGFOOD = 4102;
	public static int MOLE_ITEM_BUGFOOD_PREMIUM = 4103;
	public static int MOLE_ITEM_BUG_HUSK = 4104;
	public static int MOLE_ITEM_BUG_STAGBEETLE = 4105;
	public static int MOLE_ITEM_BUG_BOMBYX = 4106;
	public static int MOLE_ITEM_BUG_COCCINEAL = 4107;
	public static int MOLE_ITEM_CLAWS = 4108;
	public static int MOLE_ITEM_CLUMP = 4109;
	public static int MOLE_ITEM_SEEDSTONE = 4200;
	public static int MOLE_ITEM_CHITIN_ARMOR = 4111;
	//Placeholder for all 4 types of armor
	public static int MOLE_ITEM_CHITIN_TOOLS = 4115;
	//Placeholder for all 5 types of tools
	
	//Sub-Item IDs
	public static final int MOLE_GRUB_WHITE = 0;
	public static final int MOLE_GRUB_FAT = 1;
	public static final int MOLE_GRUB_RED = 2;
	
	public static final int MOLE_CLUMP_EARTH = 0; //Earth
	public static final int MOLE_CLUMP_FIRE = 1; //Hot
	public static final int MOLE_CLUMP_ICE = 2; //Cold
	public static final int MOLE_CLUMP_SAND = 3; //Dry
	public static final int MOLE_CLUMP_WATER = 4; //Mud
	public static final int MOLE_CLUMP_DARK = 5; //Dark
	public static final int MOLE_CLUMP_LIGHT = 6; //Glowing
	public static final int MOLE_CLUMP_PLANT = 7; //Fertile
	public static final int MOLE_CLUMP_EVIL = 8; //Sinister
	
	public static final int MOLE_ARMOR_HEAD = 0;
	public static final int MOLE_ARMOR_CHEST = 1;
	public static final int MOLE_ARMOR_LEGS = 2;
	public static final int MOLE_ARMOR_FEET = 3;
	
	public static final int MOLE_STILLSTONE_EYE = 0;
	public static final int MOLE_STILLSTONE_MIGHT = 1;
	public static final int MOLE_STILLSTONE_HARNDESS = 2;
	public static final int MOLE_STILLSTONE_QUICK = 3;
	public static final int MOLE_STILLSTONE_HOME = 4;
	public static final int MOLE_STILLSTONE_BLAST = 5;
	
	//Texture Files
	public static final String MOLE_TEXT_TERRAIN = "/Mole/common/resources/terrain.png";
	public static final String MOLE_TEXT_ITEMS = "/Mole/common/resources/items.png";
	
	//Tool Materials
	public static final EnumToolMaterial MOLECLAW = EnumHelper.addToolMaterial("MOLECLAW", 3, 1024, 10F, 1, 10);
	public static final EnumArmorMaterial CHITIN_ARMOR = EnumHelper.addArmorMaterial("CHITIN", 15, new int[]{2, 4, 3, 1}, 15);
	public static final EnumToolMaterial CHITIN_TOOL = EnumHelper.addToolMaterial("CHITIN", 1, 192, 3.0F, 1, 15);
	
	//Other
	public static int STANDSTONE_AREA_SIZE = 11;
	
	public static void load(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		MOLE_BLOCK_DIRTSTONE = config.get(Configuration.CATEGORY_BLOCK, "dirtstone", MOLE_BLOCK_DIRTSTONE).getInt();
		MOLE_BLOCK_TERRARIUM = config.get(Configuration.CATEGORY_BLOCK, "terrarium", MOLE_BLOCK_TERRARIUM).getInt();
		MOLE_BLOCK_SEEDSTONE = config.get(Configuration.CATEGORY_BLOCK, "seedstone", MOLE_BLOCK_SEEDSTONE).getInt();
		STANDSTONE_AREA_SIZE = config.get(Configuration.CATEGORY_GENERAL, "stoneAreaSize", STANDSTONE_AREA_SIZE).getInt();
		
		config.save();
	}
}
