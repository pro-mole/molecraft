package common.molecraft.bugfarm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=MolecraftBugfarm.modid, name="Molecraft Bug Farm", version="0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MolecraftBugfarm {

	public static final String modid = "molecraft_bugfarm";

	@Init
	public void load(FMLInitializationEvent event) {
		
	}
}
