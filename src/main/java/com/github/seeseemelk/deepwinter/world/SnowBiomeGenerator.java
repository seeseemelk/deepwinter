package com.github.seeseemelk.deepwinter.world;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.type.Snow;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

/**
 * Generates a nice pure-snow world.
 */
public class SnowBiomeGenerator extends ChunkGenerator
{
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome)
	{
		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
		generator.setScale(0.005);
		ChunkData data = createChunkData(world);

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				int absoluteX = chunkX * 16 + x;
				int absoluteZ = chunkZ * 16 + z;
				double totalHeight = generator.noise(absoluteX, absoluteZ, 0.5, 0.5) * 15 + 50;
				int height = (int) totalHeight;

				double dSnowHeight = (totalHeight - Math.floor(totalHeight));

				int snowHeight = (int) ((Math.pow(dSnowHeight, 3)) * 8);

				if (snowHeight != 0)
				{
					Snow snow = (Snow) Bukkit.createBlockData(Material.SNOW);
					snow.setLayers(snowHeight);
					data.setBlock(x, height, z, snow);
				}
				data.setBlock(x, height - 1, z, Material.SNOW_BLOCK);
				data.setBlock(x, height - 2, z, Material.DIRT);
				for (int y = 1; y < height - 2; y++)
					data.setBlock(x, y, z, Material.STONE);
				data.setBlock(x, 0, z, Material.BEDROCK);
			}
		}

		return data;
	}
}
