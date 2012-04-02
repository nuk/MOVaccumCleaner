package br.unb.sma.MOVaccumCleaner;

import java.awt.Point;

import br.unb.sma.MOVaccumCleaner.Environment.State;
import jade.core.Agent;

public class MO extends Agent{
	
	private static final Point UP		= new Point(0,-1); 
	private static final Point DOWN		= new Point(0,+1); 
	private static final Point LEFT		= new Point(-1,0); 
	private static final Point RIGHT	= new Point(+1,0); 
	
	private static final long serialVersionUID = -5831718355718486260L;
	
	private Environment env;
	
	private Point direction = RIGHT;
	
	public void setEnvironment(Environment env) {	this.env = env;	}

	public void doIt() {
		Point position = env.getPosition(this);
		if (env.get(position.x, position.y) == State.Clean){
			Point newPosition = new Point (position);
			newPosition.x += direction.x; 
			newPosition.y += direction.y; 
			
			if (direction.equals(RIGHT) && newPosition.x >= env.getCols()){
				env.setPosition(this, position.x, position.y+1);
				direction = LEFT;
			}else if (direction.equals(LEFT) && newPosition.x < 0){
				env.setPosition(this, position.x, position.y+1);
				direction = RIGHT;
			}else
				env.setPosition(this, newPosition.x, newPosition.y);
		}else
			this.env.set(position.x, position.y, State.Clean);
	}

}
