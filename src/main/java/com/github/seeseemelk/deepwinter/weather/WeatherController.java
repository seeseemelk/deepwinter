package com.github.seeseemelk.deepwinter.weather;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;

import com.github.seeseemelk.deepwinter.Controller;
import com.github.seeseemelk.deepwinter.DeepWinter;

public class WeatherController extends Controller
{
	public WeatherController(DeepWinter plugin)
	{
		super(plugin);
	}

	public void enable()
	{
		Bukkit.getWorlds().forEach(world -> {
			world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
			world.setWeatherDuration(65535);
			world.setStorm(true);
			world.setThundering(true);
		});
	}
}
