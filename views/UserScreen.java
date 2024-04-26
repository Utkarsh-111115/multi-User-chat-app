package com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.brainmentors.chatapp.dao.UserDAO;
import com.brainmentors.chatapp.dto.UserDTO;
import com.brainmentors.chatapp.util.UserInfo;

public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordField;
	public static void main(String[] args) {
					UserScreen window = new UserScreen();
	}
	UserDAO userDAO=new UserDAO();
	
private void doLogin() {
	String userid=useridtxt.getText();
	char[] password=passwordField.getPassword();
	UserDTO userDTO=new UserDTO(userid,password);
	try {
		String message="";
		if(userDAO.isLogin(userDTO)) {
			message="Welcome "+userid;
			UserInfo.USER_NAME=userid;
			JOptionPane.showMessageDialog(this,message);
			setVisible(false);
			dispose();//to finish current from memory
			DashBoard dashboard=new DashBoard(message);
			dashboard.setVisible(true);
		}else {
			message="Invalid Userid or Password";
			JOptionPane.showMessageDialog(this,message );
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void register() {
	String userid=useridtxt.getText();
	char[] password=passwordField.getPassword();
	//UserDAO userDAO=new UserDAO();
	UserDTO userDTO=new UserDTO(userid,password);
	try {
	int result=userDAO.add(userDTO);
	if(result>0) {
		JOptionPane.showMessageDialog(this, "Registered SuccessFully");
//		System.out.println("Record added....");
	}else {
		//System.out.println("Record not added....");
		JOptionPane.showMessageDialog(this, "Register Fail");
	}
	}
	catch(ClassNotFoundException |SQLException ex) {
		System.out.println("DB issue....");
		ex.printStackTrace();//print from where exception is raised
	}
	catch(Exception ex) {
		System.out.println("Some generic exception Raised...");
		ex.printStackTrace();//where is the exception
	}
	System.out.println("userid"+userid+"password"+password);//C@hashcode
}
	public UserScreen() {
		getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 20));
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Login");
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(218, 41, 192, 67);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(276, 143, 313, 27);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("User id");
		useridlbl.setHorizontalAlignment(SwingConstants.CENTER);
		useridlbl.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		useridlbl.setBounds(103, 140, 125, 41);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setHorizontalAlignment(SwingConstants.CENTER);
		pwdlbl.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		pwdlbl.setBounds(113, 189, 125, 41);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(276, 195, 313, 27);
		getContentPane().add(passwordField);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		loginbtn.setBounds(219, 281, 125, 41);
		getContentPane().add(loginbtn);
		
		JButton registerbtn = new JButton("Register");
		registerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		registerbtn.setBounds(407, 281, 125, 41);
		getContentPane().add(registerbtn);
		setBounds(100, 100, 661, 464);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
