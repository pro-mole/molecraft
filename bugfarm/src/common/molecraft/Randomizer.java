package common.molecraft;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Randomizer {
	
	static Random rand = new Random();
	
	
	public static <T> T choose(HashMap<T, Float> odds)
	{
		float total = 0;
		for (float val: odds.values())
		{
			total += val;
		}
		
		float odd = rand.nextFloat() * total;
		
		Iterator<T> K = odds.keySet().iterator();
		while(K.hasNext())
		{
			T k = K.next();
			if (odd < odds.get(k))
			{
				return k;
			}
			
			odd -= odds.get(k);
		}
		
		return null;
	}
	
	public static void seed(long S)
	{
		rand.setSeed(S);
	}
}
