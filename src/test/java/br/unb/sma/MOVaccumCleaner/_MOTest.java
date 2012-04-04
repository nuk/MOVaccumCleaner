package br.unb.sma.MOVaccumCleaner;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class _MOTest {

	@Test public void cleanCurrentPositionIfDirty(){
		Environment env = new Environment(2,2);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		env.set(1,1,Environment.State.Dirty);
		env.set(0,1,Environment.State.Dirty);

		// TODO: This tests the environment
		assertEquals(Environment.State.Clean,env.get(0,0)); 
		assertEquals(Environment.State.Dirty,env.get(0,1)); 
		assertEquals(Environment.State.Clean,env.get(1,0)); 
		assertEquals(Environment.State.Dirty,env.get(1,1)); 
		
		env.setPosition(mo,1,1);
		mo.doIt();
		assertEquals(Environment.State.Clean,env.get(0,0)); 
		assertEquals(Environment.State.Dirty,env.get(0,1)); 
		assertEquals(Environment.State.Clean,env.get(1,0)); 
		assertEquals(Environment.State.Clean,env.get(1,1));
		assertEquals(new Point(1,1),env.getPosition(mo));
		
		env.setPosition(mo,0,1);
		mo.doIt();
		assertEquals(Environment.State.Clean,env.get(0,0)); 
		assertEquals(Environment.State.Clean,env.get(0,1)); 
		assertEquals(Environment.State.Clean,env.get(1,0)); 
		assertEquals(Environment.State.Clean,env.get(1,1));
		assertEquals(new Point(0,1),env.getPosition(mo));
	}
	
	@Test public void moveRightIfClean(){
		Environment env = new Environment(2,2);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		
		assertEquals(new Point(0,0),env.getPosition(mo));
		mo.doIt();
		assertEquals(new Point(1,0),env.getPosition(mo));
	}
	
	@Test public void ifCleanMoveScanningForDirt(){
		Environment env = new Environment(3,3);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		
		assertEquals(new Point(0,0),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(1,0),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(2,0),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(2,1),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(1,1),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(0,1),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(0,2),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(1,2),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(2,2),env.getPosition(mo));
	}
	
	@Test public void stopsInTheLastCell(){
		Environment env = new Environment(3,3);
		MO mo = new MO();
		env.addAgent(mo,2,2);
		
		assertEquals(new Point(2,2),env.getPosition(mo));
		mo.doIt();	assertEquals(new Point(2,2),env.getPosition(mo));
	}
}
