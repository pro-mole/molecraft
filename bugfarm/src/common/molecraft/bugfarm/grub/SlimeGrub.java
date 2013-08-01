package common.molecraft.bugfarm.grub;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;

public class SlimeGrub extends Grub {
	
	public SlimeGrub()
	{
		super(Grub.GrubType.GRUB_SLIME);
		
		this.setPotionEffect(Potion.poison.id, 5, 1, 0.3f);
		this.setPotionEffect(PotionHelper.sugarEffect);
	}

}
