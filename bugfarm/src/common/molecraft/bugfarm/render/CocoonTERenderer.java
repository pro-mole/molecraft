package common.molecraft.bugfarm.render;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import common.molecraft.bugfarm.block.TECocoon;
import common.molecraft.bugfarm.model.ModelCocoon;

public class CocoonTERenderer extends TileEntitySpecialRenderer {

	ModelCocoon model;
	
	public CocoonTERenderer() {
		model = new ModelCocoon();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,
			double z, float f) {
		int rotation = 0;

		if (tileentity.worldObj != null)
		{
			rotation = (tileentity.getBlockMetadata() - 1) % 4;
			Block cocoonBlock = tileentity.getBlockType();
		}
		
		TECocoon cocoon = (TECocoon)tileentity;
		
		//bindTextureByName("/textures/blocks/cocoon"+cocoon.getCocoonTexture()+".png");
		bindTextureByName("/textures/blocks/"+tileentity.blockType.getUnlocalizedName2()+".png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x+0.5f, (float)y+0.5f, (float)z+0.5f);
		GL11.glScalef(1f, -1f, -1f);
		GL11.glRotatef(rotation*90f, 0f, 1f, 0f);
		model.renderAll();
		GL11.glPopMatrix();
	}

}
