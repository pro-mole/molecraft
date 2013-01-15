package Mole.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Mole.common.block.BlockSeedstone;
import Mole.common.block.DirtStone;
import Mole.common.block.StandingStone;
import Mole.common.equipment.ChitinHelm;
import Mole.common.equipment.ChitinLegging;
import Mole.common.equipment.ChitinPlate;
import Mole.common.equipment.ChitinShoes;
import Mole.common.item.BugBombyx;
import Mole.common.item.BugCoccineal;
import Mole.common.item.BugFood;
import Mole.common.item.BugHusk;
import Mole.common.item.BugStagBeetle;
import Mole.common.item.Clump;
import Mole.common.item.CookedGrub;
import Mole.common.item.Grub;
import Mole.common.item.Seedstone;
import Mole.common.item.Seedstone.EnumSeedstoneType;
import Mole.common.runestone.BlockRunestone;
import Mole.common.seedstone.TileSeedstoneHouse;
import Mole.common.seedstone.TileSeedstoneWell;
import Mole.common.terrarium.IGuiTerrariumHandler;
import Mole.common.terrarium.MachineTerrarium;
import Mole.common.terrarium.TileTerrarium;
import Mole.common.tools.ChitinAxe;
import Mole.common.tools.ChitinHoe;
import Mole.common.tools.ChitinPickaxe;
import Mole.common.tools.ChitinSpade;
import Mole.common.tools.ChitinSword;
import Mole.common.tools.MoleClaw;
import Mole.common.tools.MoleSpade;
import Mole.common.world.MolecraftWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="mod_Molecraft", name="Molecraft", version="0.6.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, 
channels={"MoleSeedstones","MoleTerrarium"}, packetHandler = PacketHandler.class)
public class Mole {
	@SidedProxy(clientSide = "Mole.client.ClientProxyMole", serverSide = "Mole.common.CommonProxyMole")
	public static CommonProxyMole proxy;
	
	@Instance("mod_Molecraft")
	public static Mole instance;
	
	public static Item
		moleSpade = new MoleSpade(),
		moleClaw = new MoleClaw(),
		
		grub = new Grub(),
		grubCooked = new CookedGrub(),
		
		bugFood = new BugFood(false), bugFoodPremium = new BugFood(true),
		
		beetleStag = new BugStagBeetle(),
		emptyHusk = new BugHusk(),
		bombyxMori = new BugBombyx(),
		coccineal = new BugCoccineal(),
		
		dirtClump = new Clump(),
		seedstone[] = {new Seedstone(Seedstone.EnumSeedstoneType.HOUSE), new Seedstone(EnumSeedstoneType.WELL)},
		
		armorChitin[] = {new ChitinHelm(), new ChitinPlate(), new ChitinLegging(), new ChitinShoes()},
		toolChitin[] = {new ChitinAxe(), new ChitinSpade(), new ChitinPickaxe(), new ChitinHoe(), new ChitinSword()};
	
	public static Block
		dirtstone = new DirtStone(false), dirtstone_baked = new DirtStone(true),
		terrarium = new MachineTerrarium(),
		standStone = new StandingStone(),
		runeStone = new BlockRunestone(),
		seedstoneBlock[] = {new BlockSeedstone(Seedstone.EnumSeedstoneType.HOUSE)};
		
