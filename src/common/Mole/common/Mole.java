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
import Mole.common.machine.IGuiTerrariumHandler;
import Mole.common.machine.MachineTerrarium;
import Mole.common.machine.TileTerrarium;

@Mod(modid="ProMole_Mod", name="Molecraft", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Mole {
	@SidedProxy(clientSide = "Mole.client.ClientProxyMole", serverSide = "Mole.common.CommonProxyMole")
	public static CommonProxyMole proxy;
	
	@Instance("ProMole_Mod")
	public static Mole instance;
	
	public static Item
		moleSpade = new MoleSpade(),
		grub = new Grub(0),
		grubCooked = new CookedGrub(),
		bugFood = new BugFood(false), bugFoodPremium = new BugFood(true),
		beetleStag = new BugStagBeetle();
	
	public static Block
		cobbledirt = new BlockCobbledDirt(),
		dirtstone = new BlockDirtStone(),
		organicdirt = new BlockOrganicDirt(),
		bait = new BlockBait(false),
		baitPremium = new BlockBait(true),
		terrarium = new MachineTerrarium();
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();

		//GUI Handler
		NetworkRegistry.instance().registerGuiHandler(this, new IGuiTerrariumHandler());
		
		//Register Blocks
		GameRegistry.registerBlock(cobbledirt);
		GameRegistry.registerBlock(dirtstone);
		GameRegistry.registerBlock(organicdirt);
		GameRegistry.registerBlock(bait);
		GameRegistry.registerBlock(baitPremium);
		GameRegistry.registerBlock(terrarium);
		GameRegistry.registerTileEntity(TileTerrarium.class, "Terrarium");
		
		//Register Recipes
		GameRegistry.addRecipe(new ItemStack(cobbledirt),
				new Object[] {
				" # ",
				"# #",
				" # ",
				'#', new ItemStack(Block.dirt)
				} );
		GameRegistry.addRecipe(new ItemStack(cobbledirt),
				new Object[] {
				"# #",
				"   ",
				"# #",
				'#', new ItemStack(Block.dirt)
				} );
		
		GameRegistry.addRecipe(new ItemStack(dirtstone),
				new Object[] {
				"%#%",
				"# #",
				"%#%",
				'#', new ItemStack(cobbledirt),
				'%', new ItemStack(Block.dirt)
				} );
		GameRegistry.addRecipe(new ItemStack(dirtstone),
				new Object[] {
				"%#%",
				"# #",
				"%#%",
				'%', new ItemStack(cobbledirt),
				'#', new ItemStack(Block.dirt)
				} );
		
		GameRegistry.addRecipe(new ItemStack(organicdirt),
				new Object[] {
				" : ",
				":#:",
				" : ",
				'#', new ItemStack(Block.dirt),
				':', new ItemStack(Item.seeds)
				} );
		
		GameRegistry.addRecipe(new ItemStack(moleSpade),
			new Object[] {
			"A",
			"I",
			'A', new ItemStack(Item.flint),
			'I', new ItemStack(Item.stick),
			} );
		
		GameRegistry.addRecipe(new ItemStack(terrarium),
				new Object[] {
				"===",
				"I#I",
				"IOI",
				'O', new ItemStack(organicdirt),
				'#', new ItemStack(Block.thinGlass),
				'I', new ItemStack(Item.stick),
				'=', new ItemStack(Block.woodSingleSlab)
				} );
		
		for (int i=0; i < BlockWood.woodType.length; i++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(bait),
				new ItemStack(Block.planks, 1, i), new ItemStack(bugFood), new ItemStack(bugFood), new ItemStack(bugFood));
			
			GameRegistry.addShapelessRecipe(new ItemStack(baitPremium),
				new ItemStack(Block.planks, 1, i), new ItemStack(bugFoodPremium), new ItemStack(bugFoodPremium), new ItemStack(bugFoodPremium));
			
			for (Item seed: new Item[]{ Item.seeds, Item.pumpkinSeeds, Item.melonSeeds, Item.wheat, Item.appleRed} )
				GameRegistry.addShapelessRecipe(new ItemStack(bugFood, 4),
					new ItemStack(seed), new ItemStack(Block.dirt), new ItemStack(Block.wood, 1, i));
		}
		
		for (Item meat: new Item[] { Item.rottenFlesh, Item.porkRaw, Item.beefRaw, Item.fishRaw, Item.chickenRaw} )
		{
			GameRegistry.addShapelessRecipe(new ItemStack(bugFoodPremium, 4),
				new ItemStack(grub), new ItemStack(Block.dirt), new ItemStack(meat));
		}
		
		GameRegistry.addSmelting(grub.shiftedIndex, new ItemStack(grubCooked), 0.1F);
	}

}
