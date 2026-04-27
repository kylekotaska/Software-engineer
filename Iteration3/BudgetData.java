public class BudgetData {
	public double income;
	public double savings;
	public double totalExpenses;

	public BudgetData(double income, double savings, double totalExpenses) {
		this.income = income;
		this.savings = savings;
		this.totalExpenses = totalExpenses;
	}
	
	public double getIncome() {
		return income;
	}
	
	public double getSavings() {
		return savings;
	}
	
	public double getTotalExpenses() {
		return totalExpenses;
	}
	
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		BudgetData objData = (BudgetData) obj;
		if (this.income != objData.getIncome()) {
			return false;
		}
		
		if (this.savings != objData.getSavings()) {
			return false;
		}
		
		if (this.totalExpenses != objData.getTotalExpenses()) {
			return false;
		}
		
		return true;
	}
}
