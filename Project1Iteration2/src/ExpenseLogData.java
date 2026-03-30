import java.time.LocalDate;

public class ExpenseLogData {
	public String categoryName;
	public double expenseAmount;
	public LocalDate expenseDate;

	public ExpenseLogData(String categoryName, double expenseAmount, LocalDate expenseDate) {
		this.categoryName = categoryName;
		this.expenseAmount = expenseAmount;
		this.expenseDate = expenseDate;
	}
	
	public String getName() {
		return this.categoryName;
	}
	
	public double getExpenseAmount() {
		return this.expenseAmount;
	}
	
	public LocalDate getExpenseDate() {
		return this.expenseDate;
	}
	
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		ExpenseLogData objData = (ExpenseLogData) obj;
		
		if (!this.categoryName.equals(objData.getName())) {
			return false;
		}
		
		if (this.expenseAmount == objData.getExpenseAmount()) {
			return false;
		}
		
		if (this.expenseDate == objData.getExpenseDate()) {
			return false;
		}
		

		return true;
	}
}
