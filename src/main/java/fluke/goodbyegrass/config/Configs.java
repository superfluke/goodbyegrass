package fluke.goodbyegrass.config;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

import fluke.goodbyegrass.GoodbyeGrass;

@Config(modid = GoodbyeGrass.MODID, category = "")
@Mod.EventBusSubscriber(modid = GoodbyeGrass.MODID)
public class Configs 
{
	public static GGConfig worldgen = new GGConfig();
	
	public static class GGConfig
	{
		@Config.Comment({"Remove grass during world gen", "Default: true"})
		@Config.RequiresWorldRestart
		public boolean removeGrass = true;
		
		@Config.Comment({"Remove flowers during world gen", "Default: false"})
		@Config.RequiresWorldRestart
		public boolean removeFlowers = false;
		
		@Config.Comment({"List of biomes where removal will run. If empty, removal will run in all biomes", "Note: When two biomes touch, the grass/flowers from one biome can leak into the touching biome", "Example: minecraft:birch_forest",  "Default: "})
		@Config.RequiresWorldRestart
		public String[] removalBiomes = {};
	}
}
