package common.molecraft.bugfarm.block;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import common.molecraft.Randomizer;
import common.molecraft.bugfarm.MolecraftBugfarm;
import common.molecraft.bugfarm.constants.Numbers;
import common.molecraft.bugfarm.grub.Grub;
import common.molecraft.bugfarm.grub.SlimeGrub;
import common.molecraft.bugfarm.grub.WaterGrub;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Burrows extends Block {
	
	static Block[] burrowed = {Block.dirt, Block.sand, Block.blockClay, Block.netherrack};
	public static String[] names = {"Dirt","Sand","Clay","Nether"},
			ids = {"dirt","sand","clay","nether"};
	
	Block matrix;
	Icon top;
	
	public Burrows(int blockindex)
	{
		super(Numbers.BLOCK_BURROW+blockindex, burrowed[blockindex].blockMaterial);
		
		matrix = burrowed[blockindex];
		this.setHardness(matrix.blockHardness);
		this.setResistance(matrix.blockHardness);
		
		setUnlocalizedName(ids[blockindex] + "Burrow");
		LanguageRegistry.addName(this, names[blockindex]+" Burrow");
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		
		if (side == 1) //Top
		{
			return top;
		}
		else
		{
			return matrix.getBlockTextureFromSide(side);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		// TODO Auto-generated method stub
		top = register.registerIcon(getUnlocalizedName2());
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y,
			int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		
		HashMap<Item, Float> grubOdds = new HashMap<Item, Float>();
		grubOdds.put(MolecraftBugfarm.grubDirt, 5f);
		
		BiomeDictionary.Type[] biomes = BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(x, z));
		
		for (BiomeDictionary.Type b: biomes)
		{
			switch(b)
			{
				case WATER:
					grubOdds.put(MolecraftBugfarm.grubWater, 4f);
					grubOdds.put(MolecraftBugfarm.grubSlime, 1f);
					break;
				case SWAMP:
					grubOdds.put(MolecraftBugfarm.grubSlime, 3f);
					grubOdds.put(MolecraftBugfarm.grubPoison, 1f);
					grubOdds.put(MolecraftBugfarm.grubTrap, 0.5f);
					break;
				case BEACH:
					grubOdds.put(MolecraftBugfarm.grubWater, 3f);
					grubOdds.put(MolecraftBugfarm.grubTrap, 1f);
					break;
				case DESERT:
					grubOdds.put(MolecraftBugfarm.grubTrap, 4f);
					grubOdds.put(MolecraftBugfarm.grubShiny, 1f);
					grubOdds.put(MolecraftBugfarm.grubGold, 0.5f);
					break;
				case FOREST:
					grubOdds.put(MolecraftBugfarm.grubSilk, 2f);
					grubOdds.put(MolecraftBugfarm.grubPoison, 2f);
					grubOdds.put(MolecraftBugfarm.grubFat, 2f);
					grubOdds.put(MolecraftBugfarm.grubWhite, 2f);
					break;
				case FROZEN:
					grubOdds.put(MolecraftBugfarm.grubFat, 4f);
					grubOdds.put(MolecraftBugfarm.grubShiny, 1f);
					break;
				case HILLS:
					break;
				case JUNGLE:
					grubOdds.put(MolecraftBugfarm.grubPoison, 4f);
					grubOdds.put(MolecraftBugfarm.grubSilk, 2f);
					break;
				case MOUNTAIN:
					break;
				case WASTELAND:
					grubOdds.put(MolecraftBugfarm.grubTrap, 2f);
					break;
				case NETHER:
					grubOdds.put(MolecraftBugfarm.grubHell, 10f);
					break;
				default:
					break;
			}
		}
		
		long time = world.getWorldTime();
		if (time > 13000 && time <= 21000)
		{
			grubOdds.put(MolecraftBugfarm.grubGlow, 2f);
		}
		System.out.println("Current time: "+time);
		
		drops.add(new ItemStack(Randomizer.choose(grubOdds)));
		
		return drops;
	}
}