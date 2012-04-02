package br.unb.sma.MOVaccumCleaner;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class _MOTest {

	@Test public void shouldCleanCurrentPositionIfDirty(){
		Environment env = new Environment(2,2);
		MO mo = new MO();
		env.addAgent(mo,0,0);
		env.set(1,1,Environment.State.Dirty);
		env.set(0,1,Environment.State.Dirty);

		// TODO: This tests the environment
		assertEquals(Environment.State.Clean,env.getState(0,0)); 
		assertEquals(Environment.State.Dirty,env.getState(0,1)); 
		assertEquals(Environment.State.Clean,env.getState(1,0)); 
		assertEquals(Environment.State.Dirty,env.getState(1,1)); 
		
		env.setPosition(mo,1,1);
		mo.doIt();
		assertEquals(Environment.State.Clean,env.getState(0,0)); 
		assertEquals(Environment.State.Dirty,env.getState(0,1)); 
		assertEquals(Environment.State.Clean,env.getState(1,0)); 
		assertEquals(Environment.State.Clean,env.getState(1,1));
		assertEquals(new Point(1,1),env.getPosition(mo));
		
		env.setPosition(mo,0,1);
		mo.doIt();
		assertEquals(Environment.State.Clean,env.getState(0,0)); 
		assertEquals(Environment.State.Clean,env.getState(0,1)); 
		assertEquals(Environment.State.Clean,env.getState(1,0)); 
		assertEquals(Environment.State.Clean,env.getState(1,1));
		assertEquals(new Point(0,1),env.getPosition(mo));
	}
	
	
}
