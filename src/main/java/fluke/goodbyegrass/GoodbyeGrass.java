package fluke.goodbyegrass;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.DoublePlantFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TallGrassFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("goodbyegrass")
public class GoodbyeGrass
{
    private static final Logger LOGGER = LogManager.getLogger();

    public GoodbyeGrass() 
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, fluke.goodbyegrass.init.ModConfig.SPEC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO HUMANS. GOODBYE GRASS");
        ForgeRegistries.BIOMES.forEach(biome->{
        	List<CompositeFeature<?, ?>> compFeatures = biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
    		for(int n=compFeatures.size()-1; n>=0; n--)
    		{
    			@SuppressWarnings("rawtypes")
				Feature feature = compFeatures.get(n).getFeature();
    			if(feature instanceof TallGrassFeature || feature instanceof DoublePlantFeature)
    			{
    				//LOGGER.info("Removing " + feature.toString() + " from " + biome.getDisplayName());
    				biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).remove(n);
    			}
    		}
        });
    }

}
