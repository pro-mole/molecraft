package common.molecraft.bugfarm.trap;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BasicTrap extends Trap {

	public BasicTrap() {
		super(0);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new TEBasicTrap();
	}

}
