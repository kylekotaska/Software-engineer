import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DbManager {
	private static final String DB_URL = "jdbc:sqlite:budget.db";

	public static class BudgetData {
		public double income;
		public double savings;
		public double totalExpenses;

		public BudgetData(double income, double savings, double totalExpenses) {
			this.income = income;
			this.savings = savings;
			this.totalExpenses = totalExpenses;
		}
	}

	public static class CategoryData {
		public String name;
		public double expenseLimit;
		public double expenseTotal;

		public CategoryData(String name, double expenseLimit, double expenseTotal) {
			this.name = name;
			this.expenseLimit = expenseLimit;
			this.expenseTotal = expenseTotal;
		}
	}

	public static class ExpenseLogData {
		public String categoryName;
		public double expenseAmount;
		public LocalDate expenseDate;

		public ExpenseLogData(String categoryName, double expenseAmount, LocalDate expenseDate) {
			this.categoryName = categoryName;
			this.expenseAmount = expenseAmount;
			this.expenseDate = expenseDate;
		}
	}

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}

	public static void initializeDatabase() {
		String createBudgetTable = """
				CREATE TABLE IF NOT EXISTS budget (
				id INTEGER PRIMARY KEY,
				income REAL NOT NULL,
				savings REAL NOT NULL,
				total_expenses REAL NOT NULL
				);
				""";

		String createCategoryTable = """
				CREATE TABLE IF NOT EXISTS purchase_category (
				id INTEGER PRIMARY KEY AUTOINCREMENT,
				name TEXT NOT NULL UNIQUE,
				expense_limit REAL NOT NULL,
				expense_total REAL NOT NULL
				);
				""";

		String createBudgetLogTable = """
				CREATE TABLE IF NOT EXISTS budget_log (
				id INTEGER PRIMARY KEY AUTOINCREMENT,
				category_name TEXT NOT NULL,
				expense_amount REAL NOT NULL,
				expense_date TEXT NOT NULL
				);
				""";

		try (Connection connection = connect(); Statement stmt = connection.createStatement()) {
			stmt.execute(createBudgetTable);
			stmt.execute(createCategoryTable);
			stmt.execute(createBudgetLogTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void saveBudget(double income, double savings, double totalExpenses) {
		String sql = """
				INSERT INTO budget (id, income, savings, total_expenses)
				VALUES (1, ?, ?, ?)
				ON CONFLICT(id) DO UPDATE SET
					income = excluded.income,
					savings = excluded.savings,
					total_expenses = excluded.total_expenses;
				""";

		try (Connection connection = connect();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setDouble(1, income);
			pstmt.setDouble(2, savings);
			pstmt.setDouble(3, totalExpenses);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void saveCategory(String name, double expenseLimit, double expenseTotal) {
		String sql = """
				INSERT INTO purchase_category (name, expense_limit, expense_total)
				VALUES (?, ?, ?)
				ON CONFLICT(name) DO UPDATE SET
					expense_limit = excluded.expense_limit,
					expense_total = excluded.expense_total;
				""";

		try (Connection connection = connect();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, name);
			pstmt.setDouble(2, expenseLimit);
			pstmt.setDouble(3, expenseTotal);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void saveExpenseLog(String categoryName, double expenseAmount, LocalDate expenseDate) {
		String sql = """
				INSERT INTO budget_log (category_name, expense_amount, expense_date)
				VALUES (?, ?, ?);
				""";

		try (Connection connection = connect();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, categoryName);
			pstmt.setDouble(2, expenseAmount);
			pstmt.setString(3, expenseDate.toString());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static BudgetData loadBudget() {
		String sql = "SELECT income, savings, total_expenses FROM budget WHERE id = 1;";

		try (Connection connection = connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				double income = rs.getDouble("income");
				double savings = rs.getDouble("savings");
				double totalExpenses = rs.getDouble("total_expenses");

				return new BudgetData(income, savings, totalExpenses);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<CategoryData> loadCategories() {
		ArrayList<CategoryData> categories = new ArrayList<>();
		String sql = "SELECT name, expense_limit, expense_total FROM purchase_category ORDER BY id;";

		try (Connection connection = connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				String name = rs.getString("name");
				double limit = rs.getDouble("expense_limit");
				double total = rs.getDouble("expense_total");

				categories.add(new CategoryData(name, limit, total));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public static ArrayList<ExpenseLogData> loadExpenseLogs() {
		ArrayList<ExpenseLogData> logs = new ArrayList<>();
		String sql = "SELECT category_name, expense_amount, expense_date FROM budget_log ORDER BY id;";

		try (Connection connection = connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				String categoryName = rs.getString("category_name");
				double expenseAmount = rs.getDouble("expense_amount");
				LocalDate expenseDate = LocalDate.parse(rs.getString("expense_date"));

				logs.add(new ExpenseLogData(categoryName, expenseAmount, expenseDate));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return logs;
	}
}