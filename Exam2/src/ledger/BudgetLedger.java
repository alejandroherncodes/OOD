package ledger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class BudgetLedger extends SimpleLedger {
  private final double budget;
  private double totalExpenses;
  private List<LedgerItem> expenses;
  public BudgetLedger(double budget) {
    this.budget = budget;
    this.totalExpenses = 0;
    this.expenses = new ArrayList<>();
  }

  /** To add new expenses to the ledger with a description and an amount*/
  @Override
  public void addExpense(String description, int dollars, int cents,
                         int date, int month, int year) throws IllegalArgumentException {
    if (description == null || dollars < 0 || cents < 0 || cents > 99) {
      throw new IllegalArgumentException("Invalid expense");
    }
    double expense = dollars + cents / 100.0;

    dollars = (int) expense;
    cents = (int) Math.round((expense - dollars) * 100);
    totalExpenses += expense;
    expenses.add(new LedgerItem(description, ItemType.Expense, dollars, cents, date, month, year));
  }

  /** return the total expenses recorded in the ledger */
  @Override
  public double getTotalExpenses() {
    return totalExpenses;
  }

  /** Return an iterator for all the expenses recorded in the ledger */
  @Override
  public Iterator<LedgerItem> allExpenses() {
    return expenses.iterator();
  }

  /** returns the remaining budget if paul is not over budget, otherwise it returns 0.
   * He can retrieve the remaining budget at any time.*/

  public double getRemainingBudget() {
    if (budget < totalExpenses) {
      return 0;
    } else {
      return budget - totalExpenses;
    }
  }

  /**Returns a string indicating whether paul is under, at, or over budget.
   * Inquire whether he is at, over, or under budget*/
  public String getBudgetStatus() {
    if (totalExpenses > budget) {
      return "Over budget";
    } else if (totalExpenses == budget) {
      return "At budget";
    } else {
      return "Under budget";
    }
  }

  @Override
  public double getTotalIncome() {
    // Not needed for paul's requirements
    return 0;
  }

  @Override
  public void addIncome(String description, int dollars, int cents, int date, int month, int year) throws IllegalArgumentException {
    // Not needed for paul's requirements
  }

  @Override
  public Iterator<LedgerItem> allIncomes() {
    // Not needed for paul's requirements
    return null;
  }
}