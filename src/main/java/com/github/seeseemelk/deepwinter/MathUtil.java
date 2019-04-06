package com.github.seeseemelk.deepwinter;

public final class MathUtil
{
	private MathUtil()
	{
		
	}
	
	public static double linearInterpolation(double from, double to, double position)
	{
		return from + (to - from) * position;
	}
	
	public static double clamp(double min, double max, double value)
	{
		if (value <= min)
			return min;
		else if (value >= max)
			return max;
		else
			return value;
	}
	
	public static long secondsToTicks(double seconds)
	{
		return Math.round(seconds * 20);
	}
	
	public static long minutesToTicks(double minutes)
	{
		return secondsToTicks(minutes * 60);
	}
	
	public static long hoursToTicks(double hours)
	{
		return minutesToTicks(hours * 60);
	}
	
	public static long daysToTicks(double days)
	{
		return hoursToTicks(days * 24);
	}
}
