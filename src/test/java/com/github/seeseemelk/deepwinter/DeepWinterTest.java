package com.github.seeseemelk.deepwinter;

import org.junit.After;
import org.junit.Before;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;

public class DeepWinterTest
{
	@SuppressWarnings("unused")
	private ServerMock server;
	@SuppressWarnings("unused")
	private DeepWinter plugin;

	@Before
	public void setUp()
	{
		server = MockBukkit.mock();
		plugin = MockBukkit.load(DeepWinter.class);
	}
	
	@After
	public void tearDown()
	{
		MockBukkit.unload();
	}

}
