package com.github.seeseemelk.deepwinter.temperature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.seeseemelk.deepwinter.Controller;
import com.github.seeseemelk.deepwinter.DeepWinter;
import com.github.seeseemelk.deepwinter.MathUtil;

public class TemperatureController extends Controller
{
	public static final double MIN_SURVIVABLE_TEMP = 35.0;
	public static final double MAX_SURVIVABLE_TEMP = 40.5;
	 
	private final Map<Player, Double> playerTemperatures = new HashMap<>();
	private final Set<HeatSource> heatSources = new HashSet<>();
	private final TemperatureUI ui;
	private final TemperatureEffects effects;
	
	public TemperatureController(DeepWinter plugin)
	{
		super(plugin);
		ui = new TemperatureUI(plugin);
		effects = new TemperatureEffects(plugin);
	}
	
	@Override
	public void enable()
	{
		heatSources.add(new HeatSource(Bukkit.getWorlds().get(0).getBlockAt(-60, 72, -54).getLocation(), 40));
		
		Bukkit.getScheduler().runTaskTimer(plugin, this::updateAllPlayerTemperatures, 1, 1);
		effects.enable();
	}
	
	@Override
	public void disable()
	{
		ui.disable();
	}
	
	public TemperatureUI getUI()
	{
		return ui;
	}
	
	/**
	 * Gets the temperature at a given location.
	 * @param location The location to get the temperature at.
	 * @return The temperature at the location.
	 */
	public double getTemperatureAt(Location location)
	{
		double height = location.getY() / location.getWorld().getMaxHeight();
		if (isInCave(location))
			return MathUtil.linearInterpolation(-40, -20, Math.pow(height, 0.2));
		else
			return MathUtil.linearInterpolation(-20, -10, 1 - height);
	}
	
	/**
	 * Gets the ambient temperature of a player.
	 * That is, the temperature at the location the player is right now.
	 * @param player The ambient temperature of a player.
	 * @return The temperature of the location of the player.
	 */
	public double getAmbientTemperatureOf(Player player)
	{
		return getTemperatureAt(player.getLocation()) + getHeatFromHeatSources(player.getLocation());
	}
	
	/**
	 * Get the amount of heat at a location from all heat sources.
	 * @param location The location to get the heat from.
	 * @return The amount of heat at the location.
	 */
	public double getHeatFromHeatSources(Location location)
	{
		return heatSources.stream()
			.mapToDouble(heatSource -> heatSource.getHeatLevelAt(location))
			.reduce(0, (a, b) -> a + b);
	}
	
	/**
	 * Gets the temperature of a player.
	 * @param player The player of whom to get the temperature of.
	 * @return The temperature of the player.
	 */
	public double getTemperature(Player player)
	{
		return playerTemperatures.getOrDefault(player, 36.5);
	}

	/**
	 * Checks if a given location is considered to be inside of a cave.
	 * @param location The location to check.
	 * @return {@code true} if the location is inside of a cave, {@code false} if the location is at the surface.
	 */
	public boolean isInCave(Location location)
	{
		return location.getBlock().getLightFromSky() <= 4;
	}
	
	private void updatePlayerTemperature(Player player)
	{
		double currentTemperature = getTemperature(player);
		double ambientTemperature = getAmbientTemperatureOf(player);
		double newTemperature = MathUtil.linearInterpolation(currentTemperature, ambientTemperature, 0.00005);
		playerTemperatures.put(player, newTemperature);
		player.sendMessage("Temp: " + newTemperature + "C");
	}
	
	private void updateAllPlayerTemperatures()
	{
		Bukkit.getOnlinePlayers().stream()
				.forEach(this::updatePlayerTemperature);
		ui.updateHUDs();
	}

}



























