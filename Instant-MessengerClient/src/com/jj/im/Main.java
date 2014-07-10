package com.jj.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 2){
			System.err.println("Usage: java IMClient <host ip/name> <port>");
			System.exit(1);
		}
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		try (
			Socket imSocket = new Socket(hostName,portNumber);
			PrintWriter out = new PrintWriter(imSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(imSocket.getInputStream()));
		) {
			OutputThread output = new OutputThread(out);
			output.start();
			String fromServer;
			in.ready();
			while ((fromServer = in.readLine()) != null) {
				System.out.println("SomeoneElse: " + fromServer);
			}
		} catch(UnknownHostException e){
			System.err.println("I'm not so sure about" + hostName);
			System.exit(1);
		} catch(IOException e) {
			System.err.println("Couldn't get I/O for connection to "+hostName);
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
