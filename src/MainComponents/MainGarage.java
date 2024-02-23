package MainComponents;

import Database.CreateDatabaseAndTables;

public class MainGarage {
	private static CreateDatabaseAndTables createDatabase;
	
	public static void main(String[] args) {
		
		//TODO Connection Database von der Main aus starten und beenden wenn das Programm geschlossen wird
		//TODO Live update der Tabellen von Kunde, Lager, Mitarbeiter
		
		createDatabase = new CreateDatabaseAndTables();
		createDatabase.createDatabaseAndTables();
		new MainInterface();
		
	}
}