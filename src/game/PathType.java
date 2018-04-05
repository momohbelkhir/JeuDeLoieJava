package game;

import java.awt.Color;

public enum PathType {
	
	Simple (Color.red),  
	Avance_3(Color.green) ,
	Avance_7 (Color.yellow),  
	Recul_3 (Color.pink), 
	Recul_7 (Color.black),
	X1(Color.cyan);
	
	private Color couleur;

	private PathType(Color couleur) {
		this.setCouleur(couleur);
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	

}
