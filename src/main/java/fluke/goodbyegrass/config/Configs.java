package fluke.goodbyegrass.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;
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

	}

}
