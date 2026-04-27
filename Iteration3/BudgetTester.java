import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

class BudgetTester {

	@Test
	void testIncome() {
		Random rand = new Random();
		
		double income = rand.nextDouble();
		
		Budget budget = new Budget();
		
		budget.setIncome(income);
		
		assertEquals(income, budget.getIncome());
		
	}
	
	@Test
	void testSavings() {
		Random rand = new Random();
		
		double savingsAmt = rand.nextDouble();
		
		Budget budget = new Budget();
		
		budget.setSavings(savingsAmt);
		
		assertEquals(savingsAmt, budget.getSavings());
	}
	
	@Test
	void testAddCategory() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double foodLimit = rand.nextDouble();
		
		budget.addExpenseCategory("Food", foodLimit);
		
		PurchaseCategory actualCat = budget.getExpenseCategory("Food");
		
		PurchaseCategory experimentalCat = new PurchaseCategory("Food", foodLimit);
		
		assertEquals(actualCat, experimentalCat);
	}
	
	@Test
	void testAddCategoryExpense() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double personalLimit = rand.nextDouble();
		
		double personalExpense = rand.nextDouble();
		
		budget.addExpenseCategory("Personal", personalLimit);
		
		budget.addExpense("Personal", personalExpense);
		
		double expectedTotal = personalExpense;
		
		PurchaseCategory personalCat = budget.getExpenseCategory("Personal");
		
		assertEquals(expectedTotal, personalCat.getExpenseTotal());
	}
	
	@Test
	void testTotalExpense() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double necessitiesLimit = rand.nextDouble();
		double personalLimit = rand.nextDouble();
		
		double necessitiesPurchase = rand.nextDouble();
		double personalPurchase = rand.nextDouble();
		
		budget.addExpenseCategory("Necessities", necessitiesLimit);
		budget.addExpenseCategory("Personal", personalLimit);
		
		budget.addExpense("Personal", personalPurchase);
		budget.addExpense("Necessities", necessitiesPurchase);
		
		double expectedTotal = personalPurchase + necessitiesPurchase;
		
		assertEquals(expectedTotal, budget.getExpenseTotal());
		
	}
	
	@Test
	void testCategoryExpenseLimit() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double expected = rand.nextDouble();
		
		budget.addExpenseCategory("Personal", expected);
		
		PurchaseCategory personalCat = budget.getExpenseCategory("Personal");
		
		assertEquals(expected, personalCat.getExpenseLimit());
	}
	
	@Test
	void testLog() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double foodLimit = rand.nextDouble();
		double foodExpense = rand.nextDouble();
		double foodExpense2= rand.nextDouble();
		
		budget.addExpenseCategory("Food", foodLimit);
		budget.addExpense("Food", foodExpense);
		budget.addExpense("Food", foodExpense2);
		
		BudgetLog log = budget.getBudgetLog();
		
		log.printPurchaseHistory();
	}
	
	@Test
	void testLogInfo() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		LocalDate currentDate = LocalDate.now();
		
		double foodLimit = rand.nextDouble();
		
		double foodExpense = rand.nextDouble();
		
		budget.addExpenseCategory("Food", foodLimit);
		budget.addExpense("Food", foodExpense);
		
		BudgetLog log = budget.getBudgetLog();
		
		LocalDate actualDate = log.purchaseHistory.get(0).getDateOfPurchase();
		double actualExpense = log.purchaseHistory.get(0).getExpenseAmount();
		String actualCategory = log.purchaseHistory.get(0).getPurchaseType();
		
		assertEquals(currentDate, actualDate);
		assertEquals(foodExpense, actualExpense);
		assertEquals("Food", actualCategory);
	}
	
	
	@Test
	void testDatabase() {
		
		
		Budget budget = new Budget();
		
		DbManager.initializeDatabase();
		
		double savings = Math.random();
		double income = Math.random();
		double totalExpenses = Math.random();
		
		double expenseLimit = Math.random();
		double expenseTotal = Math.random();
		
		double expenseAmount = Math.random();
		
		BudgetData expectedBudgetData = new BudgetData(income, savings, totalExpenses);
		
		DbManager.saveBudget(income, savings, totalExpenses);
		
		BudgetData actualBudgetData = DbManager.loadBudget();
		
		
		
		CategoryData expectedCategoryData = new CategoryData("Food", expenseLimit, expenseTotal);
		
		DbManager.saveCategory("Food", expenseLimit, expenseTotal);
		
		ArrayList<CategoryData> categories = DbManager.loadCategories();
		
		CategoryData actualCategoryData = categories.get(0);
		
		
		
		LocalDate currentDate = LocalDate.now();
		
		ExpenseLogData expectedExpenseLogData = new ExpenseLogData("Food", expenseAmount, currentDate);
		
		ArrayList<ExpenseLogData> logDataArray = DbManager.loadExpenseLogs();
		
		ExpenseLogData actualExpenseLogData = logDataArray.get(0);
		
		assertEquals(expectedBudgetData, actualBudgetData);
		assertEquals(expectedCategoryData, actualCategoryData);
		assertEquals(expectedExpenseLogData, actualExpenseLogData);
		
	}
	

}