import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
	
	

}
