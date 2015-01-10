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

public class PositiveIntTest {

    static Top top;
    static Bottom bot;
    static Interval i1, i2, i3;
    static PositiveInf p1, p2, p3;
    static NegativeInf n1, n2, n3;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        top = new Top();
        bot = new Bottom();
        i1 = new Interval(-1, 1);
        i2 = new Interval(-1, -1);
        i3 = new Interval(1, 1);
        p1 = new PositiveInf(-1);
        p2 = new PositiveInf(0);
        p3 = new PositiveInf(1);
        n1 = new NegativeInf(-1);
        n2 = new NegativeInf(0);
        n3 = new NegativeInf(1);
    }

    @Test
    public void testEquality() {
        assertTrue(p1.equals(p1));
        assertTrue(p2.equals(p2));
        assertTrue(p3.equals(p3));
        assertTrue(p1.equals(new PositiveInf(-1)));
        assertTrue(p2.equals(new PositiveInf(0)));
        assertTrue(p3.equals(new PositiveInf(1)));
        assertTrue(new PositiveInf(-1).equals(p1));
        assertTrue(new PositiveInf(0).equals(p2));
        assertTrue(new PositiveInf(1).equals(p3));
        assertFalse(p1.equals(p2));
        assertFalse(p1.equals(p3));
        assertFalse(p2.equals(p1));
        assertFalse(p2.equals(p3));
        assertFalse(p3.equals(p1));
        assertFalse(p3.equals(p2));
        assertFalse(p1.equals(top));
        assertFalse(p1.equals(bot));
        assertFalse(p1.equals(i1));
        assertFalse(p1.equals(i2));
        assertFalse(p1.equals(i3));
        assertFalse(p1.equals(n1));
        assertFalse(p1.equals(n2));
        assertFalse(p1.equals(n3));
    }

    @Test
    public void testJoin() {
        assertTrue(p1.join(top).equals(top));
        assertTrue(p1.join(bot).equals(p1));
        
        assertTrue(p1.join(p1).equals(p1));
        assertTrue(p1.join(p2).equals(p1));
        assertTrue(p1.join(p3).equals(p1));
        
        assertTrue(p2.join(p3).equals(p2));
        assertTrue(p3.join(p2).equals(p2));
        
        assertTrue(p3.join(i1).equals(p1));
        assertTrue(p3.join(i2).equals(p1));
        assertTrue(p3.join(i3).equals(p3));
        
        assertTrue(p3.join(n1).equals(top));
        assertTrue(p3.join(n2).equals(top));
        assertTrue(p3.join(n3).equals(top));
    }

    // TODO Jalil implement after meet
    @Ignore
    @Test
    public void testMeet() {
    }

    @Test
    public void testWiden() {
        assertTrue(p1.widen(top).equals(top));
        assertTrue(p1.widen(bot).equals(p1));
        assertTrue(p3.widen(p3).equals(p3));
        assertTrue(p3.widen(p2).equals(top));
        assertTrue(p3.widen(p1).equals(top));

        assertTrue(p1.widen(p2).equals(p1));
        assertTrue(p2.widen(p1).equals(top));

        assertTrue(p3.widen(i1).equals(top));
        assertTrue(p3.widen(new Interval(0, 0)).equals(top));
        assertTrue(p3.widen(i2).equals(top));
        assertTrue(p3.widen(i3).equals(p3));
        
        assertTrue(p3.widen(n1).equals(top));
        assertTrue(p3.widen(n2).equals(top));
        assertTrue(p3.widen(n3).equals(top));
    }

    @Test
    public void testAdd() {
        assertTrue(p3.add(top).equals(top));
        assertTrue(p3.add(bot).equals(bot));
        
        assertTrue(p3.add(p1).equals(p2));
        assertTrue(p3.add(p2).equals(p3));
        assertTrue(p3.add(p3).equals(new PositiveInf(2)));
        
        assertTrue(p3.add(i1).equals(p2));
        assertTrue(p3.add(i2).equals(p2));
        assertTrue(p3.add(i3).equals(new PositiveInf(2)));
        
        assertTrue(p3.add(n1).equals(top));
        assertTrue(p3.add(n2).equals(top));
        assertTrue(p3.add(n3).equals(top));
    }

    @Test
    public void testSub() {
        assertTrue(p3.sub(top).equals(top));
        assertTrue(p3.sub(bot).equals(bot));
        
        assertTrue(p3.sub(p1).equals(top));
        assertTrue(p3.sub(p2).equals(top));
        assertTrue(p3.sub(p3).equals(top));
        
        assertTrue(p3.sub(i1).equals(p2));
        assertTrue(p3.sub(i2).equals(new PositiveInf(2)));
        assertTrue(p3.sub(i3).equals(p2));
        
        assertTrue(p3.sub(n1).equals(new PositiveInf(2)));
        assertTrue(p3.sub(n2).equals(p3));
        assertTrue(p3.sub(n3).equals(p2));
    }

    @Test
    public void testMul() {
        assertTrue(p3.mul(top).equals(top));
        assertTrue(p3.mul(bot).equals(bot));
        
        assertTrue(p3.mul(p3).equals(p3));
        assertTrue(p2.mul(p2).equals(p2));
        assertTrue(p1.mul(p1).equals(top));
        
//        assertTrue(i1.mul(i2).equals(new Interval(-1, 1)));
//        assertTrue(i1.mul(i3).equals(new Interval(-1, 1)));
//        assertTrue(i1.mul(new Interval(5, 5)).equals(new Interval(-5, 5)));
//
//        assertTrue(i2.mul(i3).equals(new Interval(-1, -1)));
//        assertTrue(i3.mul(i2).equals(new Interval(-1, -1)));
//
//        assertTrue(i1.mul(p1).equals(top));
//        assertTrue(i1.mul(p2).equals(top));
//        assertTrue(i1.mul(p3).equals(top));
//
//        assertTrue(i2.mul(p1).equals(n3));
//        assertTrue(i2.mul(p2).equals(n2));
//        assertTrue(i2.mul(p3).equals(n1));
//
//        assertTrue(i3.mul(p1).equals(p1));
//        assertTrue(i3.mul(p2).equals(p2));
//        assertTrue(i3.mul(p3).equals(p3));
//
//        assertTrue(i1.mul(n1).equals(top));
//        assertTrue(i1.mul(n2).equals(top));
//        assertTrue(i1.mul(n3).equals(top));
//
//        assertTrue(i2.mul(n1).equals(p3));
//        assertTrue(i2.mul(n2).equals(p2));
//        assertTrue(i2.mul(n3).equals(p1));
//
//        assertTrue(i3.mul(n1).equals(n1));
//        assertTrue(i3.mul(n2).equals(n2));
//        assertTrue(i3.mul(n3).equals(n3));
    }

    // TODO Jalil please update results
    @Ignore
    @Test
    public void testDiv() {
        assertTrue(i1.div(top).equals(top));
        assertTrue(i1.div(bot).equals(bot));

        assertTrue(i1.div(i1).equals(top));
        assertTrue(i1.div(i2).equals(i1));
        assertTrue(i1.div(i3).equals(i1));
        assertTrue(i1.div(new Interval(5, 5)).equals(new Interval(0, 0)));

        assertTrue(i2.div(i3).equals(i2));
        assertTrue(i3.div(i2).equals(i2));
        
        assertTrue(i1.div(p1).equals(top));
        assertTrue(i1.div(p2).equals(top));
        assertTrue(i1.div(p3).equals(i1));
        
        assertTrue(i2.div(p1).equals(top));
        assertTrue(i2.div(p2).equals(top));
        assertTrue(i2.div(p3).equals(new Interval(-1, 0)));
        
        assertTrue(i3.div(p1).equals(top));
        assertTrue(i3.div(p2).equals(top));
        assertTrue(i3.div(p3).equals(new Interval(0, 1)));
        
        assertTrue(i1.div(n1).equals(i1));
        assertTrue(i1.div(n2).equals(top));
        assertTrue(i1.div(n3).equals(top));
        
        assertTrue(i2.div(n1).equals(new Interval(0, 1)));
        assertTrue(i2.div(n2).equals(top));
        assertTrue(i2.div(n3).equals(top));
        
        assertTrue(i3.div(n1).equals(new Interval(-1, 0)));
        assertTrue(i3.div(n2).equals(top));
        assertTrue(i3.div(n3).equals(top));
    }

    // TODO Jalil If you tightened mod implement me.
    @Ignore
    @Test
    public void testMod() {
    }

}
