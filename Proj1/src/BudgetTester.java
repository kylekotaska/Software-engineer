import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class BudgetTester {

	@Test
	void testIncome() {
		Random rand = new Random();
		
		double income = rand.nextDouble();
		
		Budget budget = new Budget();
		
		budget.SetIncome(income);
		
		assertEquals(income, budget.GetIncome());
		
	}
	
	@Test
	void testSavings() {
		Random rand = new Random();
		
		double savingsAmt = rand.nextDouble();
		
		Budget budget = new Budget();
		
		budget.SetSavings(savingsAmt);
		
		assertEquals(savingsAmt, budget.GetSavings());
	}
	
	@Test
	void testAddCategory() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double foodLimit = rand.nextDouble();
		
		budget.AddExpenseCategory("Food", foodLimit);
		
		PurchaseCategory actualCat = budget.GetExpenseCategory("Food");
		
		PurchaseCategory experimentalCat = new PurchaseCategory("Food", foodLimit);
		
		assertEquals(actualCat, experimentalCat);
	}
	
	@Test
	void testAddCategoryExpense() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double personalLimit = rand.nextDouble();
		
		double personalExpense = rand.nextDouble();
		
		budget.AddExpenseCategory("Personal", personalLimit);
		
		budget.AddExpense("Personal", personalExpense);
		
		double expectedTotal = personalExpense;
		
		PurchaseCategory personalCat = budget.GetExpenseCategory("Personal");
		
		assertEquals(expectedTotal, personalCat.GetExpenseTotal());
	}
	
	@Test
	void testTotalExpense() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double necessitiesLimit = rand.nextDouble();
		double personalLimit = rand.nextDouble();
		
		double necessitiesPurchase = rand.nextDouble();
		double personalPurchase = rand.nextDouble();
		
		budget.AddExpenseCategory("Necessities", necessitiesLimit);
		budget.AddExpenseCategory("Personal", personalLimit);
		
		budget.AddExpense("Personal", personalPurchase);
		budget.AddExpense("Necessities", necessitiesPurchase);
		
		double expectedTotal = personalPurchase + necessitiesPurchase;
		
		assertEquals(expectedTotal, budget.GetExpenseTotal());
		
	}
	
	@Test
	void testCategoryExpenseLimit() {
		Budget budget = new Budget();
		
		Random rand = new Random();
		
		double expected = rand.nextDouble();
		
		budget.AddExpenseCategory("Personal", expected);
		
		PurchaseCategory personalCat = budget.GetExpenseCategory("Personal");
		
		assertEquals(expected, personalCat.GetExpenseLimit());
	}

}
