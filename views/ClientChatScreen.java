package com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.brainmentors.chatapp.network.Client;
import com.brainmentors.chatapp.util.UserInfo;

public class ClientChatScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;
	public static void main(String[] args) {
					try {
						ClientChatScreen frame = new ClientChatScreen();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}
private void sendIt() {
	String message=textField.getText();
	try {
		client.sendMessage(UserInfo.USER_NAME+"-"+message);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	/**
	 * Create the frame.
	 */
	public ClientChatScreen() throws IOException {
		textArea=new JTextArea();
		client=new Client(textArea);
		setTitle("Chat-Box");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 571, 255);
		contentPane.add(scrollPane);
		
		textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		textArea.setBounds(20, 35, 536, 298);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(10, 302, 454, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendIt = new JButton("Send");
		sendIt.setBackground(new Color(0, 255, 255));
		sendIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendIt.setBounds(492, 302, 89, 38);
		contentPane.add(sendIt);
		setVisible(true);
	}
}
