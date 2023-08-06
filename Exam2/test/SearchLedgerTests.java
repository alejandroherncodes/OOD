import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

import ledger.LedgerItem;
import ledger.SearchLedgerImpl;

public class SearchLedgerTests {
  @Test
  public void testLedger() {
    // Create ledger A and B
    SearchLedgerImpl ledgerA = new SearchLedgerImpl();
    SearchLedgerImpl ledgerB = new SearchLedgerImpl();

    // Add incomes and expenses to A
    ledgerA.addIncome("Salary", 6000, 0, 1, 1, 2021);
    ledgerA.addIncome("Bonus", 2000, 0, 2, 1, 2021);
    ledgerA.addExpense("Travel to Paris", 1000, 0, 3, 1, 2021);
    ledgerA.addExpense("Dinner with client", 500, 0, 4, 1, 2021);
    ledgerA.addExpense("Travel to London", 800, 0, 5, 1, 2021);

    // Verify that A can report all expenses, all incomes and their correct totals
    assertEquals(2300.0, ledgerA.getTotalExpenses(), 0.0);
    assertEquals(8000.0, ledgerA.getTotalIncome(), 0.0);
    Iterator<LedgerItem> expensesA = ledgerA.allExpenses();
    Iterator<LedgerItem> incomesA = ledgerA.allIncomes();
    assertEquals("Travel to Paris", expensesA.next().getDescription());
    assertEquals("Dinner with client", expensesA.next().getDescription());
    assertEquals("Travel to London", expensesA.next().getDescription());
    assertEquals("Salary", incomesA.next().getDescription());
    assertEquals("Bonus", incomesA.next().getDescription());

    // Add identical items to B as in A
    ledgerB.addIncome("Salary", 6000, 0, 1, 1, 2021);
    ledgerB.addIncome("Bonus", 2000, 0, 2, 1, 2021);
    ledgerB.addExpense("Travel to Paris", 1000, 0, 3, 1, 2021);
    ledgerB.addExpense("Dinner with client", 500, 0, 4, 1, 2021);
    ledgerB.addExpense("Travel to London", 800, 0, 5, 1, 2021);

    // Verify that B can correctly find only those expenses that have the word "travel" in their description
    Iterator<LedgerItem> travelExpensesB = ledgerB.getIncomesWithDescription("Bonus");
    assertEquals("Bonus", travelExpensesB.next().getDescription());

    // Verify that B can correctly find the total of all incomes that are above $5000
    Iterator<LedgerItem> totalExpensesAbove5000 = ledgerB.getExpensesAbove(5000);
    assertEquals(8000.0, ledgerB.getTotalIncome(), 0.0);

    // Verify that it is possible for B to correctly find the total of all expenses, and that it matches what A returned for the same query
    double totalExpensesB = ledgerB.getTotalExpenses();
    assertEquals(2300.0, totalExpensesB, 0.0);

    // Add another income in B worth $10000
    ledgerB.addIncome("Stocks", 10000, 0, 6, 1, 2021);

    // Verify that B can still correctly find the total of all incomes that are above $5000
    totalExpensesAbove5000 = ledgerB.getExpensesAbove(5000);
    assertEquals(18000.0, ledgerB.getTotalIncome(), 0.0);

  }
}