package game;

 import java.awt.Color;


import java.io.Serializable;




@SuppressWarnings("serial")
//
public class GoosePlayer implements Serializable ,Comparable<GoosePlayer> {
	


	private Coordinate pos;
	private Color couleur;
	private int place;
	private boolean monTour = false ;
	private String namePlayer ;
	private int idplayer;
	private boolean connected ;
	//private Image image;
	
	
	public GoosePlayer(Coordinate pos,String name, int id) {
		this.namePlayer=name;
		this.pos = pos;
		//this.image = image;
	    this.idplayer = id;
		
}
	
	public int getIdplayer() {
		return idplayer;
	}

	public void setIdplayer(int idplayer) {
		this.idplayer = idplayer;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}
	public Coordinate getPos() {
		return pos;
	}


	public Color getCouleur() {
		return couleur;
	}


	public int getPlace() {
		return place;
	}


	public boolean isMonTour() {
		return monTour;
	}


	public void setPos(Coordinate pos) {
		this.pos = pos;
	}


	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}


	public void setPlace(int place) {
		this.place = place;
	}


	public void setMonTour(boolean monTour) {
		this.monTour = monTour;
	}
	
	
	
	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	

	@Override
	public int compareTo(GoosePlayer player) {

		if (this.getNamePlayer().equals(player.getNamePlayer()) ) {
			
			return 1;
			
		}else {
			
			
			return -1;
		}

		
	}

	
	
	}
