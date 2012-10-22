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

@Mod(modid="ProMole_Mod", name="Molecraft", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Mole {
	@SidedProxy(clientSide = "Mole.client.ClientProxyMole", serverSide = "Mole.common.CommonProxyMole")
	public static CommonProxyMole proxy;
	
	@Instance("ProMole_Mod")
	public static Mole instance;
	
	public static Item
		moleSpade = new MoleSpade(),
		moleClaw = new MoleClaw(),
		grub = new Grub(0),
		grubCooked = new CookedGrub(),
		bugFood = new BugFood(false), bugFoodPremium = new BugFood(true),
		beetleStag = new BugStagBeetle();
	
	public static Block
		dirtstone = new DirtStone(false), dirtstone_baked = new DirtStone(true),
		terrarium = new MachineTerrarium();
	
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
		GameRegistry.registerTileEntity(TileTerrarium.class, "Terrarium");
		
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
		
		GameRegistry.addSmelting(dirtstone.blockID, new ItemStack(dirtstone_baked), 0.1F);
		GameRegistry.addSmelting(grub.shiftedIndex, new ItemStack(grubCooked), 0.1F);
	}

}
