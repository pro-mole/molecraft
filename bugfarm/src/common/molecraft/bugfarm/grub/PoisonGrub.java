package common.molecraft.bugfarm.grub;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;

public class PoisonGrub extends Grub {
	
	public PoisonGrub()
	{
		super(Grub.GrubType.GRUB_POISON);
		
		this.setPotionEffect(Potion.poison.id, 10, 1, 0.6f);
		//Brew for Poison Potion
		this.setPotionEffect(PotionHelper.spiderEyeEffect);
	}
}
