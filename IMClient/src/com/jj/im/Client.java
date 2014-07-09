package com.jj.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws IOException{
		if(args.length != 2){
			System.err.println("Usage: java IMClient <host ip/name> <port>");
			System.exit(1);
		}
		String hostName = args[0];
		int portNumber= Integer.parseInt(args[1]);
		
		try (
			Socket imSocket = new Socket(hostName, portNumber); 
			PrintWriter out = new PrintWriter(imSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(imSocket.getInputStream()));
		) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromClient, fromServer;
			
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("\\shutdown")){
					break;
				}
				fromClient = stdIn.readLine();
				if(fromClient != null){
					System.out.println("Client: "+fromClient);
					out.println(fromClient);
					out.flush();
				}
				if(fromClient.equals("\\shutdown")){
					break;
				}
			}
		} catch(UnknownHostException e) {
			System.err.println("I'm not so sure about "+hostName);
			System.exit(1);
		} catch(IOException e){
			System.err.println("Couldn't get I/0 for connection to " + hostName);
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
