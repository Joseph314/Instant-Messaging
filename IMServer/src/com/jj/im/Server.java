package com.jj.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		if(args.length != 1){
			System.err.println("Usage: java IMServer <port>");
			System.exit(1);
		}
		
		int portNumber= Integer.parseInt(args[0]);
		
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		) {
<<<<<<< HEAD
=======
			System.out.println("Connected");
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
>>>>>>> upstream/master
			String fromClient, fromServer;
			fromServer="hello";
			in.ready();
			System.out.println("So far so good");
			out.println(fromServer);
			while ((fromClient = in.readLine()) != null) {
				System.out.println("Client: " + fromClient);
				if (fromClient.equals("\\shutdown")){
					break;
				}
				fromServer = "test";
				if (fromServer!=null){
					System.out.println("Client: " + fromServer);
					out.println(fromServer);
					out.flush();
				}
				if(fromServer.equals("\\shutdown")){
					break;
				}
			}
			System.out.println("Did you get anything");
		} catch(IOException e) {
			System.out.println("Exception caught when trying to listen on port" + portNumber+" or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

}
