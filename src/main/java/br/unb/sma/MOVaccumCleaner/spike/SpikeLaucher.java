package br.unb.sma.MOVaccumCleaner.spike;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import br.unb.sma.MOVaccumCleaner.Environment;
import br.unb.sma.MOVaccumCleaner.Environment.State;
import br.unb.sma.MOVaccumCleaner.EnvironmentAgent;
import br.unb.sma.MOVaccumCleaner.MO;
import br.unb.sma.MOVaccumCleaner.WallE;

public class SpikeLaucher {

	public static void main(String[] args) throws Exception {
		startJADE();
	}

	private static void startJADE() throws Exception {
		ProfileImpl config = new ProfileImpl();
		
		config.setParameter(Profile.LOCAL_HOST, "127.0.0.1");
		config.setParameter(Profile.GUI, "true");

		AgentContainer main = Runtime.instance().createMainContainer(config);
		startAgents(main,createEnvironment());
	}

	private static Environment createEnvironment(){
		Environment instance = new Environment(10,5);
		for (int i =0 ; i < 5; i ++){
			for(int j = 0 ; j < 10; j++){
				if (Math.random()*100 > 90) instance.set(j, i, State.Dirty);
			}
		}
		Environment.instance(instance);
		return instance;
	}
	
	private static void startAgents(AgentContainer main, Environment env) throws Exception {
		
		startAgent(main, env, "MO", MO.class, 0, 0);
		startAgent(main, env, "WallE", WallE.class, 9,4);
		main.createNewAgent("Auto", Auto.class.getName(), null).start();
	}

	private static void startAgent(AgentContainer main, Environment env, String tag, Class clazz, int x, int y) throws Exception {
		AgentController ctl = main.createNewAgent(tag, clazz.getName(), null);
		ctl.start();
		Thread.sleep(500);
		env.addAgent(EnvironmentAgent.find(ctl.getName()),x,y);
	}
	
	
}

