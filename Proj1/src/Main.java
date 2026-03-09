public class Main {

    public static void main(String[] args) {

        Budget myBudget = new Budget();

        myBudget.setIncome(3000);
        myBudget.setSavings(500);

        System.out.println("Income: $" + myBudget.getIncome());
        System.out.println("Savings Goal: $" + myBudget.getSavings());
        System.out.println();

        myBudget.addExpenseCategory("Food", 500);
        myBudget.addExpenseCategory("Personal", 300);
        myBudget.addExpenseCategory("Transportation", 200);

        myBudget.addExpense("Food", 45.50);
        myBudget.addExpense("Food", 20.00);
        myBudget.addExpense("Personal", 80.00);
        myBudget.addExpense("Transportation", 37.75);

        System.out.println("Total Expenses: $" + myBudget.getExpenseTotal());
        System.out.println();

        System.out.println("Category Totals:");

        PurchaseCategory food = myBudget.getExpenseCategory("Food");
        System.out.println("Food spent: $" + food.getExpenseTotal());

        PurchaseCategory entertainment = myBudget.getExpenseCategory("Personal");
        System.out.println("Entertainment spent: $" + entertainment.getExpenseTotal());

        PurchaseCategory transport = myBudget.getExpenseCategory("Transportation");
        System.out.println("Transport spent: $" + transport.getExpenseTotal());

        System.out.println();

        System.out.println("Purchase History:");
        myBudget.getBudgetLog().printPurchaseHistory();
    }
}