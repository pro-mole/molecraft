package common.molecraft.bugfarm.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TECocoon extends TileEntity {
	
	int variety;
	int metamorphosis;
	
	public TECocoon()
	{
		this.variety = 0;
		this.metamorphosis = 0;
	}
	
	public TECocoon(int type)
	{
		this.variety = type;
	}
	
	public int getCocoonTexture()
	{
		return this.variety; 
	}
	
	public void develop()
	{
		metamorphosis += this.worldObj.rand.nextInt(6)+5;
		
		if (metamorphosis >= 100)
		{
			System.out.println("Metarmorphosis complete!");
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		
		variety = tags.getInteger("CocoonType");
		metamorphosis = tags.getInteger("Metarmophosis");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tags) {
		tags.setInteger("CocoonType", variety);
		tags.setInteger("Metamorphosis", metamorphosis);
		
		super.writeToNBT(tags);
	}
}
