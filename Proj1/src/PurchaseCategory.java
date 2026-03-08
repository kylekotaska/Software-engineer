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
	
	public String GetName() {
		return categoryName;
	}
	
	
	public boolean equals(Object obj) {
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		PurchaseCategory objCategory = (PurchaseCategory) obj;
		
		if (objCategory.GetExpenseLimit() != expenseLimit) {
			return false;
		}
		
		if (objCategory.GetExpenseTotal() != expenseTotal) {
			return false;
		}
		
		if (objCategory.GetName() != categoryName) {
			return false;
		}
		
		return true;
	}
}