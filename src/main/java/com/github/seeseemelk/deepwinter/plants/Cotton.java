package com.github.seeseemelk.deepwinter.plants;

import com.github.seeseemelk.deepwinter.DeepWinter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.util.Random;

import static org.bukkit.Bukkit.getLogger;

public class Cotton implements Listener
{
	protected final DeepWinter plugin;
	private static final String COTTON = "Cotton" + ChatColor.DARK_AQUA;
	private static final String COTTONSEED = "Cotton Seed" + ChatColor.DARK_AQUA;
	private Random random = new Random();

	public Cotton(DeepWinter plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * This event is called upon when a block is being placed.
	 * There will be a check if the placed block is a Cotton Seed.
	 */
	@EventHandler
	public void placeCottonSeed(BlockPlaceEvent event)
	{
		Block placedBlock = event.getBlockPlaced();
		ItemMeta itemMeta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();

		if (isMaterialEqual(placedBlock, Material.WHEAT) && hasCorrectName(itemMeta, COTTONSEED))
		{
			changeSeedToPlant(placedBlock);
		}
	}

	/**
	 * The method will check if the block that is being broken is a dead bush,
	 * if so the {dropItemCotton(Block block)} will be executed.
	 * @param event happens when a block is broken
	 */
	@EventHandler
	public void breakCottonPlant(BlockBreakEvent event)
	{
		Block brokenBlock = event.getBlock();

		if (isMaterialEqual(brokenBlock, Material.DEAD_BUSH))
		{
			event.setDropItems(false);

			dropItemCotton(brokenBlock);
		}
	}

	/**
	 * It will change the given block to a cotton plant after 10-15 minutes
	 * @param block is the cotton seed that was planted
	 */
	private void changeSeedToPlant(Block block)
	{
		BukkitTask task = Bukkit.getScheduler().runTaskLater(plugin, () ->
		{
			String blockStringData = "minecraft:dead_bush";
			BlockData blockData = Bukkit.createBlockData(blockStringData);
			block.setType(Material.DEAD_BUSH);
			block.setBlockData(blockData);
		}, minutesToTicks(random.nextInt(5) + 10));
	}

	/**
	 * Checks the chance of an item being dropped.
	 * If the chance is lower/equal than 0.5, a piece of string with the name 'Cotton' will be dropped.
	 * If the chance is lower/equal than 0.7 a wheat seed with the name 'Cotton Seed' will be dropped.
	 * @param block is the block that got broken
	 */
	private void dropItemCotton(Block block)
	{
		if (Math.random() <= 0.5)
		{
			ItemStack cotton = new ItemStack(Material.STRING);
			ItemMeta cottonMeta = cotton.getItemMeta();
			cottonMeta.setDisplayName(ChatColor.RESET + COTTON);
			cotton.setItemMeta(cottonMeta);

			block.getWorld().dropItemNaturally(block.getLocation(), cotton);
		}

		if (Math.random() <= 0.7)
		{
			ItemStack cottonSeed = new ItemStack(Material.WHEAT_SEEDS);
			ItemMeta cottonSeedMeta = cottonSeed.getItemMeta();
			cottonSeedMeta.setDisplayName(ChatColor.RESET + COTTONSEED);
			cottonSeed.setItemMeta(cottonSeedMeta);

			block.getWorld().dropItemNaturally(block.getLocation(), cottonSeed);
		}
	}

	/**
	 * @param block is the block that you want to compare
	 * @param material is the material that the block has to equal
	 * @return {@code true} when the material equals the block
	 */
	private boolean isMaterialEqual(Block block, Material material)
	{
		return block.getType().equals(material);
	}

	/**
	 * @param item is the item of which the name is being checked
	 * @param name The name of the Item
	 * @return {@code true} when the item has a displayname that is the given name
	 */
	private boolean hasCorrectName(ItemMeta item, String name)
	{
		return (item.hasDisplayName() && item.getDisplayName().equals(name));
	}

	/**
	 * @param seconds
	 * @return the seconds converted to the amount of ticks
	 */
	private int secondsToTicks(int seconds)
	{
		return 20 * seconds;
	}

	/**
	 * @param minutes
	 * @return the minutes converted to the amount of ticks
	 */
	private int minutesToTicks(int minutes)
	{
		return secondsToTicks(minutes * 60);
	}
}
