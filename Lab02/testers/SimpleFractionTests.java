import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/* Provides tests for the Fraction interface */
public class SimpleFractionTests {

  @Test
  /* Tests the boundaries of fraction values, can not be zero or negative. */
  public void testValidFraction() {
    Random rand = new Random(200);

    for (int i = 0; i < 1000; i++) {
      int numerator = rand.nextInt(10000) + 1;
      int denominator = rand.nextInt(10000) + 1;
      Fraction fractionOne = new SimpleFraction(numerator, denominator);
      Fraction fractionTwo = new SimpleFraction(numerator, denominator);
      String expected = numerator + "/" + denominator;

      assertEquals("Fraction must be non-negative." + expected,
              expected, fractionOne.toString());
      assertEquals("Fraction must be non-negative." + expected,
              expected, fractionTwo.toString());
    }
  }


  @Test
  /* Tests the case a denominator is zero */
  public void testInvalidDenominator() {
    Random rand = new Random(500);

    for (int i = 0; i < 1000; i++) {
      int num = rand.nextInt();

      try {
        new SimpleFraction(num, 0);
      } catch (IllegalArgumentException e) {
      }
    }
  }

  @Test
  /* Tests for invalid fractions due to the numerator or denominator being negative */
  public void testInvalidNumDen() {
    Random rand = new Random(500);

    for (int i = 0; i < 1000; i++) {
      int numerator = rand.nextInt(1000) + 1;
      int denominator = rand.nextInt(1000) + 1;

      Fraction fraction;

      try {
        fraction = new SimpleFraction(numerator, -denominator);
        fail("Did not catch an IllegalArgumentException for" + numerator + "/-" + denominator);
      } catch (IllegalArgumentException e) {
        // Catch exceptions
      }
    }
  }

  @Test
  /* Tests the addFraction method */
  public void testAddFraction() {
    Random rand = new Random(500);

    for (int i = 0; i < 1000; i++) {
      int num1 = rand.nextInt(6000) + 1;
      int num2 = rand.nextInt(6000) + 1;
      int den1 = rand.nextInt(600) + 1;
      int den2 = rand.nextInt(600) + 1;
      Fraction fractionOne = new SimpleFraction(num1, den1);
      Fraction fractionTwo = new SimpleFraction(num2, den2);

      assertEquals(fractionOne.add(num2, den2).toString(),
              new SimpleFraction(num1 * den2 +
                      den1 * num2, den1 * den2).toString());
      assertEquals(fractionOne.add(fractionTwo).toString(),
              new SimpleFraction(num1 * den2 +
                      den1 * num2, den1 * den2).toString());
      assertEquals(fractionOne.add(fractionTwo).toString(),
              fractionOne.add(num2, den2).toString());

      try {
        fractionOne.add(num1, -den1);
        fail("Did not catch an IllegalArgumentException for" + num1 + "/-" + -den2);
      } catch (IllegalArgumentException e) {
        // Catch exceptions
      }

      try {
        fractionOne.add(-num2, den2);
        fail("Did not catch an IllegalArgumentException for" + -num2 + "/-" + den2);
      } catch (IllegalArgumentException e) {
        // Catch exceptions
      }

      assertEquals(new SimpleFraction(2,
              2).add(1, 2).toString(), "6/4");
      assertEquals(new SimpleFraction(1,
              2).add(1, 2).toString(), "4/4");
      assertEquals(new SimpleFraction(1,
              2).add(3, 4).toString(), "10/8");
      assertEquals(new SimpleFraction(5,
              6).add(7, 8).toString(), "82/48");
      assertEquals(new SimpleFraction(2, 2).add(
              new SimpleFraction(1, 2)).toString(), "6/4");
      assertEquals(new SimpleFraction(1, 2).add(
              new SimpleFraction(3, 4)).toString(), "10/8");
      assertEquals(new SimpleFraction(5, 6).add(
              new SimpleFraction(7, 8)).toString(), "82/48");
    }
  }

  @Test
  /* Tests the getDecimalVal method */
  public void testDecimalVal() {
    Random rand = new Random(500);

    for (int i = 0; i < 1000; i++) {
      int num1 = rand.nextInt(600) + 1;
      int num2 = rand.nextInt(600) + 1;
      int den1 = rand.nextInt(600) + 1;
      int den2 = rand.nextInt(600) + 1;
      Fraction fractionOne = new SimpleFraction(num1, den1);
      Fraction fractionTwo = new SimpleFraction(num2, den2);

      double valOne = (double) num1 / den1;
      int intOne = (int) Math.pow(10, Math.max(1, 2));

      fractionTwo = new SimpleFraction(num1, den1);
      double valTwo = (double) num1 / den1;
      int intTwo = (int) Math.pow(10, Math.max(1, 5));

      assertEquals(fractionOne.getDecimalValue(2),
              Math.round(valOne * intOne) / (double) intOne, 0.0);
      assertEquals(fractionTwo.getDecimalValue(5),
              Math.round(valTwo * intTwo) / (double) intTwo, 0.0);
    }

    assertEquals(new SimpleFraction(1, 2).getDecimalValue(1),
            0.5, 0.0);
    assertEquals(new SimpleFraction(7, 8).getDecimalValue(3),
            0.875, 0.0);
    assertEquals(new SimpleFraction(3, 4).getDecimalValue(2),
            0.75, 0.0);
    assertEquals(new SimpleFraction(9, 9).getDecimalValue(2),
            1.00, 0.0);
    assertEquals(new SimpleFraction(3, 2).getDecimalValue(1),
            1.5, 0.0);
  }

  @Test
  /* Tests the toString method */
  public void testToString() {
    Random rand = new Random(500);

    for (int i = 0; i < 1000; i++) {
      int num1 = rand.nextInt(2000) + 1;
      int den1 = rand.nextInt(2000) + 1;
      int num2 = rand.nextInt(2000) + 1;
      int den2 = rand.nextInt(2000) + 1;
      Fraction fractionOne = new SimpleFraction(num1, den1);
      Fraction fractionTwo = new SimpleFraction(num2, den2);

      assertEquals(fractionOne.toString(), "" + num1 + "/" + den1);
      assertEquals(fractionTwo.toString(), "" + num2 + "/" + den2);
    }

    assertEquals(new SimpleFraction(3, 4).toString(), "3/4");
    assertEquals(new SimpleFraction(5, 6).toString(), "5/6");
    assertEquals(new SimpleFraction(6, 4).toString(), "6/4");
    assertEquals(new SimpleFraction(4, 2).toString(), "4/2");
    assertEquals(new SimpleFraction(7, 3).toString(), "7/3");
    assertEquals(new SimpleFraction(1, 2).toString(), "1/2");
  }
}
