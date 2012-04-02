package br.unb.sma.MOVaccumCleaner;

import java.awt.Point;

import br.unb.sma.MOVaccumCleaner.Environment.State;
import jade.core.Agent;

public class MO extends Agent{
	private static final long serialVersionUID = -5831718355718486260L;
	
	private Environment env;
	
	public void setEnvironment(Environment env) {	this.env = env;	}

	public void doIt() {
		Point position = env.getPosition(this);
		this.env.set(position.x, position.y, State.Clean);
	}

}
