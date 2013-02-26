package Mole.common.seedstone;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.math.*;

import Mole.common.Constants;
import Mole.common.Mole;
import Mole.common.seedstone.TileSeedstone.Type;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Seedstone extends Item {

	int tier;
	TileSeedstone.Type type;
	
	String[] itemNames = new String[] {"pillar"};
	String[] uiNames = new String[] {"Pillar"};
	
	public Seedstone(TileSeedstone.Type type, int tier) {
		super(Constants.MOLE_ITEM_SEEDSTONE+type.ordinal());
		
		if (tier < 0) this.tier = 0;
		if (tier >= 4) this.tier = 4;
		this.type = type;
		
		setIconIndex(80+type.ordinal());
		setItemName("seedstone_"+itemNames[type.ordinal()]);
		LanguageRegistry.addName(this, uiNames[type.ordinal()]+" Seedstone");
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack item,
			EntityPlayer player, World world, int x, int y,
			int z, int side, float xoff, float yoff, float zoff) {
		
		int block = world.getBlockId(x, y, z);
		if (!Mole.isDirtLike(block))
			return false;
		
		if (block == Block.grass.blockID) block = Block.dirt.blockID;
		
		world.setBlockAndMetadataWithNotify(x, y, z, Constants.MOLE_BLOCK_SEEDSTONE, BlockSeedstone.textureIndex.get(block));
		//Crate seedstone on server only
		//The client TE will be a "sparkler
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
		if (type == Type.PILLAR)
			world.setBlockTileEntity(x, y, z, new SeedstonePillar(block,1));
		}
		item.damageItem(1, player);
		
		return false;
	}

	@Override
	public int getMaxDamage() {
		// TODO Auto-generated method stub
		return (int) Math.pow(3.0, 2.0 + tier);
	}

	@Override
	public String getTextureFile() {

		return Constants.MOLE_TEXT_ITEMS;
	}
}
