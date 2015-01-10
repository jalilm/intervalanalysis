package lattice_element_tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import abstraction.Bottom;
import abstraction.Interval;
import abstraction.NegativeInf;
import abstraction.PositiveInf;
import abstraction.Top;

public class BottomTest {

    static Top top;
    static Bottom bot;
    static Interval i1, i2, i3;
    static PositiveInf p1, p2, p3;
    static NegativeInf n1, n2, n3;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        top = new Top();
        bot = new Bottom();
        i1 = new Interval(-1,1);
        i2 = new Interval(-1,-1);
        i3 = new Interval(1,1);
        p1 = new PositiveInf(-1);
        p2 = new PositiveInf(0);
        p3 = new PositiveInf(1);
        n1 = new NegativeInf(-1);
        n2 = new NegativeInf(0);
        n3 = new NegativeInf(1);
    }

    @Test
    public void testEquality() {
        assertTrue(bot.equals(bot));
        assertTrue(bot.equals(new Bottom()));
        assertTrue(new Bottom().equals(bot));
        assertFalse(bot.equals(top));
        assertFalse(bot.equals(i1));
        assertFalse(bot.equals(i2));
        assertFalse(bot.equals(i3));
        assertFalse(bot.equals(p1));
        assertFalse(bot.equals(p2));
        assertFalse(bot.equals(p3));
        assertFalse(bot.equals(n1));
        assertFalse(bot.equals(n2));
        assertFalse(bot.equals(n3));
    }
    
    @Test
    public void testJoin() {
        assertTrue(bot.join(bot).equals(bot));
        assertTrue(bot.join(top).equals(top));
        assertTrue(bot.join(i1).equals(i1));
        assertTrue(bot.join(i2).equals(i2));
        assertTrue(bot.join(i3).equals(i3));
        assertTrue(bot.join(p1).equals(p1));
        assertTrue(bot.join(p2).equals(p2));
        assertTrue(bot.join(p3).equals(p3));
        assertTrue(bot.join(n1).equals(n1));
        assertTrue(bot.join(n2).equals(n2));
        assertTrue(bot.join(n3).equals(n3));
    }
    
    // TODO Jalil remove
    @Ignore
    @Test
    public void testMeet() {
        assertTrue(bot.meet(top).equals(bot));
        assertTrue(bot.meet(bot).equals(bot));
        assertTrue(bot.meet(i1).equals(bot));
        assertTrue(bot.meet(i2).equals(bot));
        assertTrue(bot.meet(i3).equals(bot));
        assertTrue(bot.meet(p1).equals(bot));
        assertTrue(bot.meet(p2).equals(bot));
        assertTrue(bot.meet(p3).equals(bot));
        assertTrue(bot.meet(n1).equals(bot));
        assertTrue(bot.meet(n2).equals(bot));
        assertTrue(bot.meet(n3).equals(bot));
    }
    
    @Test
    public void testWiden() {
        assertTrue(bot.widen(top).equals(top));
        assertTrue(bot.widen(bot).equals(bot));
        assertTrue(bot.widen(i1).equals(top));
        assertTrue(bot.widen(i2).equals(top));
        assertTrue(bot.widen(i3).equals(top));
        assertTrue(bot.widen(p1).equals(top));
        assertTrue(bot.widen(p2).equals(top));
        assertTrue(bot.widen(p3).equals(top));
        assertTrue(bot.widen(n1).equals(top));
        assertTrue(bot.widen(n2).equals(top));
        assertTrue(bot.widen(n3).equals(top));
    }
    
    @Test
    public void testAdd() {
        assertTrue(bot.add(top).equals(top));
        assertTrue(bot.add(bot).equals(bot));
        assertTrue(bot.add(i1).equals(bot));
        assertTrue(bot.add(i2).equals(bot));
        assertTrue(bot.add(i3).equals(bot));
        assertTrue(bot.add(p1).equals(bot));
        assertTrue(bot.add(p2).equals(bot));
        assertTrue(bot.add(p3).equals(bot));
        assertTrue(bot.add(n1).equals(bot));
        assertTrue(bot.add(n2).equals(bot));
        assertTrue(bot.add(n3).equals(bot));
    }
    
    @Test
    public void testSub() {
        assertTrue(bot.sub(top).equals(top));
        assertTrue(bot.sub(bot).equals(bot));
        assertTrue(bot.sub(i1).equals(bot));
        assertTrue(bot.sub(i2).equals(bot));
        assertTrue(bot.sub(i3).equals(bot));
        assertTrue(bot.sub(p1).equals(bot));
        assertTrue(bot.sub(p2).equals(bot));
        assertTrue(bot.sub(p3).equals(bot));
        assertTrue(bot.sub(n1).equals(bot));
        assertTrue(bot.sub(n2).equals(bot));
        assertTrue(bot.sub(n3).equals(bot));
    }
    
    @Test
    public void testMul() {
        assertTrue(bot.mul(top).equals(top));
        assertTrue(bot.mul(bot).equals(bot));
        assertTrue(bot.mul(i1).equals(bot));
        assertTrue(bot.mul(i2).equals(bot));
        assertTrue(bot.mul(i3).equals(bot));
        assertTrue(bot.mul(p1).equals(bot));
        assertTrue(bot.mul(p2).equals(bot));
        assertTrue(bot.mul(p3).equals(bot));
        assertTrue(bot.mul(n1).equals(bot));
        assertTrue(bot.mul(n2).equals(bot));
        assertTrue(bot.mul(n3).equals(bot));
    }
    
    @Test
    public void testDiv() {
        assertTrue(bot.div(top).equals(top));
        assertTrue(bot.div(bot).equals(bot));
        assertTrue(bot.div(i1).equals(bot));
        assertTrue(bot.div(i2).equals(bot));
        assertTrue(bot.div(i3).equals(bot));
        assertTrue(bot.div(p1).equals(bot));
        assertTrue(bot.div(p2).equals(bot));
        assertTrue(bot.div(p3).equals(bot));
        assertTrue(bot.div(n1).equals(bot));
        assertTrue(bot.div(n2).equals(bot));
        assertTrue(bot.div(n3).equals(bot));
    }
    
    @Test
    public void testMod() {
        assertTrue(bot.mod(top).equals(top));
        assertTrue(bot.mod(bot).equals(bot));
        assertTrue(bot.mod(i1).equals(bot));
        assertTrue(bot.mod(i2).equals(bot));
        assertTrue(bot.mod(i3).equals(bot));
        assertTrue(bot.mod(p1).equals(bot));
        assertTrue(bot.mod(p2).equals(bot));
        assertTrue(bot.mod(p3).equals(bot));
        assertTrue(bot.mod(n1).equals(bot));
        assertTrue(bot.mod(n2).equals(bot));
        assertTrue(bot.mod(n3).equals(bot));
    }


}
