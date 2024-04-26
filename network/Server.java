package com.brainmentors.chatapp.network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.brainmentors.chatapp.util.ConfigReader;
public class Server{
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers=new ArrayList<>();
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("Server start and Waiting for clients to join.....");
		handleClientRequest();
	}
	//Multiple client Handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
		Socket clientSocket=serverSocket.accept();//handshaking
		//per client per thread
		ServerWorker serverWorker=new ServerWorker(clientSocket,this);
		workers.add(serverWorker);
		serverWorker.start();
		}
	}
  public static void main(String[] args) throws IOException {
  Server server = new Server();
}
}

//Single client
//import java.io.InputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//import com.brainmentors.chatapp.util.ConfigReader;
//
//public class Server {
//	ServerSocket serverSocket;
//	public Server() throws IOException {
//		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
//		serverSocket=new ServerSocket(PORT);
//		System.out.println("Server Started and Waiting for Client");
//		Socket socket=serverSocket.accept();//Handshaking
//		System.out.println("Client joins the server");
//		InputStream in=socket.getInputStream();//read bytes from network
//		byte arr[]=in.readAllBytes();
//		String str=new String(arr);//convert byte into string
//		System.out.println("Message recieved from the client"+str);
////		in.close();
//		socket.close();
//	}
//	public static void main(String[] args) throws IOException {
//		Server server=new Server();
//
//	}
//
//}



//package com.brainmentors.chatapp.network;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//import com.brainmentors.chatapp.util.ConfigReader;
//
//public class Server {
//    ServerSocket serverSocket;
//
//    public Server() throws IOException {
//        int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
//        serverSocket = new ServerSocket(PORT);
//        System.out.println("Server Started and Waiting for Client");
//        Socket socket = serverSocket.accept(); // Handshaking
//        System.out.println("Client joins the server");
//        InputStream in = socket.getInputStream(); // read bytes from network
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        int nRead;
//        byte[] data = new byte[1024];
//        while ((nRead = in.read(data, 0, data.length)) != -1) {
//            buffer.write(data, 0, nRead);
//        }
//        buffer.flush();
//        byte[] arr = buffer.toByteArray();
//        String str = new String(arr); // convert byte into string
//        System.out.println("Message received from the client: " + str);
//        socket.close();
//    }
//
//    public static void main(String[] args) throws IOException {
//        Server server = new Server();
//    }
//}
//
