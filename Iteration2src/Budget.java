import java.time.LocalDate;
import java.util.HashMap;

public class Budget {

	private double income;
	private double savingsAmt;
	private double totalExpenses;
	private BudgetLog budgetLog = new BudgetLog();

	private HashMap<String, PurchaseCategory> purchaseCategories = new HashMap<>();
	private boolean storedData;

	public Budget() {
		DbManager.BudgetData budgetData = DbManager.loadBudget();

		if (budgetData != null) {
			this.income = budgetData.income;
			this.savingsAmt = budgetData.savings;
			this.totalExpenses = budgetData.totalExpenses;
			this.storedData = true;
		}

		for (DbManager.CategoryData categoryData : DbManager.loadCategories()) {
			PurchaseCategory category = new PurchaseCategory(categoryData.name, categoryData.expenseLimit);
			category.setExpenseTotal(categoryData.expenseTotal);
			purchaseCategories.put(categoryData.name, category);
		}

		for (DbManager.ExpenseLogData logData : DbManager.loadExpenseLogs()) {
			budgetLog.log(logData.categoryName, logData.expenseAmount, logData.expenseDate);
		}
	}

	public boolean hasStoredData() {
		return storedData;
	}

	public void setIncome(double income) {
		this.income = income;
		DbManager.saveBudget(this.income, this.savingsAmt, this.totalExpenses);
	}

	public void setSavings(double savingsAmt) {
		this.savingsAmt = savingsAmt;
		DbManager.saveBudget(this.income, this.savingsAmt, this.totalExpenses);
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
		DbManager.saveCategory(categoryName, expenseLimit, newCategory.getExpenseTotal());
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

		DbManager.saveExpenseLog(categoryName, expenseAmt, currentDate);
		DbManager.saveCategory(categoryName, category.getExpenseLimit(), category.getExpenseTotal());
		DbManager.saveBudget(income, savingsAmt, totalExpenses);
	}

	public double getExpenseTotal() {
		return totalExpenses;
	}

	public BudgetLog getBudgetLog() {
		return budgetLog;
	}
}