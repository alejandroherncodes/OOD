package ledger;

import java.util.Iterator;

/**
 * This interface extends the Ledger interface to add search functionality.
 */
public interface SearchLedger extends Ledger {
  Iterator<LedgerItem> getExpensesAbove(double amount);
  Iterator<LedgerItem> getExpensesInRange(double minAmount, double maxAmount);
  Iterator<LedgerItem> getIncomesWithDescription(String keyword);
  Iterator<LedgerItem> getItemsInPeriod(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear);
}