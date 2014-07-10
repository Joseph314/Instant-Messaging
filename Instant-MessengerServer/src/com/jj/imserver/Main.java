package com.jj.imserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {
	public static ArrayList<ServerThread> clients;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 1){
			System.err.println("Usage: java Instant-MessengerServer <port>");
			System.exit(1);
		}
		int portNumber = Integer.parseInt(args[0]);
		boolean listening = true;
		clients = new ArrayList<ServerThread>();
		
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			while (listening) {
				ServerThread socket = new ServerThread(serverSocket.accept());
				clients.add(socket);
				socket.start();
			}
		} catch(IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(1);
		}
	}
}
