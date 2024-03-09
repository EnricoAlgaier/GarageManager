package Login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import GuiElements.CTextField;

public class LoginInformation {
	private CTextField newdatabaseValue, userNameValue, loginValue;
	private File database = new File("database.txt");
	private BufferedReader reader;
	private FileWriter writer;
	private List<String> databaseValue;
	private List<String> loginInformation;
	private List<String> userName;

	public LoginInformation(CTextField newdatabaseValue, CTextField loginValue, CTextField userNameValue) {
		this.newdatabaseValue = newdatabaseValue;
		this.loginValue = loginValue;
		this.userNameValue = userNameValue;
	}

	public void readTxt() {

		try {
			reader = new BufferedReader(new FileReader(database));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] words = line.split(" ");

				for (String word : words) {
					System.out.println(word);
					databaseValue.add(word);
				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void writeTxt() {
		String input = databaseValue.get(0);
		try {
			writer = new FileWriter(database, false);
			writer.write(input);
			writer.write(System.getProperty("line.separator"));
			writer.flush();

		} catch (Exception e) {

		}
	}

	public void setDatabaseValue(int length) {
		newdatabaseValue.textInput(length);
		databaseValue = new ArrayList<>();
		databaseValue = newdatabaseValue.getText();
	}

}