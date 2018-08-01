package fluke.goodbyegrass.proxy;

import java.util.ArrayList;

import fluke.goodbyegrass.GoodbyeGrass;
import fluke.goodbyegrass.config.Configs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy 
{
	public ArrayList<Biome> biomeWhitelist = new ArrayList<Biome>();
	
	public void init()
	{
		MinecraftForge.TERRAIN_GEN_BUS.register(this);
	}
	
	public void postInit()
	{
		buildBiomeWhitelst();
	}
	
	@SubscribeEvent
	public void decorate(DecorateBiomeEvent.Decorate event) 
	{
		if(event.getType() == Decorate.EventType.GRASS)
		{
			if(Configs.worldgen.removeGrass)
			{
				if(biomeWhitelist == null)
				{
					event.setResult(Result.DENY);
				}
				else
				{
					Biome currentBiome = event.getWorld().getBiome(event.getPos());
					for(Biome whitelistedBiome: biomeWhitelist)
					{
						if(currentBiome == whitelistedBiome)
						{
							event.setResult(Result.DENY);
							return;
						}
					}
				}
			};
		}
		else if(event.getType() == Decorate.EventType.FLOWERS)
		{
			if(Configs.worldgen.removeFlowers)
			{
				if(biomeWhitelist == null)
				{
					event.setResult(Result.DENY);
				}
				else
				{
					Biome currentBiome = event.getWorld().getBiome(event.getPos());
					for(Biome whitelistedBiome: biomeWhitelist)
					{
						if(currentBiome == whitelistedBiome)
						{
							event.setResult(Result.DENY);
							return;
						}
					}
				}
			}
				
		}
	}
	
	public void buildBiomeWhitelst()
	{
		int biomesAdded = 0;
		Biome biomeToAdd;
		
		for(String biomeString: Configs.worldgen.removalBiomes)
		{
			biomeToAdd = Biome.REGISTRY.getObject(new ResourceLocation(biomeString));
			
			//try once more assuming they forgot 'minecraft:' in front of the biome name
			if(biomeToAdd == null)
				biomeToAdd = Biome.REGISTRY.getObject(new ResourceLocation("minecraft:"+biomeString));
			
			if(biomeToAdd == null)
			{
				GoodbyeGrass.logger.warn("Cannot find biome " + biomeString);
			}
			else
			{
				biomeWhitelist.add(biomeToAdd);
				biomesAdded++;
			}
		}
		
		if(biomesAdded == 0)
			biomeWhitelist = null;
	}
}
