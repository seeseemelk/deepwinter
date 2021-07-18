package com.github.seeseemelk.deepwinter.plants;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Bukkit.getLogger;

public class Cotton implements Listener
{
	private static final String COTTON = ChatColor.RESET + "Cotton" + ChatColor.DARK_AQUA;

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
	 * Checks the chance of an item being dropped.
	 * If the chance is lower/equal than 0.5, string with the name 'Cotton'
	 * will be dropped where the block was broken.
	 * @param block is the block that got broken
	 */
	private void dropItemCotton(Block block)
	{
		if (Math.random() <= 0.5)
		{
			ItemStack cotton = new ItemStack(Material.STRING);
			ItemMeta cottonMeta = cotton.getItemMeta();
			cottonMeta.setDisplayName(COTTON);
			cotton.setItemMeta(cottonMeta);

			block.getWorld().dropItemNaturally(block.getLocation(), cotton);
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
}
