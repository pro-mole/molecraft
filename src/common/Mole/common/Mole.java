package Mole.common;

import net.minecraft.src.Block;
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
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import Mole.common.block.*;
import Mole.common.item.*;
import Mole.common.machine.*;
import Mole.common.seedstone.TileSeedstoneHouse;

@Mod(modid="mod_Molecraft", name="Molecraft", version="0.5.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
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
		mealWorm = new BugMealWorm(),
		bombyxMori = new BugBombyx(),
		coccineal = new BugCoccineal(),
		dirtClump = new Clump(),
		seedstone[] = {new Seedstone(Seedstone.EnumSeedstoneType.HOUSE)};
	
	public static Block
		dirtstone = new DirtStone(false), dirtstone_baked = new DirtStone(true),
		terrarium = new MachineTerrarium(),
		seedstoneBlock[] = {new MachineSeedstone(Seedstone.EnumSeedstoneType.HOUSE)};
		
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
		GameRegistry.registerTileEntity(TileSeedstoneHouse.class, "House Seedstone");
		
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
		
		GameRegistry.addShapelessRecipe(new ItemStack(bugFood,4), new ItemStack(Item.seeds), new ItemStack(Block.wood), new ItemStack(Block.dirt));
		GameRegistry.addShapelessRecipe(new ItemStack(bugFoodPremium,4), new ItemStack(grub), new ItemStack(Item.beefRaw), new ItemStack(Block.dirt));
		
		GameRegistry.addSmelting(dirtstone.blockID, new ItemStack(dirtstone_baked), 0.1F);
		GameRegistry.addSmelting(grub.shiftedIndex, new ItemStack(grubCooked), 0.1F);
	}

	public static boolean isDirtLike(int blockID)
	{
		return (blockID == Block.dirt.blockID || blockID == Block.grass.blockID || blockID == Block.gravel.blockID || blockID == Block.sand.blockID || blockID == Block.blockClay.blockID || blockID == Block.netherrack.blockID);
	}
}
