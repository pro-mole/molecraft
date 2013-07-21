package common.molecraft;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Randomizer {
	
	static Random rand;
	
	public static Object choose(HashMap<Object, Float> odds)
	{
		float total = 0;
		for (float val: odds.values())
		{
			total += val;
		}
		
		float odd = rand.nextFloat() * total;
		
		Iterator<Object> K = odds.keySet().iterator();
		while(K != null)
		{
			if (odd < odds.get(K))
			{
				return odd;
			}
			
			odd -= odds.get(K);
			K.next();
		}
		
		return null;
	}
	
	public static void seed(long S)
	{
		rand.setSeed(S);
	}
}
