package common.molecraft.bugfarm;

import scala.xml.parsing.FatalError;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;

import common.molecraft.bugfarm.block.Cocoon;
import common.molecraft.bugfarm.block.Mortar;
import common.molecraft.bugfarm.block.TECocoon;
import common.molecraft.bugfarm.block.TEMortar;
import common.molecraft.bugfarm.crafting.PestleMortarCraftingHandler;
import common.molecraft.bugfarm.grub.DirtGrub;
import common.molecraft.bugfarm.grub.FatGrub;
import common.molecraft.bugfarm.grub.GlowGrub;
import common.molecraft.bugfarm.grub.GoldGrub;
import common.molecraft.bugfarm.grub.HellGrub;
import common.molecraft.bugfarm.grub.MysticGrub;
import common.molecraft.bugfarm.grub.PoisonGrub;
import common.molecraft.bugfarm.grub.RedGrub;
import common.molecraft.bugfarm.grub.ShinyGrub;
import common.molecraft.bugfarm.grub.SilkGrub;
import common.molecraft.bugfarm.grub.SlimeGrub;
import common.molecraft.bugfarm.grub.TinyGrub;
import common.molecraft.bugfarm.grub.TrapGrub;
import common.molecraft.bugfarm.grub.WaterGrub;
import common.molecraft.bugfarm.grub.WhiteGrub;
import common.molecraft.bugfarm.item.Dust;
import common.molecraft.bugfarm.item.Dust.DustType;
import common.molecraft.bugfarm.item.PestleAndMortar;
import common.molecraft.bugfarm.item.StickMesh;
import common.molecraft.bugfarm.render.CocoonTERenderer;
import common.molecraft.bugfarm.world.CocoonGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=MolecraftBugfarm.modid, name="Molecraft Bug Farm", version="0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MolecraftBugfarm {

	public static final String modid = "molecraft_bugfarm";
	
	@SidedProxy(clientSide="common.molecraft.bugfarm.MolecraftBugfarmClientProxy", serverSide="common.molecraft.bugfarm.MolecraftBugfarmProxy")
	public static MolecraftBugfarmClientProxy proxy;
	
	//Blocks
	public static Block woodMortar, stoneMortar, obsidianMortar,
		cocoon[], gall, reedNest, burrow;
	
	//Items
	public static Item stickMesh, pestleMortarWood, pestleMortarStone,
		dustWood, dustReed, dustMeat, dustLeaf, dustGrub, dustGourd, dustInk, dustFish, dustFungus,
		grubWhite, grubRed, grubFat, grubTiny, grubWater, grubDirt, grubPoison, grubSilk, grubGlow, grubTrap, grubSlime, grubHell, grubShiny, grubGold, grubMystic;
	
	//Tile Entities
	
	//Models
	public static int mortarModelId;

	@Init
	public void load(FMLInitializationEvent event) {
		
		stickMesh = new StickMesh();
		pestleMortarWood = new PestleAndMortar(0);
		pestleMortarStone = new PestleAndMortar(1);
		
		woodMortar = new Mortar(Mortar.MaterialMortar.WOOD);
		GameRegistry.registerBlock(woodMortar, "mortarWood");
		stoneMortar = new Mortar(Mortar.MaterialMortar.ROCK);
		GameRegistry.registerBlock(stoneMortar, "mortarStone");
		obsidianMortar = new Mortar(Mortar.MaterialMortar.OBSIDIAN);
		GameRegistry.registerBlock(obsidianMortar, "mortarObsidian");
		GameRegistry.registerTileEntity(TEMortar.class, "TEMortar");
		
		dustWood = new Dust(DustType.DUST_WOOD);
		dustReed = new Dust(DustType.DUST_REED);
		dustMeat = new Dust(DustType.DUST_MEAT);
		dustLeaf = new Dust(DustType.DUST_LEAF);
		dustGrub = new Dust(DustType.DUST_GRUB);
		dustGourd = new Dust(DustType.DUST_GOURD);
		dustInk = new Dust(DustType.DUST_INK);
		dustFish = new Dust(DustType.DUST_FISH);
		dustFungus = new Dust(DustType.DUST_FUNGUS);
		
		cocoon = new Cocoon[Cocoon.ids.length];
		for (int i=0; i < Cocoon.ids.length; i++)
		{
			cocoon[i] = new Cocoon(i);
			GameRegistry.registerBlock(cocoon[i]);
		}
		GameRegistry.registerTileEntity(TECocoon.class, "TECocoon");
		
		grubWhite = new WhiteGrub();
		grubRed = new RedGrub();
		grubFat = new FatGrub();
		grubTiny = new TinyGrub();
		grubDirt = new DirtGrub();
		grubTrap = new TrapGrub();
		grubWater = new WaterGrub();
		grubPoison = new PoisonGrub();
		grubGlow = new GlowGrub();
		grubSlime = new SlimeGrub();
		grubSilk = new SilkGrub();
		grubShiny = new ShinyGrub();
		grubGold = new GoldGrub();
		grubHell = new HellGrub();
		grubMystic = new MysticGrub();
		
		proxy.registerRenderInformation();
		
		//Crafting Recipes
		GameRegistry.registerCraftingHandler(new PestleMortarCraftingHandler());
		
		GameRegistry.addRecipe(new ItemStack(stickMesh),
			"//",
			"//", 
			'/', Item.stick);
		GameRegistry.addRecipe(new ItemStack(pestleMortarWood),
			" / ",
			"o o",
			" o ",
			'/', Item.stick,
			'o', Block.cobblestone);
		GameRegistry.addRecipe(new ItemStack(pestleMortarStone),
			" v ",
			"o o",
			" o ",
			'v', Item.flint,
			'o', Block.stone);
		
		addDustRecipes();
		
		GameRegistry.registerWorldGenerator(new CocoonGenerator());
	}
	
	public void addDustRecipes()
	{	
		//Wood Dust
		for (int i=0; i < BlockLog.woodType.length; i++)
		{
			dustRecipe(1, new ItemStack(Block.wood,1,i), dustWood, 4);
			dustRecipe(1, new ItemStack(Block.planks,1,i), dustWood, 2);
		}
		dustRecipe(1, new ItemStack(Item.stick), dustWood, 1);
		
		//Reed Dust
		dustRecipe(0, new ItemStack(Block.reed), dustReed, 3);
		
		//Meat Dust
		for (Item meat: new Item[] {Item.beefRaw, Item.chickenRaw, Item.porkRaw})
		{
			dustRecipe(0, new ItemStack(meat), dustMeat, 4);
		}
		
		//Leaf Dust
		for (Item seed: new Item[] {Item.seeds, Item.pumpkinSeeds})
		{
			dustRecipe(0, new ItemStack(seed), dustLeaf, 1);
		}
		dustRecipe(0, new ItemStack(Block.tallGrass), dustLeaf, 2);
		for (int i=0; i < BlockLog.woodType.length; i++)
			dustRecipe(0, new ItemStack(Block.leaves,1,i), dustLeaf, 2);
		
		//Grub Dust
		
		//Gourd Dust
		for (Block gourd: new Block[] {Block.pumpkin, Block.melon})
		{
			dustRecipe(0, new ItemStack(gourd), dustGourd, 4);
		}
		
		//Ink Dust
		dustRecipe(0, new ItemStack(Item.dyePowder, 1, 0), dustInk, 2);
		
		//Fish Dust
		dustRecipe(0, new ItemStack(Item.fishRaw), dustFish, 3);
		
		//Fungus Dust
		for (Block shroom: new Block[] {Block.mushroomBrown, Block.mushroomRed})
		{
			dustRecipe(0, new ItemStack(shroom), dustFungus, 1);
		}
	}
	
	public void dustRecipe(int tier, ItemStack ingredient, Item result, int amount)
	{
		if (tier <= 0)
		{
			for (int d=0; d < pestleMortarWood.getMaxDamage(); d++)
			{
				GameRegistry.addShapelessRecipe(new ItemStack(result, amount),
						new ItemStack(pestleMortarWood,1, d), ingredient);
			}
		}
		
		if (tier <= 1)
		{
			for (int d=0; d < pestleMortarStone.getMaxDamage(); d++)
			{
				GameRegistry.addShapelessRecipe(new ItemStack(result, amount),
						new ItemStack(pestleMortarStone,1, d), ingredient);
			}
		}
	}
}
