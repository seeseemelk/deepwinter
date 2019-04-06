package com.github.seeseemelk.deepwinter.temperature;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import com.github.seeseemelk.deepwinter.Controller;
import com.github.seeseemelk.deepwinter.DeepWinter;
import com.github.seeseemelk.deepwinter.MathUtil;

public class TemperatureUI extends Controller
{
	private Map<Player, BossBar> bars = new HashMap<>();

	public TemperatureUI(DeepWinter plugin)
	{
		super(plugin);
	}
	
	@Override
	public void enable()
	{
		Bukkit.getOnlinePlayers().stream()
				.forEach(this::showHUD);
	}
	
	@Override
	public void disable()
	{
		for (BossBar bar : bars.values())
		{
			bar.removeAll();
		}
	}
	
	public BossBar getBar(Player player)
	{
		return bars.get(player);
	}
	
	private BossBar showHUD(Player player)
	{
		BossBar bar = Bukkit.createBossBar("Temperature (°C)",
				BarColor.RED,
				BarStyle.SOLID);
		bar.addPlayer(player);
		bars.put(player, bar);
		return bar;
	}
	
	public static double getTemperatureBarProgression(double temperature)
	{
		return MathUtil.clamp(0.0, 1.0,
				(temperature - TemperatureController.MIN_SURVIVABLE_TEMP) / (TemperatureController.MAX_SURVIVABLE_TEMP - TemperatureController.MIN_SURVIVABLE_TEMP));
	}
	
	public double getTemperatureBarProgression(Player player)
	{
		return getTemperatureBarProgression(plugin.getTemperatureController().getTemperature(player));
	}
	
	private void updateHUD(Player player)
	{
		BossBar bar = bars.get(player);
		if (bar == null)
			bar = showHUD(player);
		bar.setVisible(true);
		bar.setProgress(getTemperatureBarProgression(player));
	}
	
	public void updateHUDs()
	{
		Bukkit.getOnlinePlayers().stream()
				.forEach(this::updateHUD);
	}

}
