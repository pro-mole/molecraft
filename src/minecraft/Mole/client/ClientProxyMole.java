package Mole.client;

import net.minecraftforge.client.MinecraftForgeClient;
import Mole.common.CommonProxyMole;

public class ClientProxyMole extends CommonProxyMole{
	
	@Override
	public void registerRenderThings()
	{
		MinecraftForgeClient.preloadTexture("/Mole/common/resources/terrain.png");
		MinecraftForgeClient.preloadTexture("/Mole/common/resources/items.png");
	}
}
