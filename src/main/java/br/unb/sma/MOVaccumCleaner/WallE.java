package br.unb.sma.MOVaccumCleaner;

import java.awt.Point;

import br.unb.sma.MOVaccumCleaner.Environment.State;


public class WallE extends EnvironmentAgent {
	private static final long serialVersionUID = 5806470060268210083L;

	public void doIt() {
		Point p = env.agentPosition(this);
		if (shouldMess())
			env.set(p.x, p.y, State.Dirty);
		else
			move(p,direction());
	}

	//Moves randomly
	protected Point direction() {
		switch ((int)(Math.random()*4)) {
			case 0: return UP;
			case 1: return DOWN;
			case 2: return LEFT;
			case 3: return RIGHT;
		}
		return null;
	}

	protected boolean shouldMess() {
		return Math.random()*100 > 90; // 10% chance of messing around
	}

}
