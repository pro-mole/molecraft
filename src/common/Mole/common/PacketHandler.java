package Mole.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import Mole.common.seedstone.SeedstonePillar;
import Mole.common.seedstone.TileSeedstone;
import Mole.common.terrarium.TileTerrarium;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {
	
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		
		System.out.println("Incoming Packet!");
		if (packet.channel.equals("MoleSeedstones"))
		{
			System.out.println("Caught a package for the seedstones!");
			updateSeedstone(packet);
		}
		if (packet.channel.equals("MoleTerrarium"))
		{
			System.out.println("Caught a package for the terrariums!");
			updateTerrarium(packet);
		}
	}
	
	public static Packet250CustomPayload createTerrariumPackate(TileTerrarium TERR)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		try {
			System.out.println("["+TERR.xCoord+","+TERR.yCoord+","+TERR.zCoord+"]");
			dos.writeInt(TERR.xCoord);
			dos.writeInt(TERR.yCoord);
			dos.writeInt(TERR.zCoord);
			
			if (TERR.bug != null)
			{
				dos.writeInt(TERR.bug.itemID);
				dos.writeInt(TERR.bug.getItemDamage());
			}
			else
			{
				dos.writeInt(0);
				dos.writeInt(0);
			}
			
			if (TERR.food != null)
			{
				dos.writeInt(TERR.food.itemID);
				dos.writeInt(TERR.food.stackSize);
			}
			else
			{
				dos.writeInt(0);
				dos.writeInt(0);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "MoleTerrarium";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		return packet;
	}
	
	public static Packet250CustomPayload createSeedstonePackage(int x, int y, int z, int[] data) throws IOException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		try {
			System.out.println("["+x+","+y+","+z+"]");
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			
			for (int item: data)
			{
				dos.writeInt(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "MoleSeedstones";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		return packet;
	}
	
	public void updateTerrarium(Packet250CustomPayload packet)
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
		DataInputStream packetStream = new DataInputStream(bis);
		
		int x,y,z;
		
		try {
			//int stuff = packetStream.readInt();
			x = packetStream.readInt();
			y = packetStream.readInt();
			z = packetStream.readInt();
			System.out.println("["+x+","+y+","+z+"]");
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		TileEntity TE = ModLoader.getMinecraftInstance().theWorld.getBlockTileEntity(x, y, z);
		
		if (TE == null) return;
		
		TileTerrarium terrarium = (TileTerrarium) TE;
		int bugID=0, bugDmg=0, foodID=0, foodSize=0;
		try {
			bugID = packetStream.readInt();
			bugDmg = packetStream.readInt();
			foodID = packetStream.readInt();
			foodSize = packetStream.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (bugID > 0)
		{
			if (terrarium.bug == null) terrarium.bug = new ItemStack(bugID,1,bugDmg);
			else
			{
				terrarium.bug.itemID = bugID;
				terrarium.bug.setItemDamage(bugDmg);
			}
		}
		else
		{
			terrarium.bug = null;
		}
		
		if (foodID > 0)
		{
			if (terrarium.food == null) terrarium.food = new ItemStack(foodID,foodSize,0);
			else
			{
				terrarium.food.itemID = foodID;
				terrarium.food.stackSize = foodSize;
			}
		}
		else
		{
			terrarium.food = null;
		}
	}
	
	public static void updateSeedstone(Packet250CustomPayload packet)
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
		DataInputStream packetStream = new DataInputStream(bis);
		
		int x,y,z;
		
		try {
			//int stuff = packetStream.readInt();
			x = packetStream.readInt();
			y = packetStream.readInt();
			z = packetStream.readInt();
			System.out.println("["+x+","+y+","+z+"]");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		TileEntity TE = ModLoader.getMinecraftInstance().theWorld.getBlockTileEntity(x, y, z);
		
		if (TE != null) return;
		
		int tier, block, type;
		try {
			tier = packetStream.readInt();
			block = packetStream.readInt();
			type = packetStream.readInt();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		if (tier == TileSeedstone.Type.PILLAR.ordinal())
		{
			SeedstonePillar pillar = new SeedstonePillar(block, tier);
			ModLoader.getMinecraftInstance().theWorld.setBlockTileEntity(x, y, z, pillar);
		}
	}
}
