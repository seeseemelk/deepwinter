package com.github.seeseemelk.deepwinter;

import java.util.logging.Logger;

public abstract class Controller
{
	protected DeepWinter plugin;
	protected Logger logger;

	public Controller(DeepWinter plugin)
	{
		this.plugin = plugin;
		logger = plugin.getLogger(); 
	}
	
	public void enable()
	{
		
	}
	
	public void disable()
	{
		
	}

}
