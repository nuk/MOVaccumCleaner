package br.unb.sma.MOVaccumCleaner;

import java.awt.Point;


public class Environment {

	public enum State {Dirty, Clean};

	private State[][] state; 
	
	private Point position;

	//TODO: Check if needs to be injected or go to a real factory
	private static Environment instance;
	public static Environment getInstance(){
		if (instance == null) {
			instance = new Environment(10,5);
			for (int i =0 ; i < 5; i ++){
				for(int j = 0 ; j < 10; j++){
					if (Math.random()*100 > 90) instance.set(j, i, State.Dirty);
				}
			}
		}
		return instance;
	}
	
	public Environment(int cols, int rows) {
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
		Point p = position;//.get(mo); 
		if (p == null)	{
			p = new Point(col, row);
			position = p;
		}
		else p.setLocation(col, row);
	}

	public void agentPosition(int x, int y) {	position.setLocation(x, y);	}

	public Point agentPosition() {
		return position;//FIXME: This should be unmodifiable
	}

	public int getRows(){	return state.length;	}
	public int getCols(){	return state[0].length;	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int y = 0 ; y < getRows() ; y ++){
			for (int x = 0; x < getCols(); x++){
				if(new Point(x,y).equals(agentPosition())){
					if (get(x,y) == State.Dirty)
						b.append('â');
					else
						b.append('a');
				}
				else{
					b.append(get(x,y) == State.Dirty?'*':'.');
				}
				b.append(' ');
			}
			b.append("\n");
		}
		return b.toString();
	};
}
