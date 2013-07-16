package common.molecraft.bugfarm;

import net.minecraftforge.client.MinecraftForgeClient;

import common.molecraft.bugfarm.block.Mortar.MaterialMortar;
import common.molecraft.bugfarm.block.Cocoon;
import common.molecraft.bugfarm.block.TECocoon;
import common.molecraft.bugfarm.block.TEMortar;
import common.molecraft.bugfarm.render.CocoonItemRenderer;
import common.molecraft.bugfarm.render.CocoonTERenderer;
import common.molecraft.bugfarm.render.MortarItemRenderer;
import common.molecraft.bugfarm.render.MortarTERenderer;

import cpw.mods.fml.client.registry.ClientRegistry;

public class MolecraftBugfarmClientProxy extends MolecraftBugfarmProxy {

	@Override
	public void registerRenderInformation() {
		ClientRegistry.bindTileEntitySpecialRenderer(TEMortar.class, new MortarTERenderer());
		MinecraftForgeClient.registerItemRenderer(MolecraftBugfarm.woodMortar.blockID, new MortarItemRenderer(MaterialMortar.WOOD));
		MinecraftForgeClient.registerItemRenderer(MolecraftBugfarm.stoneMortar.blockID, new MortarItemRenderer(MaterialMortar.ROCK));
		MinecraftForgeClient.registerItemRenderer(MolecraftBugfarm.obsidianMortar.blockID, new MortarItemRenderer(MaterialMortar.OBSIDIAN));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TECocoon.class, new CocoonTERenderer());
		for (int i=0; i < Cocoon.ids.length; i++)
		{
			MinecraftForgeClient.registerItemRenderer(MolecraftBugfarm.cocoon[i].blockID, new CocoonItemRenderer(i));
		}
	}

}
