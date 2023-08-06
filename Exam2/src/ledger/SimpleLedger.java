package ledger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a simple ledger. It records expenses and income
 * in the order in which they were entered.
 */

public class SimpleLedger implements Ledger {
  private List<LedgerItem> items;

  /**
   * Create an empty expense tracker
   */
  public SimpleLedger() {
    items = new LinkedList<LedgerItem>();
  }

  @Override
  public void addExpense(String description, int dollars, int cents, int date, int month, int year) throws IllegalArgumentException {
    LedgerItem item = new LedgerItem(description,ItemType.Expense,dollars,cents,date,month,year);
    items.add(item);
  }

  @Override
  public void addIncome(String description, int dollars, int cents, int date, int month, int year) throws IllegalArgumentException {
    LedgerItem item = new LedgerItem(description,ItemType.Income,dollars,cents,date,month,year);
    items.add(item);
  }

  @Override
  public double getTotalExpenses() {
    double amount = 0.0;

    for (LedgerItem item:items) {
      if (item.getType()==ItemType.Expense) {
        amount += item.getAmount();
      }
    }

    return amount;
  }

  @Override
  public double getTotalIncome() {
    double amount = 0.0;

    for (LedgerItem item: items) {
      if (item.getType()==ItemType.Income) {
        amount += item.getAmount();
      }
    }

    return amount;
  }

  /**
   * This implementation returns all expenses in the order they were added to this ledger.
   * @return the expenses added to this ledger in order
   */
  @Override
  public Iterator<LedgerItem> allExpenses() {
    List<LedgerItem> expenses = new LinkedList<LedgerItem>();
    for (LedgerItem item:this.items) {
      if (item.getType()==ItemType.Expense) {
        expenses.add(item);
      }
    }
    return expenses.iterator();
  }

  /**
   * This implementation returns all incomes in the order they were added to this ledger.
   * @return the incomes added to this ledger in order
   */
  @Override
  public Iterator<LedgerItem> allIncomes() {
    List<LedgerItem> incomes = new LinkedList<LedgerItem>();
    for (LedgerItem item:this.items) {
      if (item.getType()==ItemType.Income) {
        incomes.add(item);
      }
    }
    return incomes.iterator();
  }

  @Override
  public Iterator<LedgerItem> getExpensesAbove(double amount) {
    return this.getExpensesAbove(amount);
  }

  @Override
  public Iterator<LedgerItem> getExpensesInRange(double minAmount, double maxAmount) {
    return this.getExpensesInRange(minAmount, maxAmount);
  }

  @Override
  public Iterator<LedgerItem> getIncomesWithDescription(String keyword) {
    return this.getIncomesWithDescription(keyword) ;
  }

  @Override
  public Iterator<LedgerItem> getItemsInPeriod(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
    return this.getItemsInPeriod(startDay, startMonth,
            startYear, endDay, endMonth, endYear);
  }
}
