package br.unb.sma.MOVaccumCleaner;

import static org.junit.Assert.assertEquals;

public class EnvironmentAssertions {

	public static final Environment.State C = Environment.State.Clean;
	public static final Environment.State D = Environment.State.Dirty;
	
	public static void assertEnvironment(Environment.State[][] state, Environment env){
		for (int y = 0 ; y < state.length ; y ++){
			for (int x = 0; x < state[y].length; x++){
				assertEquals("Cell ("+x+","+y+")",state[y][x],env.get(x, y));
			}
		}
	}
}
