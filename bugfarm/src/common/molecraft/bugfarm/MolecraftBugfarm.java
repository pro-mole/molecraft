package common.molecraft.bugfarm;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import common.molecraft.bugfarm.crafting.PestleMortarCraftingHandler;
import common.molecraft.bugfarm.item.Dust;
import common.molecraft.bugfarm.item.Dust.DustType;
import common.molecraft.bugfarm.item.PestleAndMortar;
import common.molecraft.bugfarm.item.StickMesh;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=MolecraftBugfarm.modid, name="Molecraft Bug Farm", version="0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MolecraftBugfarm {

	public static final String modid = "molecraft_bugfarm";
	
	//Blocks
	
	//Items
	public static Item stickMesh, pestleMortar,
		dustWood, dustReed, dustMeat, dustLeaf, dustGrub, dustGourd, dustInk, dustFish, dustFungus;
	
	//Tile Entities

	@Init
	public void load(FMLInitializationEvent event) {
		
		stickMesh = new StickMesh();
		pestleMortar = new PestleAndMortar();
		dustWood = new Dust(DustType.DUST_WOOD);
		dustReed = new Dust(DustType.DUST_REED);
		dustMeat = new Dust(DustType.DUST_MEAT);
		dustLeaf = new Dust(DustType.DUST_LEAF);
		dustGrub = new Dust(DustType.DUST_GRUB);
		dustGourd = new Dust(DustType.DUST_GOURD);
		dustInk = new Dust(DustType.DUST_INK);
		dustFish = new Dust(DustType.DUST_FISH);
		dustFungus = new Dust(DustType.DUST_FUNGUS);
		
		//Crafting Recipes
		GameRegistry.registerCraftingHandler(new PestleMortarCraftingHandler());
		
		GameRegistry.addRecipe(new ItemStack(stickMesh),
			"//",
			"//", 
			'/', Item.stick);
		GameRegistry.addRecipe(new ItemStack(pestleMortar),
			" v ",
			"o o",
			" o ",
			'v', Item.flint,
			'o', Block.stone);
		dustRecipes();
	}
	
	public void dustRecipes()
	{	
		for (int d=0; d < pestleMortar.getMaxDamage(); d++)
		{
			for (int i=0; i < BlockLog.woodType.length; i++)
			GameRegistry.addShapelessRecipe(new ItemStack(dustWood),
					new ItemStack(pestleMortar,1, d), new ItemStack(Block.wood, 1, i));
		}
	}
}
