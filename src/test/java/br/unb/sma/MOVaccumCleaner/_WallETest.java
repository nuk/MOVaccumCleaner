package br.unb.sma.MOVaccumCleaner;

import static br.unb.sma.MOVaccumCleaner.EnvironmentAssertions.*;
import static org.junit.Assert.assertEquals;
import br.unb.sma.MOVaccumCleaner.Environment.State;

import java.awt.Point;

import org.junit.Test;

public class _WallETest {

	@Test public void messWithACell(){
		Environment env = new Environment(3, 3);
		WallE walle = new WallE();
		env.addAgent(walle,0,0);
		
		env.agentPosition(walle, 1,1);
		walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,D,C},
				{C,C,C},
		},env);
		assertEquals(new Point(1,1),env.agentPosition(walle));
		
		env.agentPosition(walle, 1,2);
		walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,D,C},
				{C,D,C},
		},env);
		assertEquals(new Point(1,2),env.agentPosition(walle));
	}
	
}

