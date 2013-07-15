package common.molecraft.bugfarm.block;

import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

import common.molecraft.bugfarm.block.Mortar.MaterialMortar;
import common.molecraft.bugfarm.block.Mortar.MaterialPestle;

public class TEMortar extends TileEntity {

	Slot matter, pestle, output;
	
	public MaterialMortar bowlTier;
	public MaterialPestle pestleTier;
	
	public TEMortar(MaterialMortar material) {
		bowlTier = material;
	}

}
