package Mole.common.seedstone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Mole.common.Constants;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSeedstone extends BlockContainer {

	public static final Map<Integer, Integer> textureIndex = new HashMap<Integer, Integer>() {{
		put(Block.dirt.blockID, 0);
		put(Block.sand.blockID, 1);
		put(Block.gravel.blockID, 2);
		put(Block.blockClay.blockID, 3);
		put(Block.netherrack.blockID, 4);
		put(Block.slowSand.blockID, 5);
	}};
	
	private final static String[] subNames = {
		"dirt", "sand", "gravel", "clay", "netherrack", "soulSand"
	};
	
	private final static String[] blockNames = {
			"Dirt", "Sand", "Gravel", "Clay", "Netherrack", "Soul Sand"
	};
	
	public BlockSeedstone() {
		super(Constants.MOLE_BLOCK_SEEDSTONE, Material.ground);
		setTickRandomly(true);
		setBlockName("seededBlock");
		
		GameRegistry.registerBlock(this, ItemBlockSeeded.class);
		for (int i=0; i < subNames.length; i++)
        {
        	ItemStack blockType = new ItemStack(this, 1, i);
        	LanguageRegistry.addName(blockType, "Seeded "+blockNames[i]);
        }
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		
		return new TileSeedstoneSparkler();
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		
		meta = meta % textureIndex.size();
			
		return 32 + meta;
	}

	@Override
	public void updateTick(World world, int x, int y, int z,
			Random random) {
		
		TileSeedstone seeded = (TileSeedstone) world.getBlockTileEntity(x, y, z);
		
		if (seeded != null){
			System.out.println("CHARGE "+x+","+y+","+z+"["+FMLCommonHandler.instance().getEffectiveSide()+"]");
			seeded.chargeUp();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y,
			int z, Random rand) {
		TileSeedstoneSparkler seeded = (TileSeedstoneSparkler) world.getBlockTileEntity(x, y, z);
		
		if (seeded != null) {
			seeded.sparkle(world, world.getBlockId(x, y, z));
		}
	}

	@Override
	public String getTextureFile() {
		// TODO Auto-generated method stub
		return Constants.MOLE_TEXT_TERRAIN;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tabs,
			List list) {
		
		for (int i=0; i < textureIndex.size(); i++)
		{
			list.add(new ItemStack(id, 1, i));
		}
	}

	@Override
	public int damageDropped(int damage) {
		return damage;
	}
	
	
}
