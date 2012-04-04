package br.unb.sma.MOVaccumCleaner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class _EnvironmentTest {

	private Environment.State C = Environment.State.Clean;
	private Environment.State D = Environment.State.Dirty;
	
	@Test public void everythingStartsClean(){
		Environment env = new Environment(3,3);

		assertEnvironment(new Environment.State[][]{
				{C,C,C},
				{C,C,C},
				{C,C,C}
		},env);
	}
	
	@Test public void allowsToSetDirtySpots(){
		Environment env = new Environment(3,3);

		env.set(1,0, Environment.State.Dirty);
		env.set(0,2, Environment.State.Dirty);
		env.set(2,2, Environment.State.Dirty);
		
		assertEnvironment(new Environment.State[][]{
				{C,D,C},
				{C,C,C},
				{D,C,D}
		},env);
	}
	
	@Test public void printsTheRepresentation(){
		Environment env = new Environment(3,3);
		assertEquals(". . . \n. . . \n. . . \n",env.toString());
		
		env.set(1,0, Environment.State.Dirty);
		env.set(0,2, Environment.State.Dirty);
		env.set(2,2, Environment.State.Dirty);
		assertEquals(". * . \n. . . \n* . * \n",env.toString());
		
		env.addAgent(new MO(), 1, 1);
		assertEquals(". * . \n. a . \n* . * \n",env.toString());
		
		env.agentPosition(1, 0);
		assertEquals(". â . \n. . . \n* . * \n",env.toString());
	}
	
	private void assertEnvironment(Environment.State[][] state, Environment env){
		for (int y = 0 ; y < state.length ; y ++){
			for (int x = 0; x < state[y].length; x++){
				String msg= "Cell ("+x+","+y+") Expected:<"+state[y][x]
							+"> but was: <"+env.get(x, y)+">";
				assertEquals(msg,state[y][x],env.get(x, y));
			}
		}
	}
}
