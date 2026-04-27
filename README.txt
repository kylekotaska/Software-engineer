1. External File Dependencies include a database manager file (with the jar file of sqlite-jdbc in Eclipse) and 2 jar files (names are jcommon and jfreechart, both within Eclipse).

2. To run the program, open the IDE Eclipse, make a new project and import the files (after you extracted them) to the src of the new project folder, then go to the main class and click the green run button on the top left of the IDE (if the green button is highlighted but not clicked, it should say "Run Main"). Then if its the first time the program has been run, then the first window should pop up and ask for the income. 

3. A big deviation we made from the original plan was the suggestions our budgeting app would give after the input of income and expenses. Another deviation we made was not including the input of savings within the program. 

4. We decided to use a facade, as the facade helps give our program a black box approach and makes coupling with our classes more manageable.

5. Our test cases were designed to make sure the program does what its supposed to do in the requirements. Specifically, the unit tests help test important functions within the classes to make sure we can trust each of the methods. The system test helps us show what our program should be doing as a whole and make sure each functionality shown in the system test is what is shown from our program. 