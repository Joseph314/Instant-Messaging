package com.jj.imserver;

import java.io.IOException;
import java.io.PrintWriter;

public class Interclient extends Thread{
	private String message;
	private ServerThread exception;
	public Interclient(String message, ServerThread exception) {
		super();
		this.message = message;
		this.exception = exception;
	}
	public void run(){
			int n=Main.clients.size();
			for(int i=0; i<n; i++){
				ServerThread j = Main.clients.get(i);
				if(!j.equals(exception)){
					try {
						PrintWriter out = new PrintWriter(j.socket.getOutputStream(), true);
						out.write(message);
						out.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
}
