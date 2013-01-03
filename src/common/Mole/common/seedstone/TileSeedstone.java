package Mole.common.seedstone;

import net.minecraft.tileentity.TileEntity;
import Mole.common.item.Seedstone.EnumSeedstoneType;

public class TileSeedstone extends TileEntity {

	protected EnumSeedstoneType type;
	
	protected int ticks;
	
	public EnumSeedstoneType getType() { return type; }
	
	public void setType(EnumSeedstoneType T)
	{
		type = T;
	}
}