	@PreInit
	public void preInit(FMLPreInitializationEvent event) 
	{
		Constants.load(event);
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();

		//GUI Handler
		NetworkRegistry.instance().registerGuiHandler(this, new IGuiTerrariumHandler());
		
		//Register Blocks
		GameRegistry.registerBlock(dirtstone);
		GameRegistry.registerBlock(dirtstone_baked);
		GameRegistry.registerBlock(terrarium);
		GameRegistry.registerBlock(standStone);
		GameRegistry.registerBlock(runeStone);
		for (Block seedBlock: seedstoneBlock)
			GameRegistry.registerBlock(seedBlock);
		GameRegistry.registerTileEntity(TileTerrarium.class, "Terrarium");
		GameRegistry.registerTileEntity(TileSeedstoneHouse.class, "SeedstoneHouse");
		GameRegistry.registerTileEntity(TileSeedstoneWell.class, "SeedstoneWell");
		
		//World Generator
		GameRegistry.registerWorldGenerator(new MolecraftWorldGenerator());
		
		//Register Recipes
		GameRegistry.addRecipe(new ItemStack(dirtstone, 8),
				"###",
				"#U#",
				"###",
				'#', Block.dirt,
				'U', Item.bucketWater);
		
		GameRegistry.addRecipe(new ItemStack(moleClaw),
				"A A",
				"X X",
				'A', Item.flint,
				'X', Item.leather);
		
		GameRegistry.addRecipe(new ItemStack(moleSpade),
				"A",
				"I",
				'A', Item.flint,
				'I', Item.stick);
		
		GameRegistry.addRecipe(new ItemStack(seedstone[0]),
				"OOO",
				"OOO",
				"OOO",
				'O', dirtClump);
		GameRegistry.addRecipe(new ItemStack(seedstone[1]),
				"OOO",
				"OOO",
				"OOO",
				'O', new ItemStack(dirtClump,1,Constants.MOLE_CLUMP_WATER));
		
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder,1,1),
				"C",
				'C', coccineal);
		
		GameRegistry.addRecipe(new ItemStack(terrarium),
				"---",
				"#O#",
				"---",
				'O', Block.dirt,
				'#', Block.thinGlass,
				'-', Block.woodSingleSlab);
		
		GameRegistry.addRecipe(new ItemStack(armorChitin[0]),
				"###",
				"# #",
				'#', emptyHusk);
		GameRegistry.addRecipe(new ItemStack(armorChitin[1]),
				"# #",
				"###",
				"###",
				'#', emptyHusk);
		GameRegistry.addRecipe(new ItemStack(armorChitin[2]),
				"###",
				"# #",
				"# #",
				'#', emptyHusk);
		GameRegistry.addRecipe(new ItemStack(armorChitin[3]),
				"# #",
				"# #",
				'#', emptyHusk);
		
		GameRegistry.addRecipe(new ItemStack(toolChitin[0]),
				"##",
				"|#",
				"| ",
				'#', emptyHusk,
				'|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(toolChitin[1]),
				"#",
				"|",
				"|",
				'#', emptyHusk,
				'|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(toolChitin[2]),
				"###",
				" | ",
				" | ",
				'#', emptyHusk,
				'|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(toolChitin[3]),
				"##",
				"| ",
				"| ",
				'#', emptyHusk,
				'|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(toolChitin[4]),
				"#",
				"#",
				"|",
				'#', emptyHusk,
				'|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(runeStone),
				"##",
				"##",
				'#', standStone);
		
		for (Item vegetal: new Item[] {Item.seeds, Item.appleRed, Item.melon, Item.pumpkinSeeds, Item.melonSeeds, Item.wheat})
		{
			for (int i=0; i < BlockLog.woodType.length; i++)
			{
				GameRegistry.addShapelessRecipe(new ItemStack(bugFood,4), new ItemStack(vegetal), new ItemStack(Block.wood,1,i), new ItemStack(Block.dirt));
			}
		}
		
		for (Item animal: new Item[] {Item.beefRaw, Item.chickenRaw, Item.porkRaw, Item.rottenFlesh, Item.fishRaw})
		{
			for (int i=0; i < Grub.itemNames.length; i++)
			{
				GameRegistry.addShapelessRecipe(new ItemStack(bugFoodPremium,4), new ItemStack(grub,1,i), new ItemStack(animal), new ItemStack(Block.dirt));
			}
		}
		
		GameRegistry.addSmelting(dirtstone.blockID, new ItemStack(dirtstone_baked), 0.1F);
		GameRegistry.addSmelting(grub.shiftedIndex, new ItemStack(grubCooked), 0.1F);
	}

	public static boolean isDirtLike(int blockID)
	{
		return (blockID == Block.dirt.blockID || blockID == Block.grass.blockID || blockID == Block.gravel.blockID || blockID == Block.sand.blockID || blockID == Block.blockClay.blockID || blockID == Block.netherrack.blockID);
	}
}
