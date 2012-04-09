package br.unb.sma.MOVaccumCleaner;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public abstract class EnvironmentAgent extends Agent {
	private static final long serialVersionUID = 2755943174016248773L;
	
	protected static final Point UP		= new Point(0,-1); 
	protected static final Point DOWN		= new Point(0,+1); 
	protected static final Point LEFT		= new Point(-1,0); 
	protected static final Point RIGHT	= new Point(+1,0); 
	protected static final Point STOP	= new Point(0,0); 
	
	protected Environment env;
	
	public void setEnvironment(Environment env) {	this.env = env;	}
	
	private static Map<String, EnvironmentAgent> agents = new HashMap<String, EnvironmentAgent>();
	@Override //TODO: Check how to test this
	protected void setup() {
		agents.put(getName(), this);
		addBehaviour(new TickerBehaviour(this, 500) {
			@Override protected void onTick() {	doIt();	}
		});
	}
	
	public static EnvironmentAgent find(String name){	return agents.get(name);}
	
	protected abstract void doIt();
	
	protected void move(Point position, Point direction) {
		if (position.x + direction.x < env.getCols() &&
				position.x + direction.x >= 0)
			position.x += direction.x; 
		if (position.y + direction.y < env.getRows() &&
				position.y + direction.y >= 0)
			position.y += direction.y; 
		env.agentPosition(this, position.x, position.y);
	}
}
