public class PurchaseCategory {
	
	private double expenseTotal;
	private double expenseLimit;
	private String categoryName;
	
	
	public PurchaseCategory(String categoryName, double expenseLimit) {
		this.categoryName = categoryName;
		this.expenseLimit = expenseLimit;
	}
	
	public void setExpenseLimit(double expenseLimit) {
		this.expenseLimit = expenseLimit;
	}
	
	public double getExpenseLimit() {
		return expenseLimit;
	}
	
	public void addExpense(double expenseAmt) {
		this.expenseTotal += expenseAmt;
	}
	
	public double getExpenseTotal() {
		return expenseTotal;
	}
	
	public String getName() {
		return categoryName;
	}
	
	
	public boolean equals(Object obj) {
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		PurchaseCategory objCategory = (PurchaseCategory) obj;
		
		if (objCategory.getExpenseLimit() != expenseLimit) {
			return false;
		}
		
		if (objCategory.getExpenseTotal() != expenseTotal) {
			return false;
		}
		
		if (!objCategory.getName().equals(categoryName)) {
			return false;
		}
		
		return true;
	}
}
