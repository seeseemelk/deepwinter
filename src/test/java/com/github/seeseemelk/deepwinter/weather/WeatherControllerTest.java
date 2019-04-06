package com.github.seeseemelk.deepwinter.weather;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.seeseemelk.deepwinter.DeepWinter;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;

public class WeatherControllerTest
{
	private ServerMock server;
	@SuppressWarnings("unused")
	private DeepWinter plugin;

	@Before
	public void setUp()
	{
		server = MockBukkit.mock();
		server.addSimpleWorld("world");
		plugin = MockBukkit.load(DeepWinter.class);
	}
	
	@After
	public void tearDown()
	{
		MockBukkit.unload();
	}
	
	@Test
	public void testPluginStartSetsGameRule()
	{
		assumeThat(server.getWorlds().size(), greaterThan(0));
		for (World world : server.getWorlds())
		{
			assertThat("doWeatherCycle should be disabled", world.getGameRuleValue(GameRule.DO_WEATHER_CYCLE), is(false));
		}
	}
	
	@Test
	public void testPluginStartSetsTheWeather()
	{
		assumeThat(server.getWorlds().size(), greaterThan(0));
		for (World world : server.getWorlds())
		{
			assertThat(world.getWeatherDuration(), greaterThan(0));
		}
	}
	
	@Test
	public void testPluginStartIsStorming()
	{
		assumeThat(server.getWorlds().size(), greaterThan(0));
		for (World world : server.getWorlds())
		{
			assertThat(world.hasStorm(), is(true));
		}
	}
	
	@Test
	public void testPluginStartIsThundering()
	{
		assumeThat(server.getWorlds().size(), greaterThan(0));
		for (World world : server.getWorlds())
		{
			assertThat(world.isThundering(), is(true));
		}
	}
}
