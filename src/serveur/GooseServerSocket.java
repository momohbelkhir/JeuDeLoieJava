package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import client.InitSalon;

public class GooseServerSocket extends Thread {

	private boolean stop = false;
	private int port;
	ServerSocket ss;
	private InterfaceGameLogger log = null ;

	public GooseServerSocket (int port) throws IOException {
		this.port = port;
		log = new TextGameLogger();
		this.start();
	}

	public void run(){
		try (ServerSocket ss = new ServerSocket(port)){
			ss.setSoTimeout(1000);
			log.printMessage("Server lunched And Waiting for connections ...");
			while(!stop){
				try{
					
					Socket s = ss.accept();
				    log.playerConnected(s.toString());
					new Thread (new HandlePlayer(s,log)).start();
					
				}catch (SocketTimeoutException ex){
				}
			}
		}catch (IOException e){
			log.printMessage("Port not disponible "+ port);
			
		}
	}
	
	
	
	public synchronized void finish(){
		stop = true;
	}
	//
	public static void main(String[] args) throws IOException, InterruptedException {
		new GooseServerSocket(1234);
		
		new Thread();
		Thread.sleep(2000);
		try {
			new InitSalon();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
