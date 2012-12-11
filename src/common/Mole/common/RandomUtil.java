package Mole.common;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class RandomUtil 
{
	//Random distribution
	public static Object randomize(HashMap<Object, Integer> odds, Random rand)
	{
		int oddTotal = 0;
		for (int val: odds.values())
		{
			oddTotal += val;
		}
		
		int pick = rand.nextInt(oddTotal);
		for (Entry<Object,Integer> entry: odds.entrySet())
		{
			if (pick < entry.getValue())
				return entry.getKey();
			else
				pick -= entry.getValue();
		}
		
		//Theoretically impossible but...
		return null;
	}
	
	//Uniform distribution
	public static Object randomize(Object[] odds, Random rand)
	{
		int pick = rand.nextInt(odds.length);
		
		return odds[pick];
	}
}