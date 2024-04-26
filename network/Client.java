package com.brainmentors.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.brainmentors.chatapp.util.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
    public Client(JTextArea textArea) throws UnknownHostException, IOException {
    	int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
    	socket=new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
        out = socket.getOutputStream();
        in = socket.getInputStream();
        this.textArea=textArea;
        readMessages();
//    	System.out.println("client comes");
//    	System.out.println("Enter the Message Send to the Server....");
//    	Scanner scanner =new Scanner(System.in);
//    	String message=scanner.nextLine();
//    	//to write the data
//    	OutputStream out=socket.getOutputStream();
//    	out.write(message.getBytes());
//    	System.out.println("Message sent to the server");
//    	scanner.close();
//    	out.close();
//    	socket.close();
    	
    }
    public void sendMessage(String message) throws IOException {
    	message=message+"\n";
    	out.write(message.getBytes());
    }
    public void readMessages() {
    	worker=new ClientWorker(in,textArea);
    	worker.start();
    }

}
