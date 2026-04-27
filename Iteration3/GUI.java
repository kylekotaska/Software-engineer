import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class GUI {
	
	JFrame mainFrame;
    HashMap<String, ChartPanel> chartPanels = new HashMap<>();
    private DefaultTableModel tableModel;
    private JTable budgetLogTable;
    private JLabel budgetStatusLabel;
	
	public void initGui(Budget budget) {
		if (!budget.hasStoredData()) {
			JFrame initFrame = new JFrame();
			initFrame.setTitle("Initialize Personal Budgeting App");
			initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			initFrame.setResizable(true);
			initFrame.setSize(1000,600);
			ImageIcon initImage = new ImageIcon("MoneyIcon.png");
			initFrame.setIconImage(initImage.getImage());

			initFrame.getContentPane().setBackground(new Color(81,126,166));
			initFrame.setLayout(new GridBagLayout());

			GridBagConstraints igbc = new GridBagConstraints();
			igbc.insets = new Insets(5,5,5,5);
			igbc.anchor = GridBagConstraints.CENTER;
			igbc.gridy = 0;

			igbc.gridx = 0;
			JLabel initLabel = new JLabel("Enter an Income Amount: $");
			initFrame.add(initLabel, igbc);

			igbc.gridx = 1;
			JTextField initTextField = new JTextField(20);
			Dimension initFieldSize = new Dimension(120,25); // Text field should have a width of 120 pixels and a height of 25 pixels
			initTextField.setPreferredSize(initFieldSize);
			initTextField.setMinimumSize(initFieldSize); // Prevent the size of the text field from changing based on window size
			initFrame.add(initTextField, igbc); // Add text field in second column using igbc

			igbc.gridx = 2;
			igbc.insets = new Insets(5,40,5,5);
			JButton initButton = new JButton("Enter");
			initFrame.add(initButton, igbc);

			initButton.addActionListener(a -> {
				String initText = initTextField.getText().trim();

				if (initText.isEmpty()) {
					JOptionPane.showMessageDialog(initFrame, "Please enter a valid amount of type double."); // If income amount is not a double, cancel
					return;
				}

				try {
					Double initIncomeAmt = Double.parseDouble(initText);
					budget.setIncome(initIncomeAmt);
					JOptionPane.showMessageDialog(initFrame, "Income Set!");
					initFrame.dispose();
					this.mainGui(budget);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(initFrame, "Please enter a valid amount of type double."); // If expense amount is not a double, cancel
				}
			});
			initFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Application will open fullscreen
			initFrame.setVisible(true);
		} else {
			this.mainGui(budget);
		}
	}
	
	public void updateChart(PurchaseCategory category) {
		
		String categoryName = category.getName();
		double currentValue = category.getExpenseTotal();
		double maxValue = category.getExpenseLimit();
		
		double percentValue = (double) currentValue / maxValue * 100;
		
		ChartPanel chartPanel = chartPanels.get(categoryName);
		JFreeChart chart = chartPanel.getChart();
		PiePlot plot = (PiePlot) chart.getPlot();
		
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("Spent", percentValue);
		dataSet.setValue("Unspent", 100 - percentValue);
		
		plot.setDataset(dataSet);
		
		Color spentColor = null;
		
		if (percentValue < 25) {
			spentColor = new Color(45, 247, 78);
		} else if (percentValue < 50) {
			spentColor = new Color(173, 247, 45);
		} else if (percentValue < 75) {
			spentColor = new Color(247, 220, 45);
		} else {
			spentColor = new Color(247, 45, 45);
		}
			
		plot.setSectionPaint("Unspent", new Color(0, 0, 0));
		plot.setSectionPaint("Spent", spentColor);
		
		chart.setTitle(String.format("%s: [$%.2f / $%.2f]", categoryName, currentValue, maxValue));
	}
	
	public void addCategoryDataChart(JPanel mainPanel, PurchaseCategory category) {
		
		double currentValue = category.getExpenseTotal();
		double maxValue = category.getExpenseLimit();
		String categoryName = category.getName();
		
		double percentValue = currentValue / maxValue * 100;
		
		String title = String.format("%s: [$%.2f / $%.2f]", categoryName, currentValue, maxValue);
		
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("Spent", percentValue);
		dataSet.setValue("Unspent", 100 - percentValue);
		JFreeChart chart = ChartFactory.createRingChart(title, dataSet, false, false, false);
		PiePlot plot = (PiePlot) chart.getPlot();

		
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineVisible(false);       
		plot.setShadowPaint(null); 
		
		
		plot.setSectionOutlinesVisible(false); 
		plot.setInteriorGap(0.04);           
		plot.setCircular(true);               

		Color spentColor = null;
		
		if (percentValue < 25) {
			spentColor = new Color(45, 247, 78);
		} else if (percentValue < 50) {
			spentColor = new Color(173, 247, 45);
		} else if (percentValue < 75) {
			spentColor = new Color(247, 220, 45);
		} else {
			spentColor = new Color(247, 45, 45);
		}
			
		plot.setSectionPaint("Unspent", new Color(0, 0, 0));
		plot.setSectionPaint("Spent", spentColor);
		
		plot.setDirection(Rotation.ANTICLOCKWISE); 

		ChartPanel chartPanel = new ChartPanel(chart);
		
		chartPanel.setPreferredSize(new Dimension(300, 300)); 
		
		chart.setAntiAlias(true);
		chart.setTextAntiAlias(true);
		chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		chart.getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		chartPanel.setMaximumDrawWidth(2000); 
		chartPanel.setMaximumDrawHeight(2000);
		chartPanel.setDoubleBuffered(false);
		
	
		chart.getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	
		plot.setShadowGenerator(null); 

	
		plot.setSectionOutlinesVisible(false);
		
		plot.setLabelGenerator(null);
		
		
		mainPanel.add(chartPanel);
		
		chartPanels.put(categoryName, chartPanel);
	}
	
	public void mainGui (Budget budget) {
		
		
		mainFrame = new JFrame();
		
		mainFrame.setSize(1920, 1080);
		mainFrame.setTitle("Personal Budgeting App");
		
		JTabbedPane tabPanel = new JTabbedPane();
		
		//Main Page
		JPanel homePanel = new JPanel(new BorderLayout());
		JLabel homeLabel = new JLabel("Home", SwingConstants.CENTER);
		homeLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); // Make it look like a title
		homePanel.add(homeLabel, BorderLayout.NORTH);
				
		JPanel chartsPanel = new JPanel();
		GridLayout grid = new GridLayout(2, 3, 20, 20);
		chartsPanel.setLayout(grid);
		
		for (String categoryName : budget.getMap().keySet()) {
			PurchaseCategory category = budget.getExpenseCategory(categoryName);
			addCategoryDataChart(chartsPanel, category);
		}
		
				
		JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
		centerWrapper.setBackground(Color.WHITE); 
		centerWrapper.add(chartsPanel);
		homePanel.add(centerWrapper, BorderLayout.CENTER);
		//---------------------------------------
		
		//Edit Page
		JPanel budgetEditPanel = new JPanel(new BorderLayout());
		JLabel budgetEditLabel = new JLabel("Edit Budget", SwingConstants.CENTER);
		budgetEditLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		budgetEditPanel.add(budgetEditLabel, BorderLayout.NORTH);
		
		JPanel editorsPanel = new JPanel();
		GridLayout editGrid = new GridLayout(2, 1, 5, 5);
		editorsPanel.setLayout(editGrid);
		
		JPanel catPanel = new JPanel();
		JPanel expPanel = new JPanel();
		
		
		Dimension fieldSize = new Dimension(120, 25);
		
		catPanel.setLayout(new GridBagLayout());
		expPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.gridy = 0;
		gridConstraints.gridx = 0;
		
		JLabel addCategoryLabel = new JLabel("Enter Budget Category Name: ");
		catPanel.add(addCategoryLabel, gridConstraints);
		
		gridConstraints.gridx = 1;
		
		JTextField addCategoryField = new JTextField(20);
		addCategoryField.setPreferredSize(fieldSize);
		addCategoryField.setMinimumSize(fieldSize);
		catPanel.add(addCategoryField, gridConstraints);
		
		gridConstraints.gridx = 2;
		gridConstraints.insets = new Insets(5,100,5,5);
		
		JLabel limitLabel = new JLabel("Set a Category Limit: $");
		limitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		limitLabel.setVerticalAlignment(SwingConstants.CENTER);
		catPanel.add(limitLabel, gridConstraints); 
		
		gridConstraints.gridx = 3;
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		
		JTextField limitTextField = new JTextField(15);
		limitTextField.setPreferredSize(fieldSize);
		limitTextField.setMinimumSize(fieldSize); // Prevent the size of the text field from changing based on window size
		catPanel.add(limitTextField, gridConstraints);
		
		gridConstraints.gridx = 4;
		gridConstraints.insets = new Insets(5, 40, 5, 5);
		
		JButton categoryButton = new JButton("Enter");
		catPanel.add(categoryButton, gridConstraints);
		
		gridConstraints.insets = new Insets(5,5,5,5); // 5 pixels gap on all sides

		gridConstraints.gridx = 0;
		JLabel selectCatLabel = new JLabel("Select Purchase Category Name: ");
		expPanel.add(selectCatLabel, gridConstraints);

		gridConstraints.gridx = 1;
		
		JComboBox<String> purchaseCategoriesBox = new JComboBox<>();
		for (String category : budget.getMap().keySet()) {
			purchaseCategoriesBox.addItem(category);
		}

		purchaseCategoriesBox.setPreferredSize(fieldSize);
		purchaseCategoriesBox.setMinimumSize(fieldSize); 
		expPanel.add(purchaseCategoriesBox, gridConstraints); 

		gridConstraints.gridx = 2;
		gridConstraints.insets = new Insets(5,100,5,5);
		
		JLabel expenseAmountLabel = new JLabel("Enter an Expense Amount: $");
		expenseAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		expenseAmountLabel.setVerticalAlignment(SwingConstants.CENTER);
		expPanel.add(expenseAmountLabel, gridConstraints); 

		gridConstraints.gridx = 3; 
		gridConstraints.insets = new Insets(5,5,5,5); 
		
		JTextField expenseAmountTextField = new JTextField(15);
		expenseAmountTextField.setPreferredSize(fieldSize);
		expenseAmountTextField.setMinimumSize(fieldSize); 
		expPanel.add(expenseAmountTextField, gridConstraints); 

		gridConstraints.gridx = 4; // Fifth column
		gridConstraints.insets = new Insets(5,40,5,5); 
		JButton expenseButton = new JButton("Enter");
		expPanel.add(expenseButton, gridConstraints);
		
		gridConstraints.gridx = 5; // Sixth column
		JButton clearButton = new JButton("Wipe budget progress");
		expPanel.add(clearButton, gridConstraints);
		
		categoryButton.addActionListener(a -> {
			String categoryName = addCategoryField.getText().trim(); // Gets the category name
			String limitText = limitTextField.getText().trim(); // Gets the category limit

			if (categoryName.isEmpty()) { // If category name is blank, cancel
				JOptionPane.showMessageDialog(mainFrame, "Please enter a category name.");
				return;
			}

			if (limitText.isEmpty()) { // If category limit is blank, cancel
				JOptionPane.showMessageDialog(mainFrame, "Please enter a category limit amount of type double.");
				return;
			}

			if (budget.getMap().containsKey(categoryName)) { // If category name already exists, cancel
				JOptionPane.showMessageDialog(mainFrame, "This category name already exists.");
				return;
			}

			try {
				Double categoryLimit = Double.parseDouble(limitText); // Check if category limit string is a double
				budget.addExpenseCategory(categoryName, categoryLimit); // Create the purchase category
				purchaseCategoriesBox.addItem(categoryName); // Adds new purchase category to the dropdown
				addCategoryDataChart(chartsPanel, budget.getExpenseCategory(categoryName));
				JOptionPane.showMessageDialog(mainFrame, "Purchase Category Successfully Added!");
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "Please enter a valid amount of type double."); // If category limit is not a double, cancel
			}
		}); // End action listener
		
		expenseButton.addActionListener(a -> {
			String categoryNameSelected = (String) purchaseCategoriesBox.getSelectedItem(); // Gets the selected category
			String expenseText = expenseAmountTextField.getText().trim(); // Gets the expense amount

			if (categoryNameSelected == null) { // If no category is selected, cancel
				JOptionPane.showMessageDialog(mainFrame, "Please select a category from the dropdown.");
				return;
			}

			if (expenseText.isEmpty()) { // If expense amount is blank, cancel
				JOptionPane.showMessageDialog(mainFrame, "Please enter an expense amount of type double.");
				return;
			}

			try {
				Double expenseAmount = Double.parseDouble(expenseText); // Check if expense amount string is a double
				budget.addExpense(categoryNameSelected, expenseAmount); // Create the expense
				updateChart(budget.getExpenseCategory(categoryNameSelected));
				BudgetLog.PurchaseLog newLog = budget.getBudgetLog().purchaseHistory.get(budget.getBudgetLog().purchaseHistory.size() - 1); // Index of the newest log
				String[] row = {newLog.getPurchaseType(), String.format("%.2f", newLog.getExpenseAmount()), newLog.getDateOfPurchase().toString()};
				tableModel.addRow(row);
				updateBudgetStatusLabel(budget);
				JOptionPane.showMessageDialog(mainFrame, "Expense Successfully Added!");
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "Please enter a valid amount of type double."); // If expense amount is not a double, cancel
			}
		}); // End action listener
		
		clearButton.addActionListener(a -> {
			int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Action", JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION) {
				budget.resetTotalExpenses();
				updateBudgetStatusLabel(budget);
				for (String categoryName : budget.getMap().keySet()) {
					budget.resetExpenseCategory(categoryName);
					updateChart(budget.getExpenseCategory(categoryName));
				}
			}
		});
		
		
		
		editorsPanel.add(catPanel);
		editorsPanel.add(expPanel);
		
		budgetEditPanel.add(editorsPanel, BorderLayout.CENTER);
		
		//---------------------------------------
				
		//View Page
		JPanel budgetViewPanel = new JPanel(new BorderLayout());
		JLabel budgetViewLabel = new JLabel("Budget Data", SwingConstants.CENTER);
		
		budgetViewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		budgetStatusLabel = new JLabel("", SwingConstants.CENTER);
		budgetStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JPanel viewTopPanel = new JPanel(new GridLayout(2,1));
		viewTopPanel.add(budgetViewLabel);
		viewTopPanel.add(budgetStatusLabel);
		
		budgetViewPanel.add(viewTopPanel, BorderLayout.NORTH);
		//---------------------------------------
		
		String[] tableColumns = {"Category","Amount","Date"};
		
		tableModel = new DefaultTableModel(tableColumns, 0);
		
		for (BudgetLog.PurchaseLog log : budget.getBudgetLog().purchaseHistory) {
			String[] row = {log.getPurchaseType(), String.format("%.2f", log.getExpenseAmount()), log.getDateOfPurchase().toString()};
			tableModel.addRow(row);
		}
		budgetLogTable = new JTable(tableModel);
		JScrollPane budgetLogScrollPane = new JScrollPane(budgetLogTable);
		budgetViewPanel.add(budgetLogScrollPane, BorderLayout.CENTER);
		updateBudgetStatusLabel(budget);
			
		//Adding tabs
		tabPanel.addTab("Home", homePanel);
		tabPanel.addTab("Edit Budget", budgetEditPanel);
		tabPanel.addTab("View Budget", budgetViewPanel);
		        
		mainFrame.add(tabPanel);
		mainFrame.setVisible(true);
		        
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		//CODE DIVIDER----------------------------------------------------------------------------------------------------
	}

	public GUI(Budget budget) {
		initGui(budget);
	} // End constructor
	
	public void updateBudgetStatusLabel(Budget budget) {
	    double budgetLimit = budget.getIncome() - budget.getSavings();
	    double totalExpenses = budget.getExpenseTotal();

	    if (totalExpenses >= budgetLimit) {
	        budgetStatusLabel.setText(
	            String.format("Budget Limit Met: $%.2f / $%.2f used", totalExpenses, budgetLimit)
	        );
	        budgetStatusLabel.setForeground(Color.RED);
	    } else {
	        budgetStatusLabel.setText(
	            String.format("Budget Limit Not Met: $%.2f / $%.2f used", totalExpenses, budgetLimit)
	        );
	        budgetStatusLabel.setForeground(new Color(0, 130, 0));
	    }
	}

} // End class