package br.unb.sma.MOVaccumCleaner;

import java.awt.Point;

import br.unb.sma.MOVaccumCleaner.Environment.State;

public class MO extends EnvironmentAgent{
	
	private static final long serialVersionUID = -5831718355718486260L;
	
	private Point direction_x = RIGHT;
	private Point direction_y = DOWN;
	
	public void doIt() {
		if (env != null){
			Point here = env.agentPosition(this);
			Point to = this.direction_x;
			if (here != null){
				if (env.get(here.x, here.y) == State.Clean){
					go(here, to);
				}else
					this.env.set(here.x, here.y, State.Clean);
			}
		}
	}

	private void go(Point here, Point to) {
		if (to.equals(RIGHT) && inLastCell(here)){
			direction_y = UP;
			to = this.direction_x = LEFT;
		}else if (to.equals(LEFT) && inFirstCell(here)){
			direction_y = DOWN;
			to = this.direction_x = RIGHT;
		}else if (to.equals(RIGHT) && inLastColumn(here)){
			to=direction_y;
			this.direction_x = LEFT;
		}else if (to.equals(LEFT) && inFirstColumn(here)){
			to=direction_y;
			this.direction_x = RIGHT;
		}
		
		move(here, to);
	}

	private boolean inFirstColumn(Point here) {	return here.x == 0;	}

	private boolean inLastColumn(Point here) {	return here.x == env.getCols()-1;	}

	private boolean inLastCell(Point here) {
		return inLastColumn(here) && here.y == env.getRows()-1;
	}
	private boolean inFirstCell(Point here) {
		return inFirstColumn(here) && here.y == 0;
	}
}
