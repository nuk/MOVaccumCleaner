package br.unb.sma.MOVaccumCleaner;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.awt.Point;

import br.unb.sma.MOVaccumCleaner.Environment.State;

public class MO extends EnvironmentAgent{
	
	private static final Point UP		= new Point(0,-1); 
	private static final Point DOWN		= new Point(0,+1); 
	private static final Point LEFT		= new Point(-1,0); 
	private static final Point RIGHT	= new Point(+1,0); 
	private static final Point STOP	= new Point(0,0); 
	
	private static final long serialVersionUID = -5831718355718486260L;
	
	private Point direction = RIGHT;
	
	@Override //TODO: Check how to test this
	protected void setup() {
		Environment.getInstance().addAgent(this, 0, 0);
		addBehaviour(new TickerBehaviour(this, 500) {
			@Override protected void onTick() {	doIt();	}
		});
	}
	
	public void doIt() {
		Point here = env.agentPosition(this);
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
		env.agentPosition(this, position.x, position.y);
	}

}
