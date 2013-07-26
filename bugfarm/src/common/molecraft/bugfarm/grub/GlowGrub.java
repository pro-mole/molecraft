package common.molecraft.bugfarm.grub;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;

public class GlowGrub extends Grub {

	public GlowGrub()
	{
		super(Grub.GrubType.GRUB_GLOW);
		
		this.setPotionEffect(Potion.nightVision.id, 30, 1, 0.25f);
		//Brew for Night Vision Potion
		this.setPotionEffect(PotionHelper.goldenCarrotEffect);
	}
}
