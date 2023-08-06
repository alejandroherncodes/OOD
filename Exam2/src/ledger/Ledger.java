package ledger;

import java.util.Iterator;

/**
 * This interface represents all the operations of a
 * personal ledger. A ledger is like an account book, that can be
 * used to enter income and expenses.
 */
public interface Ledger {
  /**
   * Add a new expense to this ledger.
   * @param description the description of the expense
   * @param dollars the amount of whole dollars in this expense
   * @param cents the amount of whole cents in this expense
   * @param date the date on which this expense occurred
   * @param month the month in which this expense occurred
   * @param year the year in which this expense occurred
   * @throws IllegalArgumentException if the description is null, the amounts are invalid
   */
  void addExpense(String description,int dollars,int cents,int date,int month,int year) throws IllegalArgumentException;

  /**
   * Add a new income to this ledger.
   * @param description the description of the income
   * @param dollars the amount of whole dollars in this income
   * @param cents the amount of whole cents in this income
   * @param date the date on which this income was earned
   * @param month the month in which this income was earned
   * @param year the year in which this income was earned
   * @throws IllegalArgumentException if the description is null, the amounts are invalid
   */
  void addIncome(String description,int dollars,int cents,int date,int month,int year) throws IllegalArgumentException;

  /**
   * Get the sum total of all expenses incurred so far in this ledger.
   * @return the total amount spent thus far, in DDD.CC format
   */
  double getTotalExpenses();

  /**
   * Get the sum total of all income entered into this ledger so far.
   * @return the total income, in DDD.CC format
   */
  double getTotalIncome();

  /**
   * Get all expenses in this ledger so that they can be iterated upon. The order of
   * these expenses is dependent on the implementation.
   * @return an iterator for all ledger items that are expenses
   */
  Iterator<LedgerItem> allExpenses();

  /**
   * Get all incomes in this ledger so that they can be iterated upon. The order of
   * these incomes is dependent on the implementation.
   * @return an iterator for all ledger items that are incomes
   */

  Iterator<LedgerItem> allIncomes();

  Iterator<LedgerItem> getExpensesAbove(double amount);
  Iterator<LedgerItem> getExpensesInRange(double minAmount, double maxAmount);
  Iterator<LedgerItem> getIncomesWithDescription(String keyword);
  Iterator<LedgerItem> getItemsInPeriod(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear);
}


