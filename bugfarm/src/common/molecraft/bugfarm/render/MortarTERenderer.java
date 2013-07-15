package common.molecraft.bugfarm.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import common.molecraft.bugfarm.block.TEMortar;
import common.molecraft.bugfarm.model.ModelMortar;

public class MortarTERenderer extends TileEntitySpecialRenderer {

	ModelMortar model;
	
	public static String[] materialTextures = {
		"mortarWood.png",
		"mortarStone.png",
		"mortarObsidian.png"
	};
	
	public MortarTERenderer() {
		model = new ModelMortar();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,
			double z, float f) {
		int rotation = 0;

		if (tileentity.worldObj != null)
		{
			rotation = tileentity.getBlockMetadata();
		}
		
		TEMortar mortar = (TEMortar)tileentity;
		
		bindTextureByName("/textures/blocks/"+materialTextures[mortar.bowlTier.ordinal()]);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x+0.5f, (float)y+0.5f, (float)z+0.5f);
		GL11.glScalef(1f, -1f, -1f);
		GL11.glRotatef(rotation*90, 0f, 1f, 0f);
		model.renderAll();
		GL11.glPopMatrix();
	}

}
