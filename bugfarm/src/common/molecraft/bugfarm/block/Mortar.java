package common.molecraft.bugfarm.block;

import common.molecraft.bugfarm.constants.Numbers;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Mortar extends BlockContainer {

	public static enum MaterialMortar {WOOD, ROCK, OBSIDIAN};
	public static enum MaterialPestle {STICK, FLINT, DIAMOND};
	
	public static Material[] materials = {Material.wood, Material.rock, Material.rock};
	public static String[] names = {"Wooden", "Stone", "Obsidian"};
	public static String[] ids = {"Wood", "Stone", "Obsidian"};
	
	MaterialMortar tier;
	
	public Mortar(MaterialMortar material) {
		super(Numbers.BLOCK_MORTAR+material.ordinal(), materials[material.ordinal()]);
		
		tier = material;
		
		this.setHardness(0.1f + tier.ordinal()*0.2f);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setUnlocalizedName("mortar"+ids[tier.ordinal()]);
		LanguageRegistry.addName(this, names[tier.ordinal()]+" Mortar");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TEMortar(tier);
	}

	@Override
	public int getRenderType() {
		return -2;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
