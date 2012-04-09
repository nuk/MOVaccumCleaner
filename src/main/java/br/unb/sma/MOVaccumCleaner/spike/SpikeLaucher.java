package br.unb.sma.MOVaccumCleaner.spike;

import br.unb.sma.MOVaccumCleaner.MO;
import br.unb.sma.MOVaccumCleaner.WallE;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class SpikeLaucher {

	public static void main(String[] args) throws StaleProxyException {
		startJADE();
	}

	private static void startJADE() throws StaleProxyException {
		ProfileImpl config = new ProfileImpl();
		
		config.setParameter(Profile.LOCAL_HOST, "127.0.0.1");
		config.setParameter(Profile.GUI, "true");

		AgentContainer main = Runtime.instance().createMainContainer(config);
		startAgents(main);
	}

	private static void startAgents(AgentContainer main)
			throws StaleProxyException {
		main.createNewAgent("MO", MO.class.getName(), null).start();
		main.createNewAgent("Auto", Auto.class.getName(), null).start();
		main.createNewAgent("WallE", WallE.class.getName(), null).start();
	}
	
	
}

