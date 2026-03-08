import java.time.LocalDate;
import java.util.HashMap;

public class Budget {
	
	private double income;
	private double savingsAmt;
	private double totalExpenses;
	private BudgetLog budgetLog = new BudgetLog();

	HashMap<String, PurchaseCategory> purchaseCategories = new HashMap<>();
	
	
	
	public void SetIncome(double income) {
		this.income = income;
	}
	
	public void SetSavings(double savingsAmt) {
		this.savingsAmt = savingsAmt;
	}
	
	public double GetIncome() {
		return income;
	}
	
	public double GetSavings() {
		return savingsAmt;
	}
	
	public void AddExpenseCategory(String categoryName, double expenseLimit) {
		PurchaseCategory newCategory = new PurchaseCategory(categoryName, expenseLimit);
		
		purchaseCategories.put(categoryName, newCategory);
	}
	
	public PurchaseCategory GetExpenseCategory(String categoryName) {
		return purchaseCategories.get(categoryName);
	}
	
	public void AddExpense(String categoryName, double expenseAmt) {
		PurchaseCategory category = purchaseCategories.get(categoryName);
		
		LocalDate currentDate = LocalDate.now();
		
		budgetLog.Log(categoryName, expenseAmt, currentDate);
		
		category.AddExpense(expenseAmt);
		totalExpenses += expenseAmt;
	}
	
	public double GetExpenseTotal() {
		return totalExpenses;
	}
	
	public BudgetLog GetBudgetLog() {
		return budgetLog;
	}
	
}