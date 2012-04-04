package br.unb.sma.MOVaccumCleaner;

import java.awt.Point;

import br.unb.sma.MOVaccumCleaner.Environment.State;
import jade.core.Agent;

public class MO extends Agent{
	
	private static final Point UP		= new Point(0,-1); 
	private static final Point DOWN		= new Point(0,+1); 
	private static final Point LEFT		= new Point(-1,0); 
	private static final Point RIGHT	= new Point(+1,0); 
	private static final Point STOP	= new Point(0,0); 
	
	private static final long serialVersionUID = -5831718355718486260L;
	
	private Environment env;
	
	private Point direction = RIGHT;
	
	public void setEnvironment(Environment env) {	this.env = env;	}

	public void doIt() {
		Point here = env.getPosition(this);
		Point to = this.direction;
		if (env.get(here.x, here.y) == State.Clean){
			
			if (inLastCell(here)){
				to=STOP;
			}else if (to.equals(RIGHT) && inLastColumn(here)){
				to=DOWN;
				this.direction = LEFT;
			}else if (to.equals(LEFT) && inFirstColumn(here)){
				to=DOWN;
				this.direction = RIGHT;
			}
			
			move(here, to);
		}else
			this.env.set(here.x, here.y, State.Clean);
	}

	private boolean inFirstColumn(Point here) {	return here.x == 0;	}

	private boolean inLastColumn(Point here) {	return here.x == env.getCols()-1;	}

	private boolean inLastCell(Point here) {
		return inLastColumn(here) && here.y == env.getRows()-1;
	}

	private void move(Point position, Point direction) {
		position.x += direction.x; 
		position.y += direction.y; 
		env.setPosition(this, position.x, position.y);
	}

}
