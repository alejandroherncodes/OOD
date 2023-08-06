/* A class representing a simple fraction */
public class SimpleFraction implements Fraction {
  private int numerator;
  private int denominator;

  /* Adds this fraction to another fraction, given numerator and denominator values. */
  public SimpleFraction(int numerator, int denominator) throws IllegalArgumentException {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be zero.");
    }

    if (numerator < 0 || denominator < 0) {
      throw new IllegalArgumentException("Fraction must be non-negative.");
    }

    this.numerator = numerator;
    this.denominator = denominator;
  }

  /* Adds this fraction to the other fraction. */
  public Fraction add(Fraction other) {
    SimpleFraction given = (SimpleFraction) other;

    int addNumerator = (this.numerator * given.denominator) +
            (given.numerator * this.denominator);
    int commonDenominator = this.denominator * given.denominator;

    return new SimpleFraction(addNumerator, commonDenominator);
  }

  /* Adds this fraction to another fraction, in the form of numerator, denominator. */
  public Fraction add(int numerator, int denominator) throws IllegalArgumentException {
    SimpleFraction other = new SimpleFraction(numerator, denominator);

    if (numerator < 0 || denominator < 0) {
      throw new IllegalArgumentException("Fraction must be non-negative.");
    }

    return this.add(other);
  }

  /* Returns the decimal value corresponding to the fraction's value. */
  public double getDecimalValue(int places) {
    double multiply = Math.pow(10, places);
    double decimal = (double) numerator / denominator;

    return Math.round(decimal * multiply) / multiply;
  }

  @Override
  /* Returns a string resembling the fraction */
  public String toString() {
    return numerator + "/" + denominator;
  }
}
