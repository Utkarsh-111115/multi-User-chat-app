package com.brainmentors.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//thread=worker
//worker need a job to perform
//for job u give runnable
public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket,Server server) throws IOException {
		this.server=server;
		this.clientSocket=clientSocket;
		in=clientSocket.getInputStream();//client data read
		out=clientSocket.getOutputStream();//client data write
		System.out.println("New client comes");
	}
	@Override
	public void run() {
		//read data from the client and broadcast the data to all
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String line;
		try {
		while(true) {
				line=br.readLine();
				if(line.equalsIgnoreCase("quit")){
					break;
				}
				//out.write(line.getBytes());//client send
			//broadcast
				for(ServerWorker serverWorker:server.workers) {
				    line=line+"\n";
					serverWorker.out.write(line.getBytes());
			}
		} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

