package Mole.common.machine;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class IGuiTerrariumHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileTerrarium)
        {
            return new ContainerTerrarium(player.inventory, (TileTerrarium) tileEntity);
        }
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity instanceof TileTerrarium)
		{
			return new GUITerrarium(player.inventory, (TileTerrarium) tileEntity);
		}
		return null;
	}

}
