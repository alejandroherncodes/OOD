package betterpizza;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public class VeggiePizza extends AlaCartePizza {

  /** Create a new veggie pizza a builder pizza with fields
   *
   * @return VeggiePizzaBuilder
   */
  private VeggiePizza(VeggiePizzaBuilder builder) {
    super(builder.size, builder.crust, builder.toppings);
  }

  /** A static class used to compile Veggie Pizzas using a PizzaBuilder<> */
  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizzaBuilder>{
    // An builder constructor that adds all toppings to a pizza
    public VeggiePizzaBuilder() {
      super();
      this.addTopping(ToppingName.Cheese,ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce,ToppingPortion.Full);
      this.addTopping(ToppingName.BlackOlive,ToppingPortion.Full);
      this.addTopping(ToppingName.GreenPepper,ToppingPortion.Full);
      this.addTopping(ToppingName.Onion,ToppingPortion.Full);
      this.addTopping(ToppingName.Jalapeno,ToppingPortion.Full);
      this.addTopping(ToppingName.Tomato,ToppingPortion.Full);
    }

    /** Creates a new Pizza with the given builder pizza's fields
     *
     * @return VeggiePizza                   a new veggie pizza
     */
    public ObservablePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Pizza size is unspecified");
      }
      return new VeggiePizza(this);
    }

    /** Returns the indicated builder pizza
     *
     * @return VeggiePizza            a builder pizza
     */
    @Override
    protected VeggiePizzaBuilder returnBuilder() {
      return this;
    }
  }
}