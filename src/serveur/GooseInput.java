package serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import game.GoosePlayer;

public class GooseInput {
	private boolean stop =false ;
	private HandlePlayer hplayer ;
	private InputStream inputS ;
	
	
	public GooseInput (InputStream in,HandlePlayer handle) {
		this.inputS=in;
		
		this.hplayer =handle ;	
		
	}
	
	
	public void run () throws IOException, Exception {
		
		
		
		
		ObjectInputStream in =new ObjectInputStream(inputS);
		
		GoosePlayer gooseplayer = null;
		
		//
		 
		while (!stop){
			String input = (String) in.readObject();
			switch (input){
			case "ADDCLIENT":
				try {
					gooseplayer=(GoosePlayer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hplayer.addPlayer(gooseplayer);
				break;
			case "CHANGEPOS":
				try {
					gooseplayer=(GoosePlayer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hplayer.change_pos_player(gooseplayer);
				break;
				
			case "WON":
				try {
					gooseplayer=(GoosePlayer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hplayer.player_won(gooseplayer);
				break;
				
			case "DECONNECT":
				try {
					gooseplayer=(GoosePlayer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("DECONNECT est recu par hplayer");
				//this.stop = true;
				//in.close();
				//stop= true;
				hplayer.deconnectplayer(gooseplayer);
				//this.stop();
				//input=null;
				//stop=true;
				break;
				
			//trou dans le zip
				
				default:
					
							
			}
		
	
		
			
	}

		
	
	
}
}
