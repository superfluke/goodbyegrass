package fluke.goodbyegrass.config;

import fluke.goodbyegrass.GoodbyeGrass;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = GoodbyeGrass.MODID, category = "")
@Mod.EventBusSubscriber(modid = GoodbyeGrass.MODID)
public class Configs 
{
	public static GGConfig worldgen = new GGConfig();
	
	public static class GGConfig
	{
		@Config.Comment({"Remove grass during world gen", "Default: true"})
		public boolean removeGrass = true;
		
		@Config.Comment({"Percentage of grass to be removed if removeGrass=true", "Default: 100"})
		public int grassRemovalPercent = 100;
		
		@Config.Comment({"Remove flowers during world gen", "Default: false"})
		public boolean removeFlowers = false;
		
		@Config.Comment({"Percentage of flowers to be removed if removeFlowers=true", "Default: 100"})
		public int flowerRemovalPercent = 100;
		
		@Config.Comment({"List of biomes where removal will run. If empty, removal will run in all biomes", "Note: When two biomes touch, the grass/flowers from one biome can leak into the touching biome", "Example: minecraft:birch_forest",  "Default: "})
		public String[] removalBiomes = {};
	}
	
	@SubscribeEvent
	public static void onConfigReload(ConfigChangedEvent.OnConfigChangedEvent event) 
	{
		if (GoodbyeGrass.MODID.equals(event.getModID()))
		{
			ConfigManager.sync(GoodbyeGrass.MODID, Config.Type.INSTANCE);
			GoodbyeGrass.proxy.buildBiomeWhitelst();
		}
	}
}
