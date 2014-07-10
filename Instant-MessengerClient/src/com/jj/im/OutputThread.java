package com.jj.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class OutputThread extends Thread{
	private PrintWriter out;
	public OutputThread(PrintWriter out){
		this.out = out;
	}
	public void run(){
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromClient;
		try {
			while((fromClient = stdIn.readLine())!=null){
				out.write(fromClient);
				out.flush();
			}
		} catch (IOException e) {
			System.err.println("Couldn't get I/0 for connection to hostname");
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
