package br.unb.sma.MOVaccumCleaner;

import java.awt.Point;

import jade.core.Agent;

public class EnvironmentAgent extends Agent {
	private static final long serialVersionUID = 2755943174016248773L;
	
	protected static final Point UP		= new Point(0,-1); 
	protected static final Point DOWN		= new Point(0,+1); 
	protected static final Point LEFT		= new Point(-1,0); 
	protected static final Point RIGHT	= new Point(+1,0); 
	protected static final Point STOP	= new Point(0,0); 
	
	protected Environment env;
	
	public void setEnvironment(Environment env) {	this.env = env;	}
	
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
