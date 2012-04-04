package br.unb.sma.MOVaccumCleaner;

import static br.unb.sma.MOVaccumCleaner.EnvironmentAssertions.*;
import static org.junit.Assert.assertEquals;
import br.unb.sma.MOVaccumCleaner.Environment.State;

import java.awt.Point;

import org.junit.Test;

public class _WallETest {

	@Test public void messWithACellWhenAdequate(){
		Environment env = new Environment(3, 3);
		final boolean[] mess = new boolean[]{true};
		WallE walle = new WallE(){
			protected boolean shouldMess(){	return mess[0];	}
		};
		env.addAgent(walle,0,0);
		
		env.agentPosition(walle, 1,1);
		mess[0] = true; walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,D,C},
				{C,C,C},
		},env);
		assertEquals(new Point(1,1),env.agentPosition(walle));
		
		env.agentPosition(walle, 0,2);
		mess[0] = false; walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,D,C},
				{C,C,C},
		},env);
		
		env.agentPosition(walle, 1,2);
		mess[0] = true; walle.doIt();
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
			protected boolean shouldMess(){	return true;	}
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
	
	@Test public void whenDontMessMoveRandomly(){
		Environment env = new Environment(3, 3);
		WallE walle = new WallE(){
			protected boolean shouldMess(){	return false;	}
			protected Point direction(){ return new Point(1,0);}
		};
		env.addAgent(walle,0,0);
		
		env.agentPosition(walle, 1,1);
		walle.doIt();
		assertEnvironment(new State[][]{
				{C,C,C},
				{C,C,C},
				{C,C,C},
		},env);
		assertEquals(new Point(2,1),env.agentPosition(walle));
	}
	
	@Test public void doesNotCrashDuringMovementNearBorder(){
		Environment env = new Environment(3, 3);
		WallE walle = new WallE(){
			protected boolean shouldMess(){	return false;	}
			protected Point direction(){ return new Point(1,1);}
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

