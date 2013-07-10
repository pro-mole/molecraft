package common.molecraft.bugfarm.grub;

import net.minecraft.potion.Potion;

public class HellGrub extends Grub {

	public HellGrub()
	{
		super(Grub.GrubType.GRUB_HELL);
		
		this.setPotionEffect(Potion.fireResistance.id, 20, 1, 0.25f);
	}
}
