package br.unb.sma.MOVaccumCleaner;

import jade.core.Agent;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class Environment {

	public enum State {Dirty, Clean};

	private State[][] state; 
	
	private Map<EnvironmentAgent, Point> position;

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
		position = new HashMap<EnvironmentAgent, Point>();
		for (int i =0 ; i < rows; i ++){
			for(int j = 0 ; j < cols; j++){
				state[i][j] = State.Clean;
			}
		}
	}

	public void set(int col, int row, State state) {	this.state[row][col] = state;	}

	public State get(int col, int row) {	return state[row][col];	}

	public void addAgent(EnvironmentAgent a, int col, int row) {
		a.setEnvironment(this);
		Point p = position.get(a); 
		if (p == null)	{
			p = new Point(col, row);
			position.put(a,p);
		}
		else p.setLocation(col, row);
	}

	public void agentPosition(Agent a, int x, int y) {	position.get(a).setLocation(x, y);	}

	public Point agentPosition(Agent a) {
		return position.get(a);//FIXME: This should be unmodifiable
	}

	public int getRows(){	return state.length;	}
	public int getCols(){	return state[0].length;	}
	
	private EnvironmentAgent getAgent(int x, int y){
		Point p = new Point(x, y);
		if (position.containsValue(p)){
			for (EnvironmentAgent a: position.keySet())
				if (position.get(a).equals(p)) return a;
		}
		return null;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int y = 0 ; y < getRows() ; y ++){
			for (int x = 0; x < getCols(); x++){
				EnvironmentAgent a = getAgent(x, y);
				if(a != null){
					if (a instanceof MO){
						if (get(x,y) == State.Dirty)	b.append('ô');
						else	b.append('o');
					}else{
						if (get(x,y) == State.Dirty)	b.append('ŵ');
						else	b.append('w');
					}
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
