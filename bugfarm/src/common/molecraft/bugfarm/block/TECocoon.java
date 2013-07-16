package common.molecraft.bugfarm.block;

import net.minecraft.tileentity.TileEntity;

public class TECocoon extends TileEntity {
	
	int variety;
	
	public TECocoon(int type)
	{
		this.variety = type;
	}
	
	public int getCocoonTexture()
	{
		return this.variety; 
	}
}
