package Mole.common.terrarium;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GUITerrarium extends GuiContainer {

	public GUITerrarium (InventoryPlayer invPlayer, TileTerrarium TE)
	{
		super(new ContainerTerrarium(invPlayer, TE));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j)
	{
		fontRenderer.drawString("Terrarium", 8, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {

		// TODO Auto-generated method stub
		int texture = mc.renderEngine.getTexture("/Mole/common/gui/terrarium.png");
		GL11.glColor4f(1f, 1f, 1f, 1f);
		mc.renderEngine.bindTexture(texture);
		
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
