import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;

public class BetterPizzaTest {
  private ObservablePizza alacarte;
  private ObservablePizza cheese;
  private ObservablePizza halfJalapeno;

  @Before
  public void setup() {
    alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();

    cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    halfJalapeno = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.RightHalf)
            .build();
  }

  @Test
  public void testCost() {
    assertEquals(8.25, alacarte.cost(), 0.01);
    assertEquals(9, cheese.cost(), 0.01);
    assertEquals(9.25, halfJalapeno.cost(), 0.01);
  }

  @Test
  public void testBetterAlacarte() {
    ObservablePizza alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.LeftHalf)
            .addTopping(ToppingName.Onion, ToppingPortion.RightHalf)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.RightHalf)
            .build();
  }

  @Test
  public void testBetterCheese() {
    ObservablePizza cheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();
  }

  @Test
  public void testBetterVeggie() {
    ObservablePizza veggiePizza = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Medium)
            .build();
  }

  @Test
  public void testBetterSmallThinPizza() {
    ObservablePizza cheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Small)
            .build();
  }

  @Test
  public void createHalfCheesePizza() {
    ObservablePizza halfCheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Large)
            .rightHalfCheese()
            .noOlive()
            .noJalapeno()
            .noOnion()
            .build();

    assertNotEquals(halfCheesePizza.hasTopping(ToppingName.Cheese),
            cheese.hasTopping(ToppingName.Cheese));
    System.out.println(halfCheesePizza.hasTopping(ToppingName.Cheese));
  }

  @Test
  public void createLeftHalfPizza() {
    ObservablePizza halfCheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Large)
            .leftHalfCheese()
            .leftHalfOlive()
            .leftHalfPepper()
            .leftHalfTomato()
            .leftHalfJalapeno()
            .leftHalfOnion()
            .leftHalfSauce()
            .build();

    assertNotEquals(halfCheesePizza.hasTopping(ToppingName.Cheese),
            cheese.hasTopping(ToppingName.Cheese));
    System.out.println(halfCheesePizza.hasTopping(ToppingName.Cheese));
  }

  @Test
  public void createRightHalfPizza() {
    ObservablePizza halfCheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Large)
            .rightHalfCheese()
            .rightHalfOlive()
            .rightHalfPepper()
            .rightHalfTomato()
            .rightHalfJalapeno()
            .rightHalfOnion()
            .rightHalfSauce()
            .build();

    assertNotEquals(halfCheesePizza.hasTopping(ToppingName.Cheese),
            cheese.hasTopping(ToppingName.Cheese));
    System.out.println(halfCheesePizza.hasTopping(ToppingName.Cheese));
  }

  @Test
  public void testHasToppings() {
    ObservablePizza pizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Large)
            .rightHalfCheese()
            .build();

    ObservablePizza veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .rightHalfJalapeno()
            .leftHalfOlive()
            .build();

    ObservablePizza pizza2 = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Large)
            .leftHalfCheese()
            .build();


    assertEquals(ToppingPortion.RightHalf, pizza.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.RightHalf, veggie.hasTopping(ToppingName.Jalapeno));
    assertEquals(ToppingPortion.LeftHalf, veggie.hasTopping(ToppingName.BlackOlive));
    assertEquals(ToppingPortion.LeftHalf, pizza2.hasTopping(ToppingName.Cheese));
  }

  @Test
  public void createNoTopPizza() {
    ObservablePizza halfCheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Large)
            .noCheese()
            .noOlive()
            .noPepper()
            .noTomato()
            .noJalapeno()
            .noOnion()
            .noSauce()
            .build();

    assertNotEquals(halfCheesePizza.hasTopping(ToppingName.Cheese),
            cheese.hasTopping(ToppingName.Cheese));
    System.out.println(halfCheesePizza.hasTopping(ToppingName.Cheese));
  }

  @Test
  public void createVegHalfPizza() {
    ObservablePizza halfCheesePizza = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Large)
            .leftHalfCheese()
            .leftHalfOlive()
            .leftHalfPepper()
            .leftHalfTomato()
            .leftHalfJalapeno()
            .leftHalfOnion()
            .leftHalfSauce()
            .build();

    assertNotEquals(halfCheesePizza.hasTopping(ToppingName.Cheese),
            cheese.hasTopping(ToppingName.Cheese));
    System.out.println(halfCheesePizza.hasTopping(ToppingName.Cheese));
  }

  @Test
  public void testChangeTopping() {
    ObservablePizza cheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Small)
            .build();

    assertEquals(cheesePizza.hasTopping(ToppingName.Cheese), ToppingPortion.Full);

    ObservablePizza halfCheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .addTopping(ToppingName.BlackOlive, ToppingPortion.LeftHalf)
            .leftHalfCheese()
            .build();

    assertEquals(halfCheesePizza.hasTopping(ToppingName.Cheese), ToppingPortion.LeftHalf);
    assertEquals(halfCheesePizza.hasTopping(ToppingName.BlackOlive), ToppingPortion.LeftHalf);

    ObservablePizza noCheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noCheese()
            .build();

    assertEquals(noCheesePizza.hasTopping(ToppingName.Cheese), null);
  }

  @Test
  public void testExceptions() {
    try {
      ObservablePizza noCrust = new AlaCartePizza.AlaCartePizzaBuilder()
              .size(Size.Large)
              .addTopping(ToppingName.Cheese, ToppingPortion.Full)
              .build();     // No crust specified
    } catch (IllegalStateException e) {
      System.out.println("IllegalStateException:" + e);
    }
    try {
      ObservablePizza noSize = new AlaCartePizza.AlaCartePizzaBuilder()
              .crust(Crust.Stuffed)
              .addTopping(ToppingName.Onion, ToppingPortion.RightHalf)
              .build();       // No size specified
    } catch (IllegalStateException e) {
      System.out.println("IllegalStateException" + e);
    }
  }

  @Test
  public void testBuildIllegalAlaCarte() {
    try {
      ObservablePizza alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
              .crust(Crust.Classic)
              .build();
    } catch (IllegalStateException e) {
      System.out.println("IllegalStateException: " + e);
    }
  }

  @Test
  public void testCheeseBuildIllegalSize() {
    try {
      ObservablePizza cheese = new CheesePizza.CheesePizzaBuilder()
              .crust(Crust.Classic)
              .build();
    } catch (IllegalStateException e) {
      System.out.println("IllegalStateException" + e);
    }
  }

  @Test
  public void testVeggieBuildIllegal() {
    try {
      ObservablePizza veggie = new VeggiePizza.VeggiePizzaBuilder()
              .size(Size.Large)
              .build();
    } catch (IllegalStateException e) {
      System.out.println("VeggiePizzaBuilder:" + e);
    }
  }
}