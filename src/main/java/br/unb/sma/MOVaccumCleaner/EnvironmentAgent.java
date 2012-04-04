package br.unb.sma.MOVaccumCleaner;

import jade.core.Agent;

public class EnvironmentAgent extends Agent {
	private static final long serialVersionUID = 2755943174016248773L;
	
	protected Environment env;
	
	public void setEnvironment(Environment env) {	this.env = env;	}
	
}
