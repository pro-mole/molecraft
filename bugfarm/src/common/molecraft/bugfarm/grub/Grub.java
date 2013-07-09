package common.molecraft.bugfarm.grub;

//Abstract class that implements the Grub items
public abstract class Grub extends ItemFood {
	public static enum GrubType {GRUB_WHITE, GRUB_RED, GRUB_FAT, GRUB_TINY, GRUB_WATER, GRUB_DIRT, GRUB_POISON, GRUB_SILK, GRUB_GLOW, GRUB_TRAP, GRUB_SLIME, GRUB_HELL, GRUB_SHINY, GRUB_GOLD, GRUB_MYSTIC};
	
	public static String[] names = {"White", "Red", "Fat", "Tiny", "Water", "Dirt", "Spiny", "Silky", "Flash", "Trap", "Hell", "Shiny", "Gold", "Mystic"},
		localNames = {"white", "red", "fat", "tiny", "water", "dirt", "poison", "silk", "glow", "trap", "hell", "shiny", "gold", "mystic"};
	
	public Grub(GrubType type)
	{
		super(ITEM_GRUB + type.ordinal());
		
		this.setMaxStackSize(8);
		
		this.setUnlocalizedName("grub_"+localNames[type.ordinal()]);
		LanguageRegistry.addName(this, names[type.ordinal()]+" Grub");
		this.setCreativeTab(CreativeTabs.tabFood);
	}
}