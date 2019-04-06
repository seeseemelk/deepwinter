package com.github.seeseemelk.deepwinter.mob;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.seeseemelk.deepwinter.DeepWinter;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.SimpleEntityMock;
import be.seeseemelk.mockbukkit.entity.SimpleMonsterMock;

public class MobControllerTest
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
	public void testEntitySpawnShouldNotBeCancelled()
	{
		Entity entity = new SimpleEntityMock(server);
		EntitySpawnEvent event = new EntitySpawnEvent(entity);
		server.getPluginManager().callEvent(event);
		assertFalse(event.isCancelled());
	}
	
	@Test
	public void testMonsterSpawnShouldBeCancelled()
	{
		Monster entity = new SimpleMonsterMock(server);
		EntitySpawnEvent event = new EntitySpawnEvent(entity);
		server.getPluginManager().callEvent(event);
		assertTrue(event.isCancelled());
	}
}
