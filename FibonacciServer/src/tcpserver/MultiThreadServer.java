package tcpserver;

import java.io.BufferedReader;  
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MultiThreadServer implements Runnable {

	Socket csocket;

	MultiThreadServer(Socket csocket) {
		this.csocket = csocket;
	}

	public void run() {
		try {
			PrintWriter outToClient = new PrintWriter(csocket.getOutputStream(), true);
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(csocket.getInputStream()));

			String ip = inFromClient.readLine();
			int ipNum = Integer.parseInt(ip);
			//get input from socket
			PrintStream pstream = new PrintStream(csocket.getOutputStream());
			FibonacciGenerator fibgen = new FibonacciGenerator();
			String reply = fibgen.execute(ipNum);
			System.out.println("reply::::::"+reply);
			pstream.println(reply);
			outToClient.write(reply);
			pstream.close();
			csocket.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) throws Exception { 
		
		ServerSocket ssock = new ServerSocket(8090);
		System.out.println("Listening");

		while (true) {
			Socket sock = ssock.accept();
			System.out.println("Connected");
			new Thread(new MultiThreadServer(sock)).start();
		}
	}

}