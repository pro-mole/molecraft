package common.molecraft.bugfarm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import common.molecraft.bugfarm.constants.*;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class StickMesh extends Item {

	public StickMesh() {
		super(Numbers.ITEM_STICKMESH);
		
		this.setMaxDamage(64);
		this.setUnlocalizedName("stickmesh");
		LanguageRegistry.addName(this, "Stick Mesh");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	
}