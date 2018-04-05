package client;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Coordinate;
import game.GoosePlayer;

import view.GooseGui;

public class InitSalon extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int nombre_joueur ; 
	
    
    private GoosePlayer [] players ; 
    private GooseGui[]goosegui;	
	private int classement;
	
	public InitSalon( ) throws IOException, InterruptedException {
		super();
		
		
		JPanel panel =new JPanel();
		JTextField txt = new JTextField(15);
		panel.add(txt);
		
		JButton b = new JButton("Ok");
		panel.add(b);
		//txt.setBounds(10, 10, 10, 1);
		getContentPane().add(panel);
		setSize(350, 150);
		setTitle("Entrez un Entier > 0 && < 4");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	     System.out.println(""+txt.getText());
		//this.ask_for_players();
	     
	     b.addMouseListener(new MouseAdapter() {
		    	@Override
	             public void mouseClicked(MouseEvent e){
		    		
	           String a =txt.getText();
	            int num = (Integer.parseInt(a));
	            System.out.println(""+num);
	            
	            dispose();
	            try {
					ask_for_players(num);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	 	  }
		});
	  


	}
	
	
    
	public void ask_for_players(int num) throws IOException, InterruptedException{
	
	     this.nombre_joueur = num;
	    
		
		players = new GoosePlayer[this.nombre_joueur];
	     goosegui = new GooseGui[this.nombre_joueur]; 
		for(int i =0 ; i<this.nombre_joueur ; i++){	
			
			
		
			 if (i==0){
			players[i]= new GoosePlayer(new Coordinate(i,0),"joueur"+i,(i+1) );
			players[i].setMonTour(true);
			goosegui[i]= new GooseGui(1234, "localhost", 13, 13, players[i]);
			
			
}else {
	new Thread();
	Thread.sleep(500);
	players[i]= new GoosePlayer(new Coordinate(i,0),"joueur"+i,(i+1) );
	players[i].setMonTour(false);
	goosegui[i]= new GooseGui(1234, "localhost", 13, 13, players[i]);
	
}
		}
		
		System.out.println("le jeu commence !");
		
	}
	

	public int getNombre_joueur() {
		return nombre_joueur;
	}


	public void setNombre_joueur(int nombre_joueur) {
		this.nombre_joueur = nombre_joueur;
	}


	

	public GoosePlayer[] getPlayers() {
		return players;
	}


	public void setPlayers(GoosePlayer[] players) {
		this.players = players;
	}


	public int getClassement() {
		return classement;
	}



	public void setClassement(int classement) {
		this.classement = classement;
	}



	

}
