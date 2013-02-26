package Mole.common;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.world.biome.BiomeGenBase;
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
	
	//Item IDs [4099-4226]
	//Mole Tools and General Items [4099-4114] (16)
	public static int MOLE_ITEM_SPADE = 4099;
	public static int MOLE_ITEM_CLAWS = 4100;
	public static int MOLE_ITEM_BUGFOOD = 4101;
	public static int MOLE_ITEM_BUGFOOD_PREMIUM = 4102;
	//Grubs [4115-4116]
	public static int MOLE_ITEM_GRUB = 4115;
	public static int MOLE_ITEM_GRUB_COOKED = 4116;
	//Clumps [4117]
	public static int MOLE_ITEM_CLUMP = 4117;
	//Bugs and Husks [4118-4149] (32)
	public static int MOLE_ITEM_BUG_HUSK = 4118;
	public static int MOLE_ITEM_BUG_STAGBEETLE = 4119;
	public static int MOLE_ITEM_BUG_BOMBYX = 4120;
	public static int MOLE_ITEM_BUG_COCCINEAL = 4121;
	public static int MOLE_ITEM_BUG_WATERBEETLE = 4122;
	//Seedstones[4150-4181] (32)
	public static int MOLE_ITEM_SEEDSTONE = 4150;
	//Armor [4181-4196] (16)
	public static int MOLE_ITEM_CHITIN_ARMOR = 4181; // [4181-4184]
	//Tools [4197-4216] (20)
	public static int MOLE_ITEM_CHITIN_TOOLS = 4197; // [4197-4201]
	//Runes [4217-4220] (4)
	public static int MOLE_ITEM_RUNE_ASPECT = 4217;
	public static int MOLE_ITEM_RUNE_PIGMENT = 4218;
	public static int MOLE_ITEM_RUNE_ELEMENT = 4219;
	public static int MOLE_ITEM_RUNE_COMPONENT = 4220;
	
	//Sub-Item IDs
	public static final int MOLE_GRUB_WHITE = 0;
	public static final int MOLE_GRUB_FAT = 1;
	public static final int MOLE_GRUB_RED = 2;
	public static final int MOLE_GRUB_WATER = 3;
	public static final int MOLE_GRUB_FUR = 0;
	public static final int MOLE_GRUB_VENOM = 0;
	public static final int MOLE_GRUB_SHELL = 0;
	
	public static final int MOLE_CLUMP_EARTH = 0; //Earth
	public static final int MOLE_CLUMP_FIRE = 1; //Hot
	public static final int MOLE_CLUMP_ICE = 2; //Cold
	public static final int MOLE_CLUMP_SAND = 3; //Dry
	public static final int MOLE_CLUMP_WATER = 4; //Mud
	public static final int MOLE_CLUMP_DARK = 5; //Dark
	public static final int MOLE_CLUMP_LIGHT = 0; //Glowing
	public static final int MOLE_CLUMP_PLANT = 0; //Fertile
	public static final int MOLE_CLUMP_EVIL = 0; //Sinister
	
	public static final int MOLE_ARMOR_HEAD = 0;
	public static final int MOLE_ARMOR_CHEST = 1;
	public static final int MOLE_ARMOR_LEGS = 2;
	public static final int MOLE_ARMOR_FEET = 3;
	
	public static final int MOLE_BOOK_EARTH = 0;
	public static final int MOLE_BOOK_STONE = 1;
	public static final int MOLE_BOOK_FIGHTING = 2;
	public static final int MOLE_BOOK_DARKNESS = 3;
	public static final int MOLE_BOOK_HEALING = 4;
	public static final int MOLE_BOOK_LIGHT = 5;
	public static final int MOLE_BOOK_SILENCE = 6;
	
	//Texture Files
	public static final String MOLE_TEXT_TERRAIN = "/Mole/common/resources/terrain.png";
	public static final String MOLE_TEXT_ITEMS = "/Mole/common/resources/items.png";
	
	//Tool Materials
	public static final EnumToolMaterial MOLECLAW = EnumHelper.addToolMaterial("MOLECLAW", 3, 1024, 10F, 1, 10);
	public static final EnumArmorMaterial CHITIN_ARMOR = EnumHelper.addArmorMaterial("CHITIN", 15, new int[]{2, 4, 3, 1}, 15);
	public static final EnumToolMaterial CHITIN_TOOL = EnumHelper.addToolMaterial("CHITIN", 1, 192, 3.0F, 1, 15);
	
	//Other
	public static int STANDSTONE_AREA_SIZE = 11;
	
	//Hash Maps
	//Odd modifiers for each biome
	public static final HashMap<BiomeGenBase, Integer> clumpForBiome = new HashMap() {{
		put (BiomeGenBase.plains, MOLE_CLUMP_EARTH);
		put (BiomeGenBase.beach, MOLE_CLUMP_WATER);
		put (BiomeGenBase.river, MOLE_CLUMP_WATER);
		put (BiomeGenBase.ocean, MOLE_CLUMP_WATER);
		put (BiomeGenBase.taiga, MOLE_CLUMP_ICE);
		put (BiomeGenBase.frozenRiver, MOLE_CLUMP_ICE);
		put (BiomeGenBase.frozenOcean, MOLE_CLUMP_ICE);
		put (BiomeGenBase.taigaHills, MOLE_CLUMP_ICE);
		put (BiomeGenBase.forest, MOLE_CLUMP_EARTH);
		put (BiomeGenBase.forestHills, MOLE_CLUMP_EARTH);
		put (BiomeGenBase.desert, MOLE_CLUMP_SAND);
		put (BiomeGenBase.desertHills, MOLE_CLUMP_SAND);
		put (BiomeGenBase.swampland, MOLE_CLUMP_WATER);
		put (BiomeGenBase.extremeHills, MOLE_CLUMP_EARTH);
		put (BiomeGenBase.extremeHillsEdge, MOLE_CLUMP_EARTH);
		put (BiomeGenBase.iceMountains, MOLE_CLUMP_ICE);
		put (BiomeGenBase.icePlains, MOLE_CLUMP_ICE);
		put (BiomeGenBase.jungle, MOLE_CLUMP_PLANT);
		put (BiomeGenBase.jungleHills, MOLE_CLUMP_PLANT);
		put (BiomeGenBase.mushroomIsland, MOLE_CLUMP_PLANT);
		put (BiomeGenBase.mushroomIslandShore, MOLE_CLUMP_PLANT);
		put (BiomeGenBase.hell, MOLE_CLUMP_FIRE);
	}}; 
	
	public static final HashMap<Integer, Integer> clumpForBlock = new HashMap() {{
		put (Block.dirt.blockID, MOLE_CLUMP_EARTH);
		put (Block.grass.blockID, MOLE_CLUMP_PLANT);
		put (Block.sand.blockID, MOLE_CLUMP_SAND);
		put (Block.blockClay.blockID, MOLE_CLUMP_WATER);
		put (Block.netherrack.blockID, MOLE_CLUMP_FIRE);
		put (Block.gravel.blockID, MOLE_CLUMP_EARTH);
		put (Block.waterStill.blockID, MOLE_CLUMP_WATER);
		put (Block.lavaStill.blockID, MOLE_CLUMP_FIRE);
	}};
	
	public static final HashMap<BiomeGenBase, Integer> grubForBiome = new HashMap() {{
		put (BiomeGenBase.plains, MOLE_GRUB_WHITE);
		put (BiomeGenBase.beach, MOLE_GRUB_WATER);
		put (BiomeGenBase.river, MOLE_GRUB_WATER);
		put (BiomeGenBase.ocean, MOLE_GRUB_WATER);
		put (BiomeGenBase.taiga, MOLE_GRUB_FUR);
		put (BiomeGenBase.frozenRiver, MOLE_GRUB_FUR);
		put (BiomeGenBase.frozenOcean, MOLE_GRUB_FUR);
		put (BiomeGenBase.taigaHills, MOLE_GRUB_FUR);
		put (BiomeGenBase.forest, MOLE_GRUB_FAT);
		put (BiomeGenBase.forestHills, MOLE_GRUB_FAT);
		put (BiomeGenBase.desert, MOLE_GRUB_SHELL);
		put (BiomeGenBase.desertHills, MOLE_GRUB_SHELL);
		put (BiomeGenBase.swampland, MOLE_GRUB_VENOM);
		put (BiomeGenBase.extremeHills, MOLE_GRUB_WHITE);
		put (BiomeGenBase.extremeHillsEdge, MOLE_GRUB_WHITE);
		put (BiomeGenBase.iceMountains, MOLE_GRUB_FUR);
		put (BiomeGenBase.icePlains, MOLE_GRUB_FUR);
		put (BiomeGenBase.jungle, MOLE_GRUB_FAT);
		put (BiomeGenBase.jungleHills, MOLE_GRUB_FAT);
		put (BiomeGenBase.mushroomIsland, MOLE_GRUB_WHITE);
		put (BiomeGenBase.mushroomIslandShore, MOLE_GRUB_WHITE);
		put (BiomeGenBase.hell, MOLE_GRUB_VENOM);
	}};
	
	public static final HashMap<Integer, Integer> grubForBlock = new HashMap() {{
		put (Block.dirt.blockID, MOLE_GRUB_WHITE);
		put (Block.grass.blockID, MOLE_GRUB_WHITE);
		put (Block.sand.blockID, MOLE_GRUB_SHELL);
		put (Block.blockClay.blockID, MOLE_GRUB_WATER);
		put (Block.netherrack.blockID, MOLE_GRUB_WHITE);
		put (Block.gravel.blockID, MOLE_GRUB_WHITE);
		put (Block.waterStill.blockID, MOLE_GRUB_WATER);
		put (Block.redstoneWire.blockID, MOLE_GRUB_RED);
		put (Block.wood.blockID, MOLE_GRUB_FAT);
		put (Block.leaves.blockID, MOLE_GRUB_FAT);
	}};
		
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
