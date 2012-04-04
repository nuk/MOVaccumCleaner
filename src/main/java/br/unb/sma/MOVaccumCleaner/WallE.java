package br.unb.sma.MOVaccumCleaner;

import java.awt.Point;

import br.unb.sma.MOVaccumCleaner.Environment.State;


public class WallE extends EnvironmentAgent {

	public void doIt() {
		Point p = env.agentPosition(this);
		env.set(p.x, p.y, State.Dirty);
	}

}
