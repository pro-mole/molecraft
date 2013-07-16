package common.molecraft.bugfarm.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import common.molecraft.bugfarm.constants.Numbers;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Cocoon extends BlockContainer {
	
	public static String[] names = {"Hard","Silk","Debris"},
		ids = {"hard","silk","debris"};
	
	public static Icon[] icons;
	
	int variety;
	
	public Cocoon(int type) {
		super(Numbers.BLOCK_COCOON+type, Material.leaves);
		
		this.variety = type;
		this.setHardness(0.05f);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setUnlocalizedName(ids[type]+"Cocoon");
		LanguageRegistry.addName(this, names[type]+" Cocoon");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TECocoon(variety);
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