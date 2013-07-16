package common.molecraft.bugfarm.render;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import common.molecraft.bugfarm.block.TECocoon;
import common.molecraft.bugfarm.model.ModelCocoon;

public class CocoonItemRenderer implements IItemRenderer {

	ModelCocoon model;
	int variety;
	
	public CocoonItemRenderer(int type) {
		model = new ModelCocoon();
		variety = type;
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
		TileEntityRenderer.instance.renderTileEntityAt(new TECocoon(variety), 0d, 0d, 0d, 0f);
	}

}
