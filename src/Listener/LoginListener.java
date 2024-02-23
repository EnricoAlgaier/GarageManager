package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import DatabaseLogin.DatabaseLoginFrame;
import DatabaseLogin.LoginInformation;

public class LoginListener implements ActionListener{
	private DatabaseLoginFrame databaseLoginFrame;
	private LoginInformation loginInformation;
	
	public LoginListener(DatabaseLoginFrame databaseLoginFrame, LoginInformation loginInformation) {
		this.databaseLoginFrame = databaseLoginFrame;
		this.loginInformation = loginInformation;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonID = ((JButton)e.getSource()).getActionCommand();
		
		if("login".equals(buttonID)) {
			loginInformation.setDatabaseValuesInput(databaseLoginFrame.getInputFieldSize());
			loginInformation.writeTxt();
			
		} else if("cancel".equals(buttonID)) {
			databaseLoginFrame.dispose();
		}
	}

}