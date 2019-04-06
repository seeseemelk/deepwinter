package com.github.seeseemelk.deepwinter.mob;

import org.bukkit.Bukkit;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import com.github.seeseemelk.deepwinter.Controller;
import com.github.seeseemelk.deepwinter.DeepWinter;

public class MobController extends Controller implements Listener
{	
	public MobController(DeepWinter plugin)
	{
		super(plugin);
	}
	
	public void enable()
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event)
	{
		if (event.getEntity() instanceof Monster)
			event.setCancelled(true);
	}
}
