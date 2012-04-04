package br.unb.sma.MOVaccumCleaner;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class _MOTest {

	private Environment.State C = Environment.State.Clean;
	private Environment.State D = Environment.State.Dirty;
	
	private void assertEnvironment(Environment.State[][] state, Environment env){
		for (int y = 0 ; y < state.length ; y ++){
			for (int x = 0; x < state[y].length; x++){
				String msg= "Cell ("+x+","+y+") Expected:<"+state[y][x]
							+"> but was: <"+env.get(x, y)+">";
				assertEquals(msg,state[y][x],env.get(x, y));
			}
		}
	}
	
	@Test public void cleanCurrentPositionIfDirty(){
		Environment env = new Environment(2,2);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		env.set(1,1,Environment.State.Dirty);
		env.set(0,1,Environment.State.Dirty);

		// TODO: This tests the environment
		assertEnvironment(new Environment.State[][]{
				{C,C},
				{D,D}
		},env);
		
		env.agentPosition(1,1);
		mo.doIt();
		assertEnvironment(new Environment.State[][]{
				{C,C},
				{D,C}
		},env);
		assertEquals(new Point(1,1),env.agentPosition());
		
		env.agentPosition(0,1);
		mo.doIt();
		assertEnvironment(new Environment.State[][]{
				{C,C},
				{C,C}
		},env);
		assertEquals(new Point(0,1),env.agentPosition());
	}
	
	@Test public void moveRightIfClean(){
		Environment env = new Environment(2,2);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		
		assertEquals(new Point(0,0),env.agentPosition());
		mo.doIt();
		assertEquals(new Point(1,0),env.agentPosition());
	}
	
	@Test public void ifCleanMoveScanningForDirt(){
		Environment env = new Environment(3,3);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		
		assertEquals(new Point(0,0),env.agentPosition());
		mo.doIt();	assertEquals(new Point(1,0),env.agentPosition());
		mo.doIt();	assertEquals(new Point(2,0),env.agentPosition());
		mo.doIt();	assertEquals(new Point(2,1),env.agentPosition());
		mo.doIt();	assertEquals(new Point(1,1),env.agentPosition());
		mo.doIt();	assertEquals(new Point(0,1),env.agentPosition());
		mo.doIt();	assertEquals(new Point(0,2),env.agentPosition());
		mo.doIt();	assertEquals(new Point(1,2),env.agentPosition());
		mo.doIt();	assertEquals(new Point(2,2),env.agentPosition());
	}
	
	@Test public void stopsInTheLastCell(){
		Environment env = new Environment(3,3);
		MO mo = new MO();
		env.addAgent(mo,2,2);
		
		assertEquals(new Point(2,2),env.agentPosition());
		mo.doIt();	assertEquals(new Point(2,2),env.agentPosition());
	}
}
