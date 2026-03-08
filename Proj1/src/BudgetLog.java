import java.util.ArrayList;
import java.time.LocalDate;

public class BudgetLog {
	
	ArrayList<PurchaseLog> purchaseHistory = new ArrayList<>();
	
	public class PurchaseLog {
		
		private String categoryName;
		private double expenseAmt;
		private LocalDate dateOfPurchase;
		private String logMsg;
		
		public PurchaseLog(String categoryName, double expenseAmt, LocalDate dateOfPurchase) {
			this.categoryName = categoryName;
			this.expenseAmt = expenseAmt;
			this.dateOfPurchase = dateOfPurchase;
			
			this.logMsg = "Purchase Type: " + categoryName + ", Money Spent: $" + expenseAmt + ", Purchase Date: " + dateOfPurchase;
		}
		
		public String GetPurchaseType() {
			return categoryName;
		}
		
		public LocalDate GetDateOfPurchase() {
			return dateOfPurchase;
		}
		
		public double GetExpenseAmount() {
			return expenseAmt;
		}
		
		public void PrintLog() {
			System.out.println(logMsg);
		}
	}
	
	public void Log(String categoryName, double expenseAmt, LocalDate dateOfPurchase) {
		PurchaseLog newLog = new PurchaseLog(categoryName, expenseAmt, dateOfPurchase);
		
		purchaseHistory.add(newLog);
	}
	
	public void PrintPurchaseHistory() {
		for (int i = 0; i < purchaseHistory.size(); i++) {
			purchaseHistory.get(i).PrintLog();
		}
	}
	
	
}