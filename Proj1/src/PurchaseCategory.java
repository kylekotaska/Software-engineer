public class PurchaseCategory {
	
	private double expenseTotal;
	private double expenseLimit;
	private String categoryName;
	
	
	public PurchaseCategory(String categoryName, double expenseLimit) {
		this.categoryName = categoryName;
		this.expenseLimit = expenseLimit;
	}
	
	public void SetExpenseLimit(double expenseLimit) {
		this.expenseLimit = expenseLimit;
	}
	
	public double GetExpenseLimit() {
		return expenseLimit;
	}
	
	public void AddExpense(double expenseAmt) {
		this.expenseTotal += expenseAmt;
	}
	
	public double GetExpenseTotal() {
		return expenseTotal;
	}
}