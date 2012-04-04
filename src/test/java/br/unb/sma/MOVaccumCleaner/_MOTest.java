package br.unb.sma.MOVaccumCleaner;

import static org.junit.Assert.*;
import static br.unb.sma.MOVaccumCleaner.EnvironmentAssertions.*;

import java.awt.Point;

import org.junit.Test;

public class _MOTest {
	
	@Test public void cleanCurrentPositionIfDirty(){
		Environment env = new Environment(2,2);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		env.set(1,1,Environment.State.Dirty);
		env.set(0,1,Environment.State.Dirty);

		env.agentPosition(mo,1,1);
		mo.doIt();
		assertEnvironment(new Environment.State[][]{
				{C,C},
				{D,C}
		},env);
		assertEquals(new Point(1,1),env.agentPosition(mo));
		
		env.agentPosition(mo,0,1);
		mo.doIt();
		assertEnvironment(new Environment.State[][]{
				{C,C},
				{C,C}
		},env);
		assertEquals(new Point(0,1),env.agentPosition(mo));
	}
	
	@Test public void moveRightIfClean(){
		Environment env = new Environment(2,2);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		
		assertEquals(new Point(0,0),env.agentPosition(mo));
		mo.doIt();
		assertEquals(new Point(1,0),env.agentPosition(mo));
	}
	
	@Test public void ifCleanMoveScanningForDirt(){
		Environment env = new Environment(3,3);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		
		assertEquals(new Point(0,0),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(1,0),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(2,0),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(2,1),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(1,1),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(0,1),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(0,2),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(1,2),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(2,2),env.agentPosition(mo));
	}
	
	@Test public void stopsInTheLastCell(){
		Environment env = new Environment(3,3);
		MO mo = new MO();
		env.addAgent(mo,2,2);
		
		assertEquals(new Point(2,2),env.agentPosition(mo));
		mo.doIt();	assertEquals(new Point(2,2),env.agentPosition(mo));
	}
}
