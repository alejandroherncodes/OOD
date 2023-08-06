import spreadsheet.*;
import java.util.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpreadSheetTest {
  private SpreadSheet sparseSheet;

  @Before
  public void setup() {
    sparseSheet = new SparseSpreadSheet();
  }
  
  @Test
  public void testIsEmpty() {
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        sparseSheet.set(i, j, 10);
        assertFalse(sparseSheet.isEmpty(i, j));
      }
    }
  }

  @Test
  public void testGetSet() {
    Random rand = new Random(500);
    double[][] expectedSet = new double[100][100];

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        assertEquals(expectedSet[i][j], sparseSheet.get(i, j), 0.01);
      }
    }

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        assertEquals(expectedSet[j][i], sparseSheet.get(j, i), 0.01);
      }
    }

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        double num = rand.nextDouble();
        expectedSet[i][j] = num;
        assertEquals(0.0, sparseSheet.get(i, j), 0.001);
        // Is true
        assertTrue(sparseSheet.isEmpty(i, j));
        // Then set
        sparseSheet.set(i, j, num);
        // Now false
        assertFalse(sparseSheet.isEmpty(i, j));
      }
    }
  }

  @Test
  public void testGetWidthHeight() {

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        assertTrue(sparseSheet.isEmpty(i, j));
      }
    }

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        sparseSheet.set(i, j, 0);
        assertEquals((i + 1), sparseSheet.getHeight());
        if (i == 0) {
          assertEquals((j + 1), sparseSheet.getWidth());
        } else {
          assertEquals(100, sparseSheet.getWidth());
        }
      }
    }

    sparseSheet.set(500, 500, 0);
    assertEquals(501, sparseSheet.getWidth());
    assertEquals(501, sparseSheet.getHeight());

    sparseSheet.set(2000, 2000, 0);
    assertEquals(2001, sparseSheet.getWidth());
    assertEquals(2001, sparseSheet.getHeight());
  }

  @Test
  public void testGetException() {

      // Exception thrown caused by row
      try {
      sparseSheet.set(0, 0, 0);
      sparseSheet.set(0,5, 10);
      sparseSheet.set(0,5, 1);
      sparseSheet.get(10, 0);
      sparseSheet.get(-10, 0);
    } catch (IllegalArgumentException e) {
      System.out.println("Thrown exception:" + e);
    }

    // Exception thrown caused by col
    try {
      sparseSheet.set(0, 0, 0);
      sparseSheet.set(0,10, 5);
      sparseSheet.set(0,5, 1);
      sparseSheet.get(10, 0);
      sparseSheet.get(0, -10);
    } catch (IllegalArgumentException e) {
      System.out.println("Thrown exception:" + e);
    }
  }
}
