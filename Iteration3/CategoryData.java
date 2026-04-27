public class CategoryData {
	public String name;
	public double expenseLimit;
	public double expenseTotal;

	public CategoryData(String name, double expenseLimit, double expenseTotal) {
		this.name = name;
		this.expenseLimit = expenseLimit;
		this.expenseTotal = expenseTotal;
	}
	
	public String getName() {
		return name;
	}
	
	public double getExpenseLimit() {
		return expenseLimit;
	}
	
	public double getExpenseTotal() {
		return expenseTotal;
	}
	
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		CategoryData objData = (CategoryData) obj;
		if (!this.name.equals(objData.getName())) {
			return false;
		}
		
		if (this.expenseLimit != objData.getExpenseLimit()) {
			return false;
		}
		
		if (this.expenseTotal != objData.getExpenseTotal()) {
			return false;
		}
		
		return true;
	}
}