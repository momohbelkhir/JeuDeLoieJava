package serveur;


import java.net.Socket;

import game.GoosePlayer;

public class HandlePlayer implements Runnable, GooseModelEvents, GooseProtocol {
	private final Socket s;

	private GooseInput gooseIn;
	private GooseOutput gooseOut;
    public  InterfaceGameLogger log;
    private boolean stop = false;
	public HandlePlayer(Socket s, InterfaceGameLogger log ) {

		this.s = s;
        this.log = log;
	}

	@Override
	public void run() {

		try {

			this.gooseOut = new GooseOutput(s.getOutputStream());

			this.gooseIn = new GooseInput(s.getInputStream(), this);
			gooseIn.run();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void refreshPlayerlist() {
		// TODO Auto-generated method stub
		gooseOut.sendPlayerList(GooseModel.getPlayerList());
		
	}
	@Override
	public void refrechdisconnect() {
		System.out.println("je suis dans refrechdisconnect pour notifier j'envoi quit");
		gooseOut.sendQuit(GooseModel.getPlayerList());
		
		//finish(goosePlayer);
	}
	
	@Override
	public void addPlayer(GoosePlayer player) {
	
			GooseModel.addplayer(player, this);
							
		
	}
	
	//
	@Override
	public void change_pos_player(GoosePlayer player) {
	
			GooseModel.change_pos_player(player, this);
							
		
	}
	@Override
	public void player_won(GoosePlayer player) {
	
			GooseModel.player_won(player, this);
							
		
	}
	
	@Override
	public void deconnectplayer(GoosePlayer player) {
	       System.out.println("hplayer appel le model");
	      
			GooseModel.deconnectplayer(player, this);
							 
		
	}

	public Socket getS() {
		return s;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	
	
	
}
