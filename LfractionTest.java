import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

/** Testklass.
 * @author Zakhar Kutianskyi
 */
public class LfractionTest {

   @Test (timeout=1000)
    public void testPowZero() {
        Lfraction fraction = new Lfraction(3, 5);
        Lfraction result = fraction.pow(0);
        assertEquals(new Lfraction(1, 1), result);
    }

    @Test (timeout=1000)
    public void testPowOne() {
        Lfraction fraction = new Lfraction(3, 5);
        Lfraction result = fraction.pow(1);
        assertEquals(fraction, result);
    }

    @Test (timeout=1000)
    public void testPowNegative() {
        Lfraction fraction = new Lfraction(3, 5);
        Lfraction result = fraction.pow(-2);
        assertEquals(new Lfraction(25, 9), result);
    }

    @Test (timeout=1000)
    public void testPowPositive() {
        Lfraction fraction = new Lfraction(3, 5);
        Lfraction result = fraction.pow(3);
        assertEquals(new Lfraction(27, 125), result);
    }

    @Test(expected = RuntimeException.class)
    public void testPowZeroNumerator() {
        Lfraction fraction = new Lfraction(0, 5);
        fraction.pow(-1);
    }

    @Test(expected = RuntimeException.class)
    public void testPowZeroDenominator() {
        Lfraction fraction = new Lfraction(3, 0);
        fraction.pow(-1);
    }

    @Test
    public void testPowLargeExponent() {
        Lfraction fraction = new Lfraction(2, 3);
        Lfraction result = fraction.pow(10);
        assertEquals(new Lfraction(1024, 59049), result);
    }

}

