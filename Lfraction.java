import java.util.*;
import java.util.regex.PatternSyntaxException;

/** This class represents fractions of form n/d where n and d are long integer
 * numbers. Basic operations and arithmetics for fractions are provided.
 */
public class Lfraction implements Comparable<Lfraction> {

   /** Main method. Different tests. */
   public static void main (String[] param) {
      
      
   }


   // TODO!!! instance variables here
   private long numerator;
   private long denominator;

   /** Constructor.
    * @param a numerator
    * @param b denominator > 0
    */
   public Lfraction (long a, long b) {
      if (b == 0) {
         throw new RuntimeException("Division by zero is not allowed." + a + "/" + b);
      }
      long gcd = gcd(Math.abs(a), Math.abs(b));
      this.numerator = a / gcd;
      this.denominator = b / gcd;
      if (this.denominator < 0) {
         this.numerator *= -1;
         this.denominator *= -1;
      }
   }

   /** Calculates the greatest common divisor of two long integers.
    * @param a first number
    * @param b second number
    * @return greatest common divisor of a and b
    */
   private static long gcd(long a, long b) {
      while (b != 0) {
         long temp = b;
         b = a % b;
         a = temp;
      }
      return a;
   }

   /** Public method to access the numerator field.
    * @return numerator
    */
   public long getNumerator() {
      return numerator;
   }

   /** Public method to access the denominator field.
    * @return denominator
    */
   public long getDenominator() {
      return denominator; 
   }

   /** Conversion to string.
    * @return string representation of the fraction
    */
   @Override
   public String toString() {
      if (denominator == 1) {
         return String.valueOf(numerator);
      } else {
         return numerator + "/" + denominator;
      } 
   }

   /** Equality test.
    * @param m second fraction
    * @return true if fractions this and m are equal
    */
   @Override
   public boolean equals (Object m) {
      if (this == m) return true;
      if (!(m instanceof Lfraction)) return false;
      Lfraction other = (Lfraction) m;
      return numerator == other.numerator && denominator == other.denominator; 
   }

   /** Hashcode has to be the same for equal fractions and in general, different
    * for different fractions.
    * @return hashcode
    */
   @Override
   public int hashCode() {
      return Objects.hash(numerator, denominator);
   }

   /** Sum of fractions.
    * @param m second addend
    * @return this+m
    */
   public Lfraction plus (Lfraction m) {
      long newNumerator = numerator * m.denominator + m.numerator * denominator;
      long newDenominator = denominator * m.denominator;
      return new Lfraction(newNumerator, newDenominator); 
   }

   /** Multiplication of fractions.
    * @param m second factor
    * @return this*m
    */
   public Lfraction times (Lfraction m) {
      long newNumerator = numerator * m.numerator;
      long newDenominator = denominator * m.denominator;
      return new Lfraction(newNumerator, newDenominator);
   }

   /** Inverse of the fraction. n/d becomes d/n.
    * @return inverse of this fraction: 1/this
    */
   public Lfraction inverse() {
      if (numerator == 0) {
         throw new RuntimeException("Cannot compute inverse of zero.");
      }
      return new Lfraction(denominator, numerator);
   }

   /** Opposite of the fraction. n/d becomes -n/d.
    * @return opposite of this fraction: -this
    */
   public Lfraction opposite() {
      return new Lfraction(-numerator, denominator); 
   }

   /** Difference of fractions.
    * @param m subtrahend
    * @return this-m
    */
   public Lfraction minus (Lfraction m) {
      return plus(m.opposite());
   }

   /** Quotient of fractions.
    * @param m divisor
    * @return this/m
    */
   public Lfraction divideBy (Lfraction m) {
      return times(m.inverse());
   }

   /** Comparision of fractions.
    * @param m second fraction
    * @return -1 if this < m; 0 if this==m; 1 if this > m
    */
   @Override
   public int compareTo (Lfraction m) {
      long crossProduct = numerator * m.denominator - m.numerator * denominator;
      if (crossProduct < 0) {
         return -1;
      } else if (crossProduct > 0) {
         return 1;
      } else {
         return 0;
      } 
   }

   /** Clone of the fraction.
    * @return new fraction equal to this
    */
   @Override
   public Object clone() throws CloneNotSupportedException {
      return new Lfraction(numerator, denominator);
   }

   /** Integer part of the (improper) fraction.
    * @return integer part of this fraction
    */
   public long integerPart() {
      return numerator / denominator; 
   }

   /** Extract fraction part of the (improper) fraction
    * (a proper fraction without the integer part).
    * @return fraction part of this fraction
    */
   public Lfraction fractionPart() {
      return new Lfraction(numerator % denominator, denominator); 
   }

   /** Approximate value of the fraction.
    * @return real value of this fraction
    */
   public double toDouble() {
      return (double) numerator / denominator;
   }

   /** Double value f presented as a fraction with denominator d > 0.
    * @param f real number
    * @param d positive denominator for the result
    * @return f as an approximate fraction of form n/d
    */
   public static Lfraction toLfraction (double f, long d) {
      long newNumerator = Math.round(f * d);
      return new Lfraction(newNumerator, d); 
   }

   /** Conversion from string to the fraction. Accepts strings of form
    * that is defined by the toString method.
    * @param s string form (as produced by toString) of the fraction
    * @return fraction represented by s
    */
   public static Lfraction valueOf (String s) {
      if (s.isEmpty() || s.endsWith("/")) {
         throw new RuntimeException("Invalid fraction format: " + s);
     }
      try {
         String[] parts = s.split("/");
         
         if (parts.length != 2) {
             throw new RuntimeException("Invalid fraction format: " + s);
         }
 
         long numerator = Long.parseLong(parts[0]);
         long denominator = Long.parseLong(parts[1]);
 
         if (denominator == 0) {
             throw new RuntimeException("Denominator cannot be zero: " + s);
         }
 
         return new Lfraction(numerator, denominator);
     } catch (NumberFormatException e) {
         throw new RuntimeException("Invalid fraction format: " + s);
     }
   }
}
