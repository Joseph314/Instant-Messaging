package com.jj.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private Socket socket = null;
	public Server(Socket socket) {
        super("Server");
        this.socket = socket;
    }
	public void run(){

		try (
			/*ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();*/
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {

			System.out.println("Connected");
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
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
			System.out.println(e.getMessage());
		}
	}

}
