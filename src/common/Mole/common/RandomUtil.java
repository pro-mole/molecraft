package Mole.common;

import java.util.Random;

class RandomUtil 
{
	public static class ODD
	{
		int oddVal;
		Object result;
		
		public ODD(int o, Object O)
		{
			oddVal = o;
			result = O;
		}
		
		public ODD(Object O)
		{
			if (O instanceof Object[])
			{
				Object[] _O = (Object[]) O;
				switch(_O.length)
				{
					case 0:
						oddVal = 0;
						result = null;
						break;
					case 1:
						oddVal = 1;
						result = _O[0];
						break;
					default:
						if (_O[0] instanceof Integer)
						{
							oddVal = (Integer) _O[0];
							result = _O[1];
						}
						else
						{
							oddVal = 1;
							result = _O;
						}
						break;
				}
			}
			else
			{
				oddVal = 1;
				result = O;
			}
		}
		
		public int getOdds() { return oddVal; }
		public Object get() { return result; }
	}
	
	public static Object randomize(ODD[] odds, Random rand)
	{
		int totalOdds = 0;
		for (ODD o: odds)
		{
			totalOdds += o.getOdds();
		}
		
		int baseChance = rand.nextInt(totalOdds);
		for (ODD o: odds)
		{
			if (baseChance < o.getOdds()) return o.get();
			baseChance -= o.getOdds();
		}
		
		return null;
	}
	
	public static Object randomize(Object[] odds, Random rand)
	{
		ODD[] O = new ODD[odds.length];
		for (int i=0; i < odds.length; i++)
		{
			O[i] = new ODD(odds[i]);
		}
		
		return randomize(O, rand);
	}
}