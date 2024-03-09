package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Login.DatabaseLoginFrame;
import Login.LoginInformation;

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
			
			loginInformation.setDatabaseValue(databaseLoginFrame.getFieldSize());
			loginInformation.writeTxt();
			
		} else if("cancel".equals(buttonID)) {
			databaseLoginFrame.dispose();
		}
	}

}