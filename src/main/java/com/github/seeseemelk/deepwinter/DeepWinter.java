package com.github.seeseemelk.deepwinter;

import java.io.File;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import com.github.seeseemelk.deepwinter.mob.MobController;
import com.github.seeseemelk.deepwinter.temperature.TemperatureController;
import com.github.seeseemelk.deepwinter.weather.WeatherController;

public class DeepWinter extends JavaPlugin
{
	private WeatherController weatherController;
	private MobController mobController;
	private TemperatureController temperatureController;
	
	public DeepWinter()
	{
		super();
	}
	
	protected DeepWinter(final JavaPluginLoader loader, final PluginDescriptionFile description, final File dataFolder, final File file)
	{
		super(loader, description, dataFolder, file);
	}
	
	@Override
	public void onEnable()
	{
		weatherController = new WeatherController(this);
		mobController = new MobController(this);
		temperatureController = new TemperatureController(this);
		
		weatherController.enable();
		mobController.enable();
		temperatureController.enable();
	}
	
	@Override
	public void onDisable()
	{
		temperatureController.disable();
	}
	
	public MobController getMobController()
	{
		return mobController;
	}
	
	public WeatherController getWeatherController()
	{
		return weatherController;
	}
	
	public TemperatureController getTemperatureController()
	{
		return temperatureController;
	}
}
































