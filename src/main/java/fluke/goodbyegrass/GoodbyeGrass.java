package fluke.goodbyegrass;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.Logger;

import fluke.goodbyegrass.proxy.CommonProxy;


@Mod(modid = GoodbyeGrass.MODID, name = GoodbyeGrass.NAME, version = GoodbyeGrass.VERSION, acceptableRemoteVersions="*")
public class GoodbyeGrass 
{

	public static final String MODID = "goodbyegrass";
	public static final String NAME = "Goodbye Grass";
	public static final String VERSION = "1.1.0";


	@Instance(MODID)
	public static GoodbyeGrass instance;

	@SidedProxy(clientSide = "fluke.goodbyegrass.proxy.ClientProxy", serverSide = "fluke.goodbyegrass.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		logger = event.getModLog();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit();
	}
}

