package tests.unittests;

import static org.junit.Assert.*;
import intervalAnalysis.State;

import org.junit.Test;

import abstraction.Interval;
import abstraction.LatticeElement;
import soot.Local;
import soot.IntType;
import soot.jimple.internal.JimpleLocal;

public class TestStateLattice {
    State bottom; 
    State emptyState = new State();
    Local a,b,c;
    final State twoVariablesState = new State();
    final State threeVariablesState = new State();

    
    public TestStateLattice()
    {
        bottom = new State();
        bottom.setBottom(true);

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
    public void testMeet() {
        assertEquals(bottom, threeVariablesState.meet(bottom));
        assertEquals(bottom, bottom.meet(threeVariablesState));
    }
    
    @Test
    public void testJoin() {
        assertEquals(threeVariablesState, threeVariablesState.join(bottom));
        assertEquals(threeVariablesState, bottom.join(threeVariablesState));
    }

}
