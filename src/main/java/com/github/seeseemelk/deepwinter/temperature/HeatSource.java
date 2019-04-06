package com.github.seeseemelk.deepwinter.temperature;

import org.bukkit.Location;

public class HeatSource
{
	private final Location location;
	private final double heat;

	public HeatSource(Location location, double heat)
	{
		this.location = location;
		this.heat = heat;
	}

	public Location getLocation()
	{
		return location;
	}
	
	public double getHeat()
	{
		return heat;
	}
	
	public double getHeatLevelAt(Location otherLocation)
	{
		return heat / otherLocation.distanceSquared(location);
	}

}
