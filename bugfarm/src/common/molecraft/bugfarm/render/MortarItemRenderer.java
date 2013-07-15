package common.molecraft.bugfarm.render;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import common.molecraft.bugfarm.block.Mortar;
import common.molecraft.bugfarm.block.TEMortar;
import common.molecraft.bugfarm.block.Mortar.MaterialMortar;
import common.molecraft.bugfarm.constants.Numbers;
import common.molecraft.bugfarm.model.ModelMortar;

public class MortarItemRenderer implements IItemRenderer {

	ModelMortar model;
	MaterialMortar material;
	
	public MortarItemRenderer(MaterialMortar mat) {
		model = new ModelMortar();
		material = mat;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		// TODO Auto-generated method stub
		TileEntityRenderer.instance.renderTileEntityAt(new TEMortar(material), 0d, 0d, 0d, 0f);
	}

}
