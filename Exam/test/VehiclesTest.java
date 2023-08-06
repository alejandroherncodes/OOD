import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VehiclesTest {
  private AVehicle newCar;
  private AVehicle new2Car;
  private AVehicle oldCar;
  private AVehicle newConvertible;
  private AVehicle oldConvertible;
  private AVehicle newBoat;
  private AVehicle newBigBoat;
  private AVehicle newBoat2;
  private AVehicle newBoat3;
  private AVehicle newBoat4;
  private AVehicle newBoat5;
  private AVehicle newAircraft;
  private AVehicle newAirplane;
  private AVehicle newAirplane2;
  private AVehicle newHelicopter;
  private AVehicle newHelicopter2;
  private MakeModel greatYamaha = new MakeModel("Gr8White",
          "Yamaha");

  @Before
  public void setup() {
    // No bump, no discounts
    newCar = new NewCar("ABC123", 2023,
            new MakeModel("Toyota", "Camry"), 25000.0f,
            Car.BodyType.Sedan, 100);

    // Age Discount
    new2Car = new NewCar("ABC123", 2015,
            new MakeModel("Toyota", "Camry"), 25000.0f,
            Car.BodyType.Sedan, 100);

    // Age Discount, Mileage Discount, Not-Certified Pre Owned Discount
    oldCar = new UsedCar("ABC123", 2015,
            new MakeModel("Toyota", "Camry"), 20000.0f,
            Car.BodyType.SUV, 500000, 6,
            1, false);

    // Rare Bump, Convertible Bump
    newConvertible = new NewCar("ABC123", 2023,
            new MakeModel("Toyota", "Camry"), 25000.0f,
            Car.BodyType.Convertible, 1);

    //  Age Discount, Convertible Bump, Accidents Discount
    oldConvertible = new UsedCar("ABC123", 2015,
            new MakeModel("Toyota", "Camry"), 20000.0f,
            Car.BodyType.Convertible, 25000, 4,
            3, true);

    // No Age Discount, SailPower Bump
    newBoat = new Boat("B04T", 2023, greatYamaha, 100000.0f,
            20.0f, 6, true, Vessel.PropulsionType.SailPower,
            Vessel.BoatType.FishingBoats);

    // No Age Discount, SailPower Bump, Passenger Bump
    newBigBoat = new Boat("B04T", 2023, greatYamaha, 100000.0f,
            20.0f, 10, true, Vessel.PropulsionType.SailPower,
            Vessel.BoatType.FishingBoats);

    // No Age Discount, SailPower Bump, Non-Trailable Discount
    newBoat2 = new Boat("B04T", 2023, greatYamaha, 100000.0f,
            20.0f, 6, false, Vessel.PropulsionType.SailPower,
            Vessel.BoatType.FishingBoats);

    // No Age Discount, SailPower Bump, Non-Trailable Discount, LengthNPass Discount
    newBoat3 = new Boat("B04T", 2023, greatYamaha, 100000.0f,
            20.0f, 4, false, Vessel.PropulsionType.SailPower,
            Vessel.BoatType.FishingBoats);

    // No Age Discount, SailPower Bump, LengthNPass Discount
    newBoat4 = new Boat("B04T", 2023, greatYamaha, 100000.0f,
            20.0f, 4, true, Vessel.PropulsionType.SailPower,
            Vessel.BoatType.FishingBoats);

    // Age Discount, LengthNPass Discount
    newBoat5 = new Boat("B04T", 2015, greatYamaha, 100000.0f,
            20.0f, 4, true,
            Vessel.PropulsionType.OutboardEngine, Vessel.BoatType.FishingBoats);

    // A Few Flights Bump
    newAircraft = new Aircraft("B04T", 2023,
            greatYamaha, 100000.0f, 99);

    // Airplane Bump
    newAirplane = new Airplane("B04T", 2015,
            greatYamaha, 100000.0f, 2000);

    // Airplane Bump And A Few Flights Bump
    newAirplane2 = new Airplane("B04T", 2015,
            greatYamaha, 100000.0f, 99);

    // A Few Flights Bump
    newHelicopter = new Helicopter("B04T", 2015,
            greatYamaha, 100000.0f, 99);

    // Lots of Flights Discount
    newHelicopter2 = new Helicopter("B04T", 2015,
            greatYamaha, 11.0f, 2000);
  }

  @Test
  public void testCarEstimatePrice() {
    // No bump, no discounts
    assertEquals(25000.0, newCar.estimatePrice(), 0.0);
    assertEquals("ABC123", newCar.getId());
    assertEquals(25000, newCar.getMsrp(), 0.0);
    assertEquals(2023, newCar.getManufacturingYear(), 0.0);

    // Age Discount
    assertEquals(24998.4, new2Car.estimatePrice(), 0.1);

    // Age Discount, Mileage Discount, Not-Certified Pre Owned Discount
    assertEquals(11999.0, oldCar.estimatePrice(), 0.1);

    // Rare Bump, Convertible Bump
    assertEquals(48000.004f, newConvertible.estimatePrice(), 0);

    // Age Discount, Convertible Bump, Accidents Discount
    assertEquals(20798.3, oldConvertible.estimatePrice(), 0.1);
  }

  @Test
  public void testNotCarEstimatePrice() {
    // No Age Discount, SailPower Bump
    assertEquals(125000, newBoat.estimatePrice(), 0.0);
    assertEquals(greatYamaha, newBoat.getMakeModel());
    assertEquals(new MakeModel("Gr8White",
            "Yamaha").getMake(), "Gr8White");
    assertEquals(new MakeModel("Gr8White",
            "Yamaha").getModel(), "Yamaha");

    // No Age Discount, SailPower Bump, Passenger Bump
    assertEquals(162500, newBigBoat.estimatePrice(), 0.0);

    // No Age Discount, SailPower Bump, Non-Trailable Discount
    assertEquals(112500, newBoat2.estimatePrice(), 0.0);

    // No Age Discount, SailPower Bump, Non-Trailable Discount, LengthNPass Discount
    assertEquals(84375.0, newBoat3.estimatePrice(), 0.0);

    // No Age Discount, SailPower Bump, LengthNPass Discount
    assertEquals(93750, newBoat4.estimatePrice(), 0.0);

    // Age Discount, LengthNPass Discount
    assertEquals(74998.8, newBoat5.estimatePrice(), 0.1);

    // A Few Flights Bump
    assertEquals(110000, newAircraft.estimatePrice(), 0.0);

    // Airplane Bump
    assertEquals(154000, newAirplane.estimatePrice(), 0.01);

    // Airplane Bump and Few Flights Bump
    assertEquals(200200.0, newAirplane2.estimatePrice(), 0.01);

    // A Few Flights Bump
    assertEquals(143000, newHelicopter.estimatePrice(), 0.1);

    // Lots of Flights Discount
    assertEquals(9.3, newHelicopter2.estimatePrice(), 0.1);
  }

  @Test
  public void testExceptions() {
    try {
      Car exCar = new NewCar("ABC123", 2023,
              new MakeModel("Toyota", "Camry"), 25000.0f,
              Car.BodyType.Sedan, -1);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Car exCar2 = new UsedCar("ABC123", 2015,
              new MakeModel("Toyota", "Camry"), 20000.0f,
              Car.BodyType.Convertible, -1, 4,
              3, true);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Car exCar3 = new UsedCar("ABC123", 2015,
              new MakeModel("Toyota", "Camry"), 20000.0f,
              Car.BodyType.Convertible, -1, 0,
              3, true);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Car exCar4 = new UsedCar("ABC123", 2015,
              new MakeModel("Toyota", "Camry"), 20000.0f,
              Car.BodyType.Convertible, 1, 0,
              3, true);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Car exCar5 = new UsedCar("ABC123", 2015,
              new MakeModel("Toyota", "Camry"), 20000.0f,
              Car.BodyType.Convertible, 1, 1,
              -1, true);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Vessel exVessel = new Vessel("ABC123", 2023, greatYamaha, 5.0f,
              1.0f, -1);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Vessel exVessel = new Vessel("ABC123", 2023, greatYamaha, 5.0f,
              0.0f, 1);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Vessel exVessel2 = new Vessel("ABC123", 2025, greatYamaha, 5.0f,
              0.0f, 1);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Vessel exVessel2 = new Vessel("ABC123", 2023, greatYamaha, -1.0f,
              1.0f, 1);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      Aircraft exAir = new Aircraft("ABC123", 2023, greatYamaha,
              1, -1);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }
}

