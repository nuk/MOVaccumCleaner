package br.unb.sma.MOVaccumCleaner.spike;

import br.unb.sma.MOVaccumCleaner.Environment;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Viewer extends Agent {
	private static final long serialVersionUID = 4608004591243343623L;
	
	private Environment env;
	
	protected void setup() {
		env = Environment.getInstance();
		addBehaviour(new TickerBehaviour(this, 200) {
			@Override protected void onTick() {	
				System.out.println("-----------------------------");
				System.out.println(env.toString());
				System.out.println("-----------------------------");
			}
		});
	}
}
