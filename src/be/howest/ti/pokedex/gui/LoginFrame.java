package be.howest.ti.pokedex.gui;

import javax.swing.*;

public class LoginFrame extends JFrame {
	private static final int WIDTH = 300;
	private static final int HEIGHT = 400;
	private JPanel mainPanel;
	private JTextField usernameLoginTextField;
	private JPasswordField passwordLoginTextField;
	private JButton loginButton;
	private JLabel loginErrorLabel;
	private JTextField usernameRegisterTextField;
	private JPasswordField passwordTextField;
	private JPasswordField passwordRepeatTextfield;
	private JButton registerButton;
	private JLabel registerErrorLabel;
	private JPanel loginPanel;
	private JPanel registerPanel;
	private JTabbedPane loginTabbedPane;

	public LoginFrame() {
		setTitle("Program");
		setSize(WIDTH, HEIGHT);
		setContentPane(mainPanel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JTextField getUsernameLoginTextField() {
		return usernameLoginTextField;
	}

	public JPasswordField getPasswordLoginTextField() {
		return passwordLoginTextField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JLabel getLoginErrorLabel() {
		return loginErrorLabel;
	}

	public JTextField getUsernameRegisterTextField() {
		return usernameRegisterTextField;
	}

	public JPasswordField getPasswordTextField() {
		return passwordTextField;
	}

	public JPasswordField getPasswordRepeatTextfield() {
		return passwordRepeatTextfield;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JLabel getRegisterErrorLabel() {
		return registerErrorLabel;
	}
}
