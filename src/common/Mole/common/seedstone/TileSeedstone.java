package Mole.common.seedstone;

import Mole.common.item.Seedstone.EnumSeedstoneType;
import net.minecraft.src.TileEntity;

public class TileSeedstone extends TileEntity {

	protected EnumSeedstoneType type;
	
	public EnumSeedstoneType getType() { return type; }
	
	public void setType(EnumSeedstoneType T)
	{
		type = T;
	}
}
