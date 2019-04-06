package com.github.seeseemelk.deepwinter.temperature;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import com.github.seeseemelk.deepwinter.Controller;
import com.github.seeseemelk.deepwinter.DeepWinter;
import com.github.seeseemelk.deepwinter.MathUtil;

public class TemperatureEffects extends Controller
{

	public TemperatureEffects(DeepWinter plugin)
	{
		super(plugin);
	}
	
	@Override
	public void enable()
	{
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::doAllEffects,
				0, MathUtil.secondsToTicks(2));
	}
	
	private void doDamage(Player player, double damage)
	{
		player.damage(damage);
		BossBar bar = plugin.getTemperatureController().getUI().getBar(player);
		bar.addFlag(BarFlag.CREATE_FOG);
		bar.addFlag(BarFlag.DARKEN_SKY);
	}
	
	private void doEffects(Player player)
	{
		double temperature = plugin.getTemperatureController().getTemperature(player);
		if (temperature <= TemperatureController.MIN_SURVIVABLE_TEMP)
			doDamage(player, (TemperatureController.MIN_SURVIVABLE_TEMP - temperature) / 2);
		else if (temperature >= TemperatureController.MAX_SURVIVABLE_TEMP)
			doDamage(player, (temperature - TemperatureController.MAX_SURVIVABLE_TEMP) * 2);
	}
	
	public void doAllEffects()
	{
		Bukkit.getOnlinePlayers().stream()
				.forEach(this::doEffects);
	}

}
