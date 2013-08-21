package common.molecraft.bugfarm;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;

import common.molecraft.bugfarm.block.Burrows;
import common.molecraft.bugfarm.block.Cocoon;
import common.molecraft.bugfarm.block.Mortar;
import common.molecraft.bugfarm.block.TECocoon;
import common.molecraft.bugfarm.block.TEMortar;
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
import common.molecraft.bugfarm.world.GrubGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=MolecraftBugfarm.modid, name="Molecraft Bug Farm", version="0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MolecraftBugfarm {

	public static final String modid = "molecraft_bugfarm";
	
	@SidedProxy(clientSide="common.molecraft.bugfarm.MolecraftBugfarmClientProxy", serverSide="common.molecraft.bugfarm.MolecraftBugfarmProxy")
	public static MolecraftBugfarmClientProxy proxy;
	
	//Blocks
	public static Block woodMortar, stoneMortar, obsidianMortar,
		cocoon[], gall, reedNest, burrow[];
	
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
		
		burrow = new Burrows[Burrows.ids.length];
		for (int i=0; i < Burrows.ids.length; i++)
		{
			burrow[i] = new Burrows(i);
			GameRegistry.registerBlock(burrow[i]);
		}
		
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
		//grubMystic = new MysticGrub();
		
		proxy.registerRenderInformation();
		
		//Crafting Recipes
		GameRegistry.addRecipe(new ItemStack(stickMesh),
			"//",
			"//", 
			'/', Item.stick);
		
		GameRegistry.addRecipe(new ItemStack(woodMortar),
			"#u#",
			"###",
			'#', new ItemStack(Block.planks),
			'u', new ItemStack(Item.bowlEmpty)
			);
		GameRegistry.addRecipe(new ItemStack(stoneMortar),
			"#u#",
			"###",
			'#', new ItemStack(Block.stone),
			'u', new ItemStack(Item.bowlEmpty)
			);
		GameRegistry.addRecipe(new ItemStack(obsidianMortar),
			"#u#",
			"###",
			'#', new ItemStack(Block.obsidian),
			'u', new ItemStack(Item.bowlEmpty)
			);
		
		GameRegistry.registerWorldGenerator(new GrubGenerator());
	}
}
