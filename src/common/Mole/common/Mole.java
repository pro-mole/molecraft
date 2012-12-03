package Mole.common;

import net.minecraft.src.Block;
import net.minecraft.src.BlockLog;
import net.minecraft.src.BlockOre;
import net.minecraft.src.BlockWood;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemAppleGold;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import Mole.common.block.*;
import Mole.common.item.*;
import Mole.common.machine.*;
import Mole.common.seedstone.TileSeedstoneHouse;
import Mole.common.terrarium.IGuiTerrariumHandler;
import Mole.common.terrarium.MachineTerrarium;
import Mole.common.terrarium.TileTerrarium;
import Mole.common.tools.MoleClaw;
import Mole.common.tools.MoleSpade;

@Mod(modid="mod_Molecraft", name="Molecraft", version="0.5.0")
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
		seedstone[] = {new Seedstone(Seedstone.EnumSeedstoneType.HOUSE)};
	
	public static Block
		dirtstone = new DirtStone(false), dirtstone_baked = new DirtStone(true),
		terrarium = new MachineTerrarium(),
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
		for (Block seedBlock: seedstoneBlock)
			GameRegistry.registerBlock(seedBlock);
		GameRegistry.registerTileEntity(TileTerrarium.class, "Terrarium");
		GameRegistry.registerTileEntity(TileSeedstoneHouse.class, "SeedstoneHouse");
		
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
