/* An interface to house different types of fractions */
public interface Fraction {

  /* Adds this fraction to the other fraction */
  Fraction add(Fraction other);

  /* Adds this fraction to another fraction, given numerator and denominator values. */
  Fraction add(int numerator, int denominator) throws IllegalArgumentException;

  /* Returns the decimal value corresponding to the fraction. */
  double getDecimalValue(int places);
}
