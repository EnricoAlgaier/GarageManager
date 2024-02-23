package DatabaseLogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import GuiElements.CTextField;

public class LoginInformation {
	private CTextField newdatabaseValue;
	private File database = new File("databasevalues.txt");
	private BufferedReader reader;
	private FileWriter writer;
	private List<String>databaseValues;
	
	public LoginInformation(CTextField newdatabaseValue) {
		this.newdatabaseValue = newdatabaseValue;
	}
	
	public void readTxt() {
		try {
			reader = new BufferedReader(new FileReader(database));
			String line;
			
			while((line = reader.readLine()) != null) {
				String[] words = line.split(" ");
				
				for(String word : words) {
					System.out.println(word);
					databaseValues.add(word);
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void writeTxt() {
		String input = databaseValues.get(0);
		try {
			writer = new FileWriter(database, false);
			writer.write(input);
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			
		} catch (Exception e) {
			
		}
	}
	
	public void setDatabaseValuesInput(int length) {
		newdatabaseValue.textInput(length);
		databaseValues = new ArrayList<>();
		databaseValues = newdatabaseValue.getText();
	}
}