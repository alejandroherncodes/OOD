package ledger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * This class represents a simple ledger. It records expenses and income
 * in the order in which they were entered.
 */
public class SearchLedgerImpl implements SearchLedger {
  private List<LedgerItem> items;

  /**
   * Create an empty expense tracker
   */
  public SearchLedgerImpl() {
    items = new ArrayList<>();
  }

  // add an expense
  @Override
  public void addExpense(String description, int dollars, int cents, int date, int month, int year) throws IllegalArgumentException {
    LedgerItem item = new LedgerItem(description, ItemType.Expense, dollars, cents, date, month, year);
    items.add(item);
  }

  // adds an income
  @Override
  public void addIncome(String description, int dollars, int cents, int date, int month, int year) throws IllegalArgumentException {
    LedgerItem item = new LedgerItem(description, ItemType.Income, dollars, cents, date, month, year);
    items.add(item);
  }

  // Returns a total of expenses
  @Override
  public double getTotalExpenses() {
    double amount = 0.0;
    for (LedgerItem item : items) {
      if (item.getType() == ItemType.Expense) {
        amount += item.getAmount();
      }
    }
    return amount;
  }

  @Override
  public double getTotalIncome() {
    double amount = 0.0;
    for (LedgerItem item : items) {
      if (item.getType() == ItemType.Income) {
        amount += item.getAmount();
      }
    }
    return amount;
  }

  // Returns this expense
  @Override
  public Iterator<LedgerItem> allExpenses() {
    List<LedgerItem> expenses = new ArrayList<>();
    for (LedgerItem item : items) {
      if (item.getType() == ItemType.Expense) {
        expenses.add(item);
      }
    }
    return expenses.iterator();
  }

  // Returns this income
  @Override
  public Iterator<LedgerItem> allIncomes() {
    List<LedgerItem> incomes = new ArrayList<>();
    for (LedgerItem item : items) {
      if (item.getType() == ItemType.Income) {
        incomes.add(item);
      }
    }
    return incomes.iterator();
  }

  @Override
  public Iterator<LedgerItem> getExpensesAbove(double amount) {
    List<LedgerItem> expensesAbove = new ArrayList<>();
    for (LedgerItem item : items) {
      if (item.getType() == ItemType.Expense && item.getAmount() > amount) {
        expensesAbove.add(item);
      }
    }
    return expensesAbove.iterator();
  }

  // Returns an iterator of all expenses within a certain range of amounts
  @Override
  public Iterator<LedgerItem> getExpensesInRange(double minAmount, double maxAmount) {
    List<LedgerItem> expenses = new ArrayList<>();
    for (LedgerItem item : items) {
      if (item.getType() == ItemType.Expense && item.getAmount() >= minAmount && item.getAmount() <= maxAmount) {
        expenses.add(item);
      }
    }
    return expenses.iterator();
  }
  // Returns an iterator of all incomes with a description that includes a certain string
  @Override
  public Iterator<LedgerItem> getIncomesWithDescription(String description) {
    List<LedgerItem> incomes = new ArrayList<>();
    for (LedgerItem item : items) {
      if (item.getType() == ItemType.Income && item.getDescription().contains(description)) {
        incomes.add(item);
      }
    }
    return incomes.iterator();
  }

  // Returns an iterator of all items within a certain period
  @Override
  public Iterator<LedgerItem> getItemsInPeriod(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
    List<LedgerItem> itemsInPeriod = new ArrayList<>();

    LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
    LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

    for (LedgerItem item : items) {
      LocalDate itemDate = LocalDate.of(item.getYear(), item.getMonth(), item.getDate());
      if (itemDate.isEqual(startDate) || itemDate.isEqual(endDate) || (itemDate.isAfter(startDate) && itemDate.isBefore(endDate))) {
        itemsInPeriod.add(item);
      }
    }

    return itemsInPeriod.iterator();
  }


}
