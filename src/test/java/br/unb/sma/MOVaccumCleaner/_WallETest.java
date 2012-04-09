package br.unb.sma.MOVaccumCleaner;

import static br.unb.sma.MOVaccumCleaner.EnvironmentAssertions.*;
import static org.junit.Assert.assertEquals;
import br.unb.sma.MOVaccumCleaner.Environment.State;

import java.awt.Point;

import org.junit.Test;

public class _WallETest {

	@Test public void messWithACellWith10PercentChance(){
		Environment env = new Environment(3, 3);
		final double[] mess = new double[]{0};
		WallE walle = new WallE(){
			protected double random(){	return mess[0];	}
		};
		env.addAgent(walle,0,0);
		
		env.agentPosition(walle, 1,1);
		mess[0] = 0.91; walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,D,C},
				{C,C,C},
		},env);
		assertEquals(new Point(1,1),env.agentPosition(walle));
		
		env.agentPosition(walle, 0,2);
		mess[0] = 0.89; walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,D,C},
				{C,C,C},
		},env);
		
		env.agentPosition(walle, 1,2);
		mess[0] = 0.99; walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,D,C},
				{C,D,C},
		},env);
		assertEquals(new Point(1,2),env.agentPosition(walle));
	}
	
	@Test public void whenMessStayAtTheSameSpot(){
		Environment env = new Environment(3, 3);
		WallE walle = new WallE(){
			protected double random(){	return 1;	} 
		};
		env.addAgent(walle,0,0);
		
		env.agentPosition(walle, 1,1);
		walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,D,C},
				{C,C,C},
		},env);
		assertEquals(new Point(1,1),env.agentPosition(walle));
	}
	
	@Test public void whenDontMessMoveRandomlyWith25PercentChanceForEachDirection(){
		Environment env = new Environment(3, 3);
		final double[] mess = new double[]{0};
		WallE walle = new WallE(){
			protected double random(){	return mess[0];	} 
		};
		env.addAgent(walle,0,0);
		
		env.agentPosition(walle, 1,1);
		mess[0] = 0.76;	walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,C,C},
				{C,C,C},
		},env);	assertEquals(new Point(2,1),env.agentPosition(walle));
		
		mess[0] = 0.1;	walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,C,C},
				{C,C,C},
		},env);	assertEquals(new Point(2,0),env.agentPosition(walle));
		
		mess[0] = 0.65;	walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,C,C},
				{C,C,C},
		},env);	assertEquals(new Point(1,0),env.agentPosition(walle));
		
		mess[0] = 0.26;	walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,C,C},
				{C,C,C},
		},env);	assertEquals(new Point(1,1),env.agentPosition(walle));
	}
	
	@Test public void doesNotCrashDuringMovementNearBorder(){
		Environment env = new Environment(3, 3);
		WallE walle = new WallE(){
			protected double random(){	return 0.9;	} 
		};
		env.addAgent(walle,0,0);
		
		env.agentPosition(walle, 2,2);
		walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,C,C},
				{C,C,C},
		},env);
		assertEquals(new Point(2,2),env.agentPosition(walle));
	}
	
}

