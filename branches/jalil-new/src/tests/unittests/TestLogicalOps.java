package tests.unittests;

import static org.junit.Assert.*;
import intervalAnalysis.State;

import org.junit.Test;

import soot.IntType;
import soot.Local;
import soot.Type;
import soot.Value;
import soot.jimple.IntConstant;
import soot.jimple.internal.JimpleLocal;
import abstraction.Bottom;
import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.NegativeInf;
import abstraction.PositiveInf;
import abstraction.Top;
import abstraction.operations.EqOp;
import abstraction.operations.GeOp;
import abstraction.operations.GtOp;
import abstraction.operations.LeOp;
import abstraction.operations.LtOp;
import abstraction.operations.NeqOp;


public class TestLogicalOps {
	EqOp eq = new EqOp();
	NeqOp neq = new NeqOp();
	GeOp ge = new GeOp();
	GtOp gt = new GtOp();
	LeOp le = new LeOp();
	LtOp lt = new LtOp();
	State emptyState = new State();
	Local a,b,c;
	final State twoVariablesState = new State();
	final State threeVariablesState = new State();
	Bottom bottom = new Bottom();
	Top top = new Top();
	
	public TestLogicalOps() {
		a = new JimpleLocal("a",new IntType(null));
		LatticeElement al = new Interval(-1, 1);  // a = [-1,1]
		b = new JimpleLocal("b",new IntType(null));
		LatticeElement bl = new Interval(-1, 1);  // b = [-1,1]
		c = new JimpleLocal("c",new IntType(null));
		LatticeElement cl = new Interval(10, 20);   //c = [10,20]
		
		twoVariablesState.setVarState(a,al);
		twoVariablesState.setVarState(b,bl);
		
		threeVariablesState.setVarState(a,al);
		threeVariablesState.setVarState(b,bl);
		threeVariablesState.setVarState(c,cl);
	}
	
	@Test
	public void testConstruction() throws Exception {	
		assertNotNull(eq);
		assertNotNull(neq);
		assertNotNull(ge);
		assertNotNull(gt);
		assertNotNull(le);
		assertNotNull(lt);
	}
	
	@Test
	public void testEq() throws Exception {
		// [-1,1]==[5,5] = bottom
		assertEquals(emptyState, eq.op(twoVariablesState, a, IntConstant.v(5)));
		assertEquals(emptyState, eq.op(threeVariablesState, a, c));
		
		//[-1,1]==[-1,1]=[-1,1]
		State result = twoVariablesState.clone();
		assertEquals(result, eq.op(twoVariablesState, a, a));
		
		result = twoVariablesState.clone();
		assertEquals(result, eq.op(twoVariablesState, a, b));
		
		
	}
	
	@Test
	public void testGt() throws Exception {
		// [-1,1]>[5,5] = bottom
		assertEquals(emptyState, gt.op(twoVariablesState, a, IntConstant.v(5)));
		assertEquals(emptyState, gt.op(twoVariablesState, IntConstant.v(-5),a));
		assertEquals(emptyState, gt.op(twoVariablesState, IntConstant.v(3),IntConstant.v(5)));
		assertEquals(emptyState, gt.op(twoVariablesState, a, a));
		
		//[-1,1]==[-1,1]=[-1,1]
		State result = threeVariablesState.clone();
		assertEquals(result, gt.op(threeVariablesState, IntConstant.v(5),IntConstant.v(3)));
		assertEquals(result, gt.op(threeVariablesState, c,a));
		
	}
	
	@Test
	public void testNeq() throws Exception {
		// [-1,1]>[5,5] = bottom
		State in = threeVariablesState.clone();
		in.setVarState(a, new Interval(1,1));
		assertEquals(emptyState, neq.op(in, a, IntConstant.v(1)));
		assertEquals(emptyState, neq.op(in, IntConstant.v(1),a));
		assertEquals(emptyState, neq.op(in, IntConstant.v(5),IntConstant.v(5)));
		assertEquals(emptyState, neq.op(in, a, a));
	
		assertEquals(in, neq.op(in, a, IntConstant.v(2)));
		assertEquals(in, neq.op(in, IntConstant.v(2),a));
		assertEquals(in, neq.op(in, IntConstant.v(6),IntConstant.v(5)));
		assertEquals(in, neq.op(in, a, c));
		
	}
}