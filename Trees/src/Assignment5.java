import java.util.Scanner;

public class Assignment5 {
	
	public static void main(String[] args) {
		EnchantedForest forest = new EnchantedForest();
		Scanner input = new Scanner(System.in);
		
		boolean systemRunning = true;
		
		while (systemRunning) {
			System.out.println("**Menu:**");
			System.out.println();
			
			System.out.println("1. Plant a Tree");
			System.out.println("2. Wither a Tree");
			System.out.println("3. Find a Tree");
			System.out.println("4. Explore Trees by Power");
			System.out.println("5. Load Trees from File");
			System.out.println("0. Exit");
			
			System.out.println();
			
			System.out.println("Please enter your choice (1-6)");
			int choice = input.nextInt();
			
			if (choice == 1) {
				System.out.print("Enter a tree power and name: ");
				
				int key = input.nextInt();
				String value = input.next();
				
				forest.plantTree(key, value);
			}
			else if (choice == 2) {
				
			}
			else if (choice == 3) {
				
			}
			else if (choice == 4) {
				
			}
			else if (choice == 5) {
				
			}
			else if (choice == 0) {
				
			}
		}
				
	}
}