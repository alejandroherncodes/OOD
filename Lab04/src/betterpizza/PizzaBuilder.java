package betterpizza;

import pizza.Size;
import pizza.Crust;
import pizza.ToppingName;
import pizza.ToppingPortion;

import java.util.HashMap;

import java.util.Map;


public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {

  // size
  public Size size;
  public Crust crust;
  public Map<ToppingName, ToppingPortion> toppings;

  public PizzaBuilder() {
    this.size = null;
    this.crust = null;
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  public abstract ObservablePizza build();

  protected abstract T returnBuilder();

  public T crust(Crust c) {
    this.crust = c;
    return returnBuilder();
  }

  public T size(Size s) {
    this.size = s;
    return returnBuilder();
  }

  public T addTopping(ToppingName tn, ToppingPortion tp) {
    this.toppings.put(tn, tp);
    return returnBuilder();
  }

  /**
   * Used to remove sauce from the Veggie pizza
   *
   * @return VeggiePizzaBuilder       without sauce
   */
  public T noSauce() {
    this.toppings.remove(ToppingName.Sauce);
    return returnBuilder();
  }

  /**
   * Removes the sauce from the left half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T leftHalfSauce() {
    this.toppings.put(ToppingName.Sauce, ToppingPortion.LeftHalf);
    return returnBuilder();
  }

  /**
   * Removes the sauce from the right half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T rightHalfSauce() {
    this.toppings.put(ToppingName.Sauce, ToppingPortion.RightHalf);
    return returnBuilder();
  }

  /**
   * Used to remove olives from the Veggie pizza
   *
   * @return VeggiePizzaBuilder       without olive
   */
  public T noOlive() {
    this.toppings.remove(ToppingName.BlackOlive);
    return returnBuilder();
  }

  /**
   * Removes the Black Olives from the left half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T leftHalfOlive() {
    this.toppings.put(ToppingName.BlackOlive, ToppingPortion.LeftHalf);
    return returnBuilder();
  }

  /**
   * Removes the Black Olives from the right half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T rightHalfOlive() {
    this.toppings.put(ToppingName.BlackOlive, ToppingPortion.RightHalf);
    return returnBuilder();
  }

  /**
   * Used to remove green pepper from the Veggie pizza
   *
   * @return VeggiePizzaBuilder       without green pepper
   */
  public T noPepper() {
    this.toppings.remove(ToppingName.GreenPepper);
    return returnBuilder();
  }

  /**
   * Removes the Green Pepper from the left half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T leftHalfPepper() {
    this.toppings.put(ToppingName.GreenPepper, ToppingPortion.LeftHalf);
    return returnBuilder();
  }

  /**
   * Removes the Green Pepper from the right half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T rightHalfPepper() {
    this.toppings.put(ToppingName.GreenPepper, ToppingPortion.RightHalf);
    return returnBuilder();
  }

  /**
   * Used to remove onion from the Veggie pizza
   *
   * @return VeggiePizzaBuilder       without onion
   */
  public T noOnion() {
    this.toppings.remove(ToppingName.Onion);
    return returnBuilder();
  }

  /**
   * Removes the Onion from the left half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T leftHalfOnion() {
    this.toppings.put(ToppingName.Onion, ToppingPortion.LeftHalf);
    return returnBuilder();
  }

  /**
   * Removes the Onion from the right half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T rightHalfOnion() {
    this.toppings.put(ToppingName.Onion, ToppingPortion.RightHalf);
    return returnBuilder();
  }

  /**
   * Used to remove jalapeno from the Veggie pizza
   *
   * @return VeggiePizzaBuilder       without jalapeno
   */
  public T noJalapeno() {
    this.toppings.remove(ToppingName.Jalapeno);
    return returnBuilder();
  }

  /**
   * Removes the Jalapeno from the left half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T leftHalfJalapeno() {
    this.toppings.put(ToppingName.Jalapeno, ToppingPortion.LeftHalf);
    return returnBuilder();
  }

  /**
   * Removes the Jalapeno from the right half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T rightHalfJalapeno() {
    this.toppings.put(ToppingName.Jalapeno, ToppingPortion.RightHalf);
    return returnBuilder();
  }

  /**
   * Used to remove tomato from the Veggie pizza
   *
   * @return VeggiePizzaBuilder       without tomato
   */
  public T noTomato() {
    this.toppings.remove(ToppingName.Tomato);
    return returnBuilder();
  }

  /**
   * Removes the Tomato from the left half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T leftHalfTomato() {
    this.toppings.put(ToppingName.Tomato, ToppingPortion.LeftHalf);
    return returnBuilder();
  }

  /**
   * Removes the Tomato from the right half of the PizzaBuilder
   *
   * @return the PizzaBuilder
   */
  public T rightHalfTomato() {
    this.toppings.put(ToppingName.Tomato, ToppingPortion.RightHalf);
    return returnBuilder();
  }

  /**
   * Used to remove cheese from this pizza
   *
   * @return CheesePizzaBuilder
   */
  public T noCheese() {
    this.toppings.remove(ToppingName.Cheese);
    return returnBuilder();
  }

  /**
   * Used to remove cheese from the left half of this pizza
   *
   * @return CheesePizzaBuilder
   */
  public T rightHalfCheese() {
    this.toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
    return returnBuilder();
  }

  /**
   * Used to remove cheese from the right half of this pizza
   *
   * @return CheesePizzaBuilder
   */
  public T leftHalfCheese() {
    this.toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
    return returnBuilder();
  }
}