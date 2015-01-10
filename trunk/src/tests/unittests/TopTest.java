package tests.unittests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import abstraction.Bottom;
import abstraction.Interval;
import abstraction.NegativeInf;
import abstraction.PositiveInf;
import abstraction.Top;

public class TopTest {

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
        assertTrue(top.equals(top));
        assertTrue(top.equals(new Top()));
        assertTrue(new Top().equals(top));
        assertFalse(top.equals(bot));
        assertFalse(top.equals(i1));
        assertFalse(top.equals(i2));
        assertFalse(top.equals(i3));
        assertFalse(top.equals(p1));
        assertFalse(top.equals(p2));
        assertFalse(top.equals(p3));
        assertFalse(top.equals(n1));
        assertFalse(top.equals(n2));
        assertFalse(top.equals(n3));
    }

    @Test
    public void testJoin() {
        assertTrue(top.join(top).equals(top));
        assertTrue(top.join(bot).equals(top));
        assertTrue(top.join(i1).equals(top));
        assertTrue(top.join(i2).equals(top));
        assertTrue(top.join(i3).equals(top));
        assertTrue(top.join(p1).equals(top));
        assertTrue(top.join(p2).equals(top));
        assertTrue(top.join(p3).equals(top));
        assertTrue(top.join(n1).equals(top));
        assertTrue(top.join(n2).equals(top));
        assertTrue(top.join(n3).equals(top));
    }

    @Test
    public void testMeet() {
        assertTrue(top.meet(top).equals(top));
        assertTrue(top.meet(bot).equals(bot));
        assertTrue(top.meet(i1).equals(i1));
        assertTrue(top.meet(i2).equals(i2));
        assertTrue(top.meet(i3).equals(i3));
        assertTrue(top.meet(p1).equals(p1));
        assertTrue(top.meet(p2).equals(p2));
        assertTrue(top.meet(p3).equals(p3));
        assertTrue(top.meet(n1).equals(n1));
        assertTrue(top.meet(n2).equals(n2));
        assertTrue(top.meet(n3).equals(n3));
    }

    @Test
    public void testWiden() {
        assertTrue(top.widen(top).equals(top));
        assertTrue(top.widen(bot).equals(top));
        assertTrue(top.widen(i1).equals(top));
        assertTrue(top.widen(i2).equals(top));
        assertTrue(top.widen(i3).equals(top));
        assertTrue(top.widen(p1).equals(top));
        assertTrue(top.widen(p2).equals(top));
        assertTrue(top.widen(p3).equals(top));
        assertTrue(top.widen(n1).equals(top));
        assertTrue(top.widen(n2).equals(top));
        assertTrue(top.widen(n3).equals(top));
    }

    @Test
    public void testAdd() {
        assertTrue(top.add(top).equals(top));
        assertTrue(top.add(bot).equals(top));
        assertTrue(top.add(i1).equals(top));
        assertTrue(top.add(i2).equals(top));
        assertTrue(top.add(i3).equals(top));
        assertTrue(top.add(p1).equals(top));
        assertTrue(top.add(p2).equals(top));
        assertTrue(top.add(p3).equals(top));
        assertTrue(top.add(n1).equals(top));
        assertTrue(top.add(n2).equals(top));
        assertTrue(top.add(n3).equals(top));
    }

    @Test
    public void testSub() {
        assertTrue(top.sub(top).equals(top));
        assertTrue(top.sub(bot).equals(top));
        assertTrue(top.sub(i1).equals(top));
        assertTrue(top.sub(i2).equals(top));
        assertTrue(top.sub(i3).equals(top));
        assertTrue(top.sub(p1).equals(top));
        assertTrue(top.sub(p2).equals(top));
        assertTrue(top.sub(p3).equals(top));
        assertTrue(top.sub(n1).equals(top));
        assertTrue(top.sub(n2).equals(top));
        assertTrue(top.sub(n3).equals(top));
    }

    @Test
    public void testMul() {
        assertTrue(top.mul(top).equals(top));
        assertTrue(top.mul(bot).equals(top));
        assertTrue(top.mul(i1).equals(top));
        assertTrue(top.mul(i2).equals(top));
        assertTrue(top.mul(i3).equals(top));
        assertTrue(top.mul(p1).equals(top));
        assertTrue(top.mul(p2).equals(top));
        assertTrue(top.mul(p3).equals(top));
        assertTrue(top.mul(n1).equals(top));
        assertTrue(top.mul(n2).equals(top));
        assertTrue(top.mul(n3).equals(top));
    }

    @Test
    public void testDiv() {
        assertTrue(top.div(top).equals(top));
        assertTrue(top.div(bot).equals(top));
        assertTrue(top.div(i1).equals(top));
        assertTrue(top.div(i2).equals(top));
        assertTrue(top.div(i3).equals(top));
        assertTrue(top.div(p1).equals(top));
        assertTrue(top.div(p2).equals(top));
        assertTrue(top.div(p3).equals(top));
        assertTrue(top.div(n1).equals(top));
        assertTrue(top.div(n2).equals(top));
        assertTrue(top.div(n3).equals(top));
    }

    @Test
    public void testMod() {
        assertTrue(top.mod(top).equals(top));
        assertTrue(top.mod(bot).equals(top));
        assertTrue(top.mod(i1).equals(top));
        assertTrue(top.mod(i2).equals(top));
        assertTrue(top.mod(i3).equals(top));
        assertTrue(top.mod(p1).equals(top));
        assertTrue(top.mod(p2).equals(top));
        assertTrue(top.mod(p3).equals(top));
        assertTrue(top.mod(n1).equals(top));
        assertTrue(top.mod(n2).equals(top));
        assertTrue(top.mod(n3).equals(top));
    }
}
