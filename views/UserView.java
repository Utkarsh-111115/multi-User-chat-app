package com.brainmentors.chatapp.views;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class UserView extends JFrame implements ActionListener{
	int count=0;
	JLabel welcome;
	public UserView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setSize(500,500);
		setResizable(false);
		//setLocation(500,150);
//		setLocationRelativeTo(null);
		setVisible(true);
		welcome=new JLabel("Login");
		welcome.setFont(new Font("Arial",Font.BOLD,40));
//		Container container=this.getContentPane();
//		container.setLayout(null);
//		welcome.setBounds(100,70, 200, 60);
//		container.add(welcome);
		JButton button=new JButton("counter");
		button.addActionListener(this);
//		button.setBounds(50,70, 200, 60);
//		container.add(button);
		add(welcome);
		add(button);
		setLayout(new FlowLayout());
	}
	public void actionPerformed(ActionEvent e){
		count++;
		welcome.setText(""+count);
	}
	public static void main(String []args) {
		UserView userView=new UserView();
	}
}
