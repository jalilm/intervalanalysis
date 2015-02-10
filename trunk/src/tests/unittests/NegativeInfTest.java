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

public class NegativeInfTest {

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
        assertTrue(n1.equals(n1));
        assertTrue(n2.equals(n2));
        assertTrue(n3.equals(n3));
        assertTrue(p3.equals(n1.neg()));
        assertTrue(p2.equals(n2.neg()));
        assertTrue(p1.equals(n3.neg()));
        assertTrue(n1.equals(new NegativeInf(-1)));
        assertTrue(n2.equals(new NegativeInf(0)));
        assertTrue(n3.equals(new NegativeInf(1)));
        assertTrue(new NegativeInf(-1).equals(n1));
        assertTrue(new NegativeInf(0).equals(n2));
        assertTrue(new NegativeInf(1).equals(n3));
        assertFalse(n1.equals(n2));
        assertFalse(n1.equals(n3));
        assertFalse(n2.equals(n1));
        assertFalse(n2.equals(n3));
        assertFalse(n3.equals(n1));
        assertFalse(n3.equals(n2));
        assertFalse(n1.equals(top));
        assertFalse(n1.equals(bot));
        assertFalse(n1.equals(i1));
        assertFalse(n1.equals(i2));
        assertFalse(n1.equals(i3));
        assertFalse(n1.equals(p1));
        assertFalse(n1.equals(p2));
        assertFalse(n1.equals(p3));
    }

    @Test
    public void testJoin() {
        assertTrue(n1.join(top).equals(top));
        assertTrue(n1.join(bot).equals(n1));

        assertTrue(n1.join(n1).equals(n1));
        assertTrue(n1.join(n2).equals(n2));
        assertTrue(n1.join(n3).equals(n3));

        assertTrue(n2.join(n3).equals(n3));
        assertTrue(n3.join(n2).equals(n3));

        assertTrue(n1.join(i1).equals(n3));
        assertTrue(n1.join(i2).equals(n1));
        assertTrue(n1.join(i3).equals(n3));

        assertTrue(n1.join(p1).equals(top));
        assertTrue(n1.join(p2).equals(top));
        assertTrue(n1.join(p3).equals(top));
    }


    @Test
    public void testWiden() {
        assertTrue(n1.widen(top).equals(top));
        assertTrue(n1.widen(bot).equals(n1));
        assertTrue(n1.widen(n1).equals(n1));
        assertTrue(n1.widen(n2).equals(top));
        assertTrue(n1.widen(n3).equals(top));

        assertTrue(n3.widen(n2).equals(n3));
        assertTrue(n2.widen(n3).equals(top));

        assertTrue(n1.widen(i1).equals(top));
        assertTrue(n1.widen(new Interval(0, 0)).equals(top));
        assertTrue(n1.widen(i2).equals(n1));
        assertTrue(n1.widen(i3).equals(top));

        assertTrue(n1.widen(p1).equals(top));
        assertTrue(n1.widen(p2).equals(top));
        assertTrue(n1.widen(p3).equals(top));
    }

    @Test
    public void testAdd() {
        assertTrue(n1.add(top).equals(top));
        assertTrue(n1.add(bot).equals(bot));

        assertTrue(n1.add(n1).equals(new NegativeInf(-2)));
        assertTrue(n1.add(n2).equals(n1));
        assertTrue(n1.add(n3).equals(n2));

        assertTrue(n1.add(i1).equals(n2));
        assertTrue(n1.add(i2).equals(new NegativeInf(-2)));
        assertTrue(n1.add(i3).equals(n2));

        assertTrue(n1.add(p1).equals(top));
        assertTrue(n1.add(p2).equals(top));
        assertTrue(n1.add(p3).equals(top));
    }

    @Test
    public void testSub() {
        assertTrue(n1.sub(top).equals(top));
        assertTrue(n1.sub(bot).equals(bot));

        assertTrue(n1.sub(n1).equals(top));
        assertTrue(n1.sub(n2).equals(top));
        assertTrue(n1.sub(n3).equals(top));

        assertTrue(n1.sub(i1).equals(n2));
        assertTrue(n1.sub(i2).equals(n2));
        assertTrue(n1.sub(i3).equals(new NegativeInf(-2)));

        assertTrue(n1.sub(p1).equals(n2));
        assertTrue(n1.sub(p2).equals(n1));
        assertTrue(n1.sub(p3).equals(new NegativeInf(-2)));
    }

    @Test
    public void testMul() {
        assertTrue(n1.mul(top).equals(top));
        assertTrue(n1.mul(bot).equals(bot));

        assertTrue(n1.mul(n1).equals(p3));
        assertTrue(n2.mul(n2).equals(p2));
        assertTrue(n3.mul(n3).equals(top));

        assertTrue(n1.mul(n3).equals(top));
        assertTrue(n1.mul(n2).equals(p2));
        assertTrue(n1.mul(new NegativeInf(-3)).equals(new PositiveInf(3)));

        assertTrue(n2.mul(n3).equals(top));
        assertTrue(n3.mul(n2).equals(top));
        assertTrue(n2.mul(n2).equals(p2));
        assertTrue(n2.mul(new NegativeInf(-3)).equals(p2));
        assertTrue(new NegativeInf(-3).mul(n2).equals(p2));

        assertTrue(n1.mul(i1).equals(top));
        assertTrue(n1.mul(i2).equals(p3));
        assertTrue(n1.mul(i3).equals(n1));
        assertTrue(n1.mul(new Interval(5, 5)).equals(new NegativeInf(-5)));
        assertTrue(n1.mul(new Interval(-5, -5)).equals(new PositiveInf(5)));

        assertTrue(n2.mul(i1).equals(top));
        assertTrue(n2.mul(i2).equals(p2));
        assertTrue(n2.mul(i3).equals(n2));
        assertTrue(n2.mul(new Interval(5, 5)).equals(n2));
        assertTrue(n2.mul(new Interval(-5, -5)).equals(p2));

        assertTrue(n3.mul(i1).equals(top));
        assertTrue(n3.mul(i2).equals(p1));
        assertTrue(n3.mul(i3).equals(n3));
        assertTrue(n3.mul(new Interval(5, 5)).equals(new NegativeInf(5)));
        assertTrue(n3.mul(new Interval(-5, -5)).equals(new PositiveInf(-5)));

        assertTrue(n1.mul(p1).equals(top));
        assertTrue(n1.mul(p2).equals(n2));
        assertTrue(n1.mul(p3).equals(n1));
        assertTrue(n1.mul(new PositiveInf(5)).equals(new NegativeInf(-5)));
        assertTrue(n1.mul(new PositiveInf(-5)).equals(top));

        assertTrue(n2.mul(p1).equals(top));
        assertTrue(n2.mul(p2).equals(n2));
        assertTrue(n2.mul(p3).equals(n2));
        assertTrue(n2.mul(new PositiveInf(5)).equals(n2));
        assertTrue(n2.mul(new PositiveInf(-5)).equals(top));

        assertTrue(n3.mul(p1).equals(top));
        assertTrue(n3.mul(p2).equals(top));
        assertTrue(n3.mul(p3).equals(top));
        assertTrue(n3.mul(new PositiveInf(5)).equals(top));
        assertTrue(n3.mul(new PositiveInf(-5)).equals(top));
    }

    @Test
    public void testDiv() {
        NegativeInf n5 = new NegativeInf(5);
        NegativeInf nm5 = new NegativeInf(-5);

        assertTrue(n1.div(top).equals(top));
        assertTrue(n1.div(bot).equals(bot));

        assertTrue(n1.div(n3).equals(top));
        assertTrue(n1.div(new NegativeInf(-5)).equals(p2));
        assertTrue(n1.div(n2).equals(top));
        assertTrue(n1.div(n1).equals(p2));
        assertTrue(n1.div(new NegativeInf(5)).equals(top));

        assertTrue(n2.div(n3).equals(top));
        assertTrue(n3.div(n2).equals(top));

        assertTrue(n1.div(i1).equals(top));
        assertTrue(n1.div(i2).equals(p3));
        assertTrue(n1.div(i3).equals(n1));
        assertTrue(n1.div(new Interval(5, 5)).equals(n2));
        assertTrue(n1.div(new Interval(-5, -5)).equals(p2));

        assertTrue(n2.div(i1).equals(top));
        assertTrue(n2.div(i2).equals(p2));
        assertTrue(n2.div(i3).equals(n2));
        assertTrue(n2.div(new Interval(5, 5)).equals(n2));
        assertTrue(n2.div(new Interval(-5, -5)).equals(p2));

        assertTrue(n3.div(i1).equals(top));
        assertTrue(n3.div(i2).equals(p1));
        assertTrue(n3.div(i3).equals(n3));
        assertTrue(n3.div(new Interval(5, 5)).equals(n2));
        assertTrue(n3.div(new Interval(-5, -5)).equals(p2));

        assertTrue(n5.div(i1).equals(top));
        assertTrue(n5.div(i2).equals(new PositiveInf(-5)));
        assertTrue(n5.div(i3).equals(n5));
        assertTrue(n5.div(new Interval(5, 5)).equals(n3));
        assertTrue(n5.div(new Interval(-5, -5)).equals(p1));

        assertTrue(nm5.div(i1).equals(top));
        assertTrue(nm5.div(i2).equals(new PositiveInf(5)));
        assertTrue(nm5.div(i3).equals(new NegativeInf(-5)));
        assertTrue(nm5.div(new Interval(5, 5)).equals(n1));
        assertTrue(nm5.div(new Interval(-5, -5)).equals(p3));

        assertTrue(n1.div(p1).equals(top));
        assertTrue(n1.div(p2).equals(top));
        assertTrue(n1.div(p3).equals(n2));
        assertTrue(n1.div(new PositiveInf(5)).equals(n2));
        assertTrue(n1.div(new PositiveInf(-5)).equals(top));

        assertTrue(n2.div(p1).equals(top));
        assertTrue(n2.div(p2).equals(top));
        assertTrue(n2.div(p3).equals(n2));
        assertTrue(n2.div(new PositiveInf(5)).equals(n2));
        assertTrue(n2.div(new PositiveInf(-5)).equals(top));

        assertTrue(n3.div(p1).equals(top));
        assertTrue(n3.div(p2).equals(top));
        assertTrue(n3.div(p3).equals(n3));
        assertTrue(n3.div(new PositiveInf(5)).equals(n2));
        assertTrue(n3.div(new PositiveInf(-5)).equals(top));
        
        assertTrue(n5.div(p1).equals(top));
        assertTrue(n5.div(p2).equals(top));
        assertTrue(n5.div(p3).equals(n5));
        assertTrue(n5.div(new PositiveInf(5)).equals(n3));
        assertTrue(n5.div(new PositiveInf(-5)).equals(top));
        
        assertTrue(nm5.div(p1).equals(top));
        assertTrue(nm5.div(p2).equals(top));
        assertTrue(nm5.div(p3).equals(n2));
        assertTrue(nm5.div(new PositiveInf(5)).equals(n2));
        assertTrue(nm5.div(new PositiveInf(-5)).equals(top));
    }

    // Will not be tested.
    @Ignore
    @Test
    public void testMod() {
    }


}
