package tests.unittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstraction.Bottom;
import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.NegativeInf;
import abstraction.PositiveInf;
import abstraction.Top;

public class TestIntervalLattice {
	
	@Test
	public void testConstruction() throws Exception {
		LatticeElement i = new Interval(1,2);
		assertNotNull(i);
		LatticeElement top = new Top();
		assertNotNull(top);
		LatticeElement bottom = new Bottom();
		assertNotNull(bottom);
		LatticeElement pinf = new PositiveInf(-2);
		assertNotNull(pinf);
		LatticeElement ninf = new NegativeInf(2);
		assertNotNull(ninf);
	}
	
	@Test
	public void testMeet() throws Exception {
		LatticeElement i1 = new Interval(1,2);
		LatticeElement i2 = new Interval(-5,0);
		LatticeElement top = new Top();
		LatticeElement bottom = new Bottom();
		LatticeElement pinf = new PositiveInf(-2);
		LatticeElement ninf = new NegativeInf(2);
		
		//Interval
		assertEquals(i1.meet(i2), bottom);
		assertEquals(i2.meet(i1), bottom);
		assertEquals(i1.meet(pinf), i1);
		assertEquals(i2.meet(pinf), new Interval(-2,0));
		assertEquals(i1.meet(ninf), i1);
		assertEquals(i2.meet(ninf), i2);
		assertEquals(i1.meet(top), i1);
		assertEquals(i2.meet(top), i2);
		assertEquals(i1.meet(bottom), bottom);
		assertEquals(i2.meet(bottom), bottom);
		
		//Top
		assertEquals(top.meet(i1), i1);
		assertEquals(top.meet(i2), i2);
		assertEquals(top.meet(top), top);
		assertEquals(top.meet(bottom), bottom);
		assertEquals(top.meet(pinf), pinf);
		assertEquals(top.meet(ninf), ninf);

		//Bottom
		assertEquals(bottom.meet(i1), bottom);
		assertEquals(bottom.meet(i2), bottom);
		assertEquals(bottom.meet(bottom), bottom);
		assertEquals(bottom.meet(pinf), bottom);
		assertEquals(bottom.meet(ninf), bottom);
		
		//Ninf
		assertEquals(ninf.meet(i1), i1);
		assertEquals(ninf.meet(i2), i2);
		assertEquals(ninf.meet(bottom), bottom);
		assertEquals(ninf.meet(pinf), new Interval(-2,2));
		assertEquals(ninf.meet(ninf), ninf);
		assertEquals(ninf.meet(top), ninf);
		
		//Pinf
		assertEquals(pinf.meet(i1), i1);
		assertEquals(pinf.meet(i2), new Interval(-2,0));
		assertEquals(pinf.meet(bottom), bottom);
		assertEquals(pinf.meet(pinf), pinf);
		assertEquals(pinf.meet(ninf), new Interval(-2,2));
		assertEquals(pinf.meet(top), pinf);

	}
	
	@Test
	public void testJoin() throws Exception {
		LatticeElement i1 = new Interval(1,2);
		LatticeElement i2 = new Interval(-5,0);
		LatticeElement top = new Top();
		LatticeElement bottom = new Bottom();
		LatticeElement pinf = new PositiveInf(-2);
		LatticeElement ninf = new NegativeInf(2);
		
		Interval join = new Interval(-5,2);
		assertEquals(join, i1.join(i2));
		assertEquals(join, i2.join(i1));
			
		//Top
		assertEquals(top.join(top),top);
		assertEquals(top.join(i1),top);
		assertEquals(top.join(i2),top);
		assertEquals(top.join(top),top);
		assertEquals(top.join(pinf),top);
		assertEquals(top.join(ninf),top);
		
		//Bottom
		assertEquals(bottom.join(bottom),bottom);
		assertEquals(bottom.join(i1),i1);
		assertEquals(bottom.join(i2),i2);
		assertEquals(bottom.join(top),top);
		assertEquals(bottom.join(pinf),pinf);
		assertEquals(bottom.join(ninf),ninf);
	}
	
	@Test
	public void testMath() throws Exception {
		LatticeElement i1 = new Interval(1,2);
		LatticeElement i2 = new Interval(4,4);
		Interval sum = new Interval(5,6);
		assertEquals(i1.add(i2), sum);
		assertEquals(i2.add(i1), sum);
	}
	
	@Test
	public void testBuilder() throws Exception {
		LatticeElement i1 = new Interval(1,2);
		LatticeElement i2 = new Interval(-5,0);
		LatticeElement top = new Top();
		LatticeElement bottom = new Bottom();
		LatticeElement pinf = new PositiveInf(-2);
		LatticeElement ninf = new NegativeInf(2);
		
		assertEquals(new PositiveInf(1), i1.createLowToPositiveInf());
		assertEquals(new PositiveInf(-5), i2.createLowToPositiveInf());
		assertEquals(top, top.createLowToPositiveInf());
		assertEquals(bottom, bottom.createLowToPositiveInf());
		assertEquals(pinf, pinf.createLowToPositiveInf());
		assertEquals(top, ninf.createLowToPositiveInf());
		
		assertEquals(new NegativeInf(2), i1.createNegativeInfToHigh());
		assertEquals(new NegativeInf(0), i2.createNegativeInfToHigh());
		assertEquals(top, top.createNegativeInfToHigh());
		assertEquals(bottom, bottom.createNegativeInfToHigh());
		assertEquals(top, pinf.createNegativeInfToHigh());
		assertEquals(ninf, ninf.createNegativeInfToHigh());
	}
	
}
