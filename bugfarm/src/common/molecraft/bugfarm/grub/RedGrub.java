package common.molecraft.bugfarm.grub;

import net.minecraft.potion.PotionHelper;

public class RedGrub extends Grub {
	
	public RedGrub()
	{
		super(Grub.GrubType.GRUB_RED);
		
		//Brew like redstone
		this.setPotionEffect(PotionHelper.redstoneEffect);
	}

}
