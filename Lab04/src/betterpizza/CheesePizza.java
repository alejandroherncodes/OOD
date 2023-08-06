package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

public class CheesePizza extends AlaCartePizza {

  /** Create a new cheese pizza given a builder pizza with fields
   *
   * @return CheesePizzaBuilder
   */
  public CheesePizza(CheesePizzaBuilder builder) {
    super(builder.size, builder.crust, builder.toppings);
  }

  /** A static class used to compile Cheese Pizzas using a PizzaBuilder<> */
  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizzaBuilder> {


    // Default-case constructor
    public CheesePizzaBuilder() {
      super();
      this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
    }

    /** Creates a new Pizza with the given builder pizza's fields
     *
     * @return CheesePizza                   a new cheese pizza
     */
    @Override
    public ObservablePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Pizza size is unspecified");
      }
      return new CheesePizza(this);
    }

    /** Returns the indicated builder pizza
     *
     * @return CheesePizzaBuilder
     */
    @Override
    protected CheesePizzaBuilder returnBuilder() {
      return this;
    }
  }
}