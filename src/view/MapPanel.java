package view;


//


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")



public class MapPanel extends JPanel{
	
	  private GooseGui g;
	  JButton b2 ;
	  JButton b3;
	public MapPanel(GooseGui g) throws InterruptedException {
		super();
		this.g=g;
		
		     b2 = new JButton("Lance Dé");
		     b2.setVerticalTextPosition(AbstractButton.BOTTOM);
		     b2.setHorizontalTextPosition(AbstractButton.LEFT);
		   // b2.setMnemonic(KeyEvent.VK_M);
		   // b2.setEnabled(g.getGoosePlayer().isMonTour());
		    
		     b3 = new JButton("Disconnect");
		     b3.setVerticalTextPosition(AbstractButton.BOTTOM);
		     b3.setHorizontalTextPosition(AbstractButton.LEFT);
		   // b3.setMnemonic(KeyEvent.VK_M);
		   
		    b3.setBounds(7, 0, 4, 4);
		 /* if(!( g.getGoosePlayer().isMonTour())){
		    	JOptionPane.showMessageDialog(null,"wait your turn",null,0);
		    }*/
		  
		    b2.addMouseListener(new MouseAdapter() {
		    	@Override
	             public void mouseClicked(MouseEvent e){
	             System.out.println("jai clicker");
	           Random r = new Random();
	    		
	             int d1= 1+ r.nextInt(6);
	             int d2= 1+ r.nextInt(6);
	             int result = d1+d2;
	             JOptionPane.showMessageDialog(null,"dé1 = "+d1+"    dé2 = "+d2+"    ("+(d1+d2)+")",null,0);
	             g.setDéResult((d1+d2));
	             System.out.println("dé "+result);
	             
	             g.move(result);
	             //repaint();
	             //g.setClick(true);
	            
	 	  }
		});
		this.add(b2);
		b3.addMouseListener(new MouseAdapter() {
			@Override
		       public void mouseClicked(MouseEvent e){
	             System.out.println("deconnection");
	     try {
	    	 
	    	 
	    	 System.out.println("jai cliké sur deconnection envoi vers le serveur");
	    	g.getFrame().dispose();
	    	 g.gooseSocket.DeconnectPlayer(g.getGoosePlayer());
	    	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	           
	            
	 	  }
		});
		this.add(b3);
		
		
		
		
		 this.addMouseListener(new MouseAdapter() {
       	  @Override
             public void mouseClicked(MouseEvent e){
         	    int x = e.getX();
	             int y = e.getY();
	             System.out.println("mouse    "+x/45 + " " + y/45);
	             
	           
             
 	  }
     });
	}

	public GooseGui getG() {
		return g;
	}

	public void setG(GooseGui g) {
		this.g = g;
	}

	public JButton getB2() {
		return b2;
	}

	public void setB2(JButton b2) {
		this.b2 = b2;
	}

	public JButton getB3() {
		return b3;
	}

	public void setB3(JButton b3) {
		this.b3 = b3;
	}
	
	

}
