import java.time.LocalDate;
import java.util.HashMap;

public class Budget {
	
	private double income;
	private double savingsAmt;
	private double totalExpenses;
	private BudgetLog budgetLog = new BudgetLog();

	private HashMap<String, PurchaseCategory> purchaseCategories = new HashMap<>();
	
	public void setIncome(double income) {
		this.income = income;
	}
	
	public void setSavings(double savingsAmt) {
		this.savingsAmt = savingsAmt;
	}
	
	public double getIncome() {
		return income;
	}
	
	public double getSavings() {
		return savingsAmt;
	}
	
	public void addExpenseCategory(String categoryName, double expenseLimit) {
		PurchaseCategory newCategory = new PurchaseCategory(categoryName, expenseLimit);
		
		purchaseCategories.put(categoryName, newCategory);
	}
	
	public PurchaseCategory getExpenseCategory(String categoryName) {
		return purchaseCategories.get(categoryName);
	}
	
	public void addExpense(String categoryName, double expenseAmt) {
		PurchaseCategory category = purchaseCategories.get(categoryName);
		
		LocalDate currentDate = LocalDate.now();
		
		budgetLog.log(categoryName, expenseAmt, currentDate);
		
		category.addExpense(expenseAmt);
		totalExpenses += expenseAmt;
	}
	
	public double getExpenseTotal() {
		return totalExpenses;
	}
	
	public BudgetLog getBudgetLog() {
		return budgetLog;
	}
	
}
