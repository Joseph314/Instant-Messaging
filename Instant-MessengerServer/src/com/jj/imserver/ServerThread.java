package com.jj.imserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	public Socket socket = null;
	public ServerThread(Socket socket) {
        this.socket = socket;
    }
	public void run(){
		try (
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			) {
				
				System.out.println("Connected");
				String fromClient;
				in.ready();
				while ((fromClient = in.readLine()) != null) {
					Interclient messenger = new Interclient(fromClient,this);
					messenger.start();
				}
				System.out.println("Did you get anything");
			} catch(IOException e) {
				System.out.println(e.getMessage());
			}
	}
	
}
