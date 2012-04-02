package br.unb.sma.MOVaccumCleaner;

import jade.core.Agent;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class Environment {

	public enum State {Dirty,Clean};

	private State[][] state; 
	
	private Map<MO, Point> position;
	
	public Environment(int cols, int rows) {
		position = new HashMap<MO, Point>();
		state = new State[rows][cols];
		for (int i =0 ; i < rows; i ++){
			for(int j = 0 ; j < cols; j++){
				state[i][j] = State.Clean;
			}
		}
	}

	public void set(int col, int row, State state) {	this.state[row][col] = state;	}

	public State get(int col, int row) {	return state[row][col];	}

	public void addAgent(MO mo, int col, int row) {
		mo.setEnvironment(this);
		Point p = position.get(mo); 
		if (p == null)	{
			p = new Point(col, row);
			position.put(mo, p);
		}
		else p.setLocation(col, row);
	}

	public void setPosition(MO mo, int x, int y) {	position.get(mo).setLocation(x, y);	}

	public Point getPosition(MO mo) {
		return position.get(mo); //FIXME: This should be unmodifiable
	}

	public int getRows(){	return state.length;	}
	public int getCols(){	return state[0].length;	}
}
