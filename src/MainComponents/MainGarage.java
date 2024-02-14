package MainComponents;

import Database.CreateDatabaseAndTables;

public class MainGarage {
	private static CreateDatabaseAndTables createDatabase;
	public static void main(String[] args) {
		
		createDatabase = new CreateDatabaseAndTables();
		createDatabase.createDatabaseAndTables();
		new MainInterface();
		
	}
}