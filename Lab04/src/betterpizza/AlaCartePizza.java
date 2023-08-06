package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its size, crust type, and a HashMap of toppings.
   *
   * @param size     the size of the pizza
   * @param crust    the type of the crust
   * @param toppings a map of all the topping names and portions
   */
  protected AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {

    // Takes in the size
    if (size == null) {
      throw new IllegalStateException("Invalid Parameter: Size is Null");
    } if (crust == null) {
      throw new IllegalStateException("Invalid Parameter: Crust is Null");
    } if (toppings == null) {
      throw new IllegalStateException("Invalid Parameter: Toppings is Null");
    }
    this.crust = crust;
    this.size = size;
    this.toppings = toppings;
  }

  protected AlaCartePizza(AlaCartePizzaBuilder builder) {
    this(builder.size, builder.crust, builder.toppings);
  }

  /**
   * Determines if the specified topping is on this pizza and if so, return its portion
   *
   * @param name the name of the topping
   * @return the portion of this topping on this pizza, or null if the given topping is not
   * on this pizza
   */
  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  /**
   * Get the cost of this pizza
   *
   * @return the cost of this pizza in MM.CC format
   */
  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  public static class AlaCartePizzaBuilder extends PizzaBuilder<AlaCartePizzaBuilder> {

    // Default-case constructor
    public AlaCartePizzaBuilder() {
      super();
      this.toppings = new HashMap<ToppingName, ToppingPortion>();
    }


    /**
     * Create a new pizza using the builder pizza's fields.
     *
     * @return AlaCartePizza                  a new pizza
     */
    @Override
    public AlaCartePizza build() {
      return new AlaCartePizza(this);
    }

    /**
     * Returns the builder pizza
     *
     * @return AlaCartePizza      the given builder pizza
     */
    @Override
    protected AlaCartePizzaBuilder returnBuilder() {
      return this;
    }
  }
}
