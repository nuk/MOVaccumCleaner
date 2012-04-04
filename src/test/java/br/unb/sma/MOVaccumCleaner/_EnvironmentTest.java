package br.unb.sma.MOVaccumCleaner;

import static org.junit.Assert.assertEquals;
import static br.unb.sma.MOVaccumCleaner.EnvironmentAssertions.*;

import java.awt.Point;

import org.junit.Test;

public class _EnvironmentTest {
	
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
	
	@Test public void knowsWhereAnAgentIs(){
		Environment env = new Environment(3,3);
		
		MO mo1 = new MO();
		MO mo2 = new MO();
		env.addAgent(mo1, 1, 1);
		env.addAgent(mo2, 2, 2);
		
		assertEquals(new Point(1,1),env.agentPosition(mo1));
		assertEquals(new Point(2,2),env.agentPosition(mo2));
	}
	
	@Test public void printsTheRepresentation(){
		Environment env = new Environment(3,3);
		assertEquals(". . . \n. . . \n. . . \n",env.toString());
		
		env.set(1,0, Environment.State.Dirty);
		env.set(0,2, Environment.State.Dirty);
		env.set(2,2, Environment.State.Dirty);
		assertEquals(". * . \n. . . \n* . * \n",env.toString());
		
		MO mo = new MO();
		env.addAgent(mo, 1, 1);
		assertEquals(". * . \n. a . \n* . * \n",env.toString());
		
		env.agentPosition(mo,1, 0);
		assertEquals(". â . \n. . . \n* . * \n",env.toString());
	}
	
}
