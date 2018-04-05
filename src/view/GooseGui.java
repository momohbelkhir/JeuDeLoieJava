package view;


import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.GooseSocket;
import game.Coordinate;
import game.GoosePlayer;
import game.Path;
import game.PathType;





import javax.swing.ImageIcon;
import java.awt.Image;




//


public class GooseGui {
    
	private int width;
	private int height;
	private static final int scale = 45;
	private MapPanel map;
	private ArrayList<Path> path;
	private int dÈResult;
	GooseSocket gooseSocket;
	private GoosePlayer goosePlayer;
	private ArrayList<GoosePlayer> allGoosePlayers;

	private int port;
	private String ip = "localhost";

	private JFrame  frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@SuppressWarnings("serial")
	public GooseGui(int port, String ip, int width, int height,GoosePlayer gp) throws IOException, InterruptedException {

		super();
		this.port = port;
		this.ip = ip;
		this.setHeight(height);
		this.width = width;
		this.path= new ArrayList<Path>();
		this.allGoosePlayers = new ArrayList<GoosePlayer>();
		
		
		
		goosePlayer = gp;
		 frame  = new JFrame("Goose Game -> "+gp.getNamePlayer()+" id= "+ gp.getIdplayer());
		
		 gooseSocket =new GooseSocket(port, ip, this);
		 gooseSocket.connect();
		 gooseSocket.addPlayerToServer(goosePlayer);
		
		dessinerChemin();
		
		// JLabel l =new JLabel("");
		// Image img = new ImageIcon(this.getClass().getResource("/oie2.jpg")).getImage();
		// l.setIcon(new ImageIcon(img));
		 
		
		this.map = new MapPanel(this) {
	        
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				
				for (Path o : path) {

					g.setColor(o.getType().getCouleur());
					g.fill3DRect(o.getPos().getX() * scale, o.getPos().getY() * scale, scale, scale, false);
					 //g.drawImage(image,0,0, width, height, this);
				}
		    //g.setColor(Color.BLUE);	
            //g.fill3DRect(goosePlayer.getPos().getX() * scale, goosePlayer.getPos().getY() * scale, scale, scale, false);
				for (int i=0; i< allGoosePlayers.size(); i++) {
					Coordinate posP = allGoosePlayers.get(i).getPos();
					//g.setColor( allGoosePlayers.get(i).getCouleur());
					//g.fill3DRect(posP.getX() * scale, posP.getY() * scale, scale, scale, false);
					Image img= new ImageIcon(this.getClass().getResource("/oie"+(i+1)+".jpg")).getImage();
			g.drawImage(img,posP.getX()*scale, posP.getY()*scale, null);
					System.out.println(getAllGoosePlayers().get(i).getNamePlayer());
					
					
				}
				
				//g.drawImage(img,goosePlayer.getPos().getX()*scale, goosePlayer.getPos().getY()*scale, null);
				//l.setBounds(goosePlayer.getPos().getX()*scale,goosePlayer.getPos().getY()*scale,100,100);
			}
		};
		
	
		// frame.getContentPane().add(l);
		frame.getContentPane().add(map);
		frame.setSize(width * scale, height * scale);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public void move(int d) {
		// TODO Auto-generated method stub
		
		System.out.println("je suis dand move apres le click");
		if(goosePlayer.getPlace()+d < getPath().size()) { 
			
			goosePlayer.setPlace(goosePlayer.getPlace()+d);
			goosePlayer.setPos(getPath().get(goosePlayer.getPlace()).getPos()); //deplacer 
			goosePlayer.setMonTour(false);
			
		
			System.out.println(goosePlayer.getPos());
			try {
				this.gooseSocket.change_pos_player(goosePlayer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actionMouvement (goosePlayer.getPlace()); // re deplacer si la case contient un pouvoir
            
            
		}else {
			int c=getPath().size();
			goosePlayer.setPlace(  (c- (d- (c-goosePlayer.getPlace())))-2  );
			goosePlayer.setPos(getPath().get(goosePlayer.getPlace()).getPos()); //deplacer 
			//goosePlayer.setMonTour(false);
			
			System.out.println(goosePlayer.getPos());
			try {
				
				 
				this.gooseSocket.change_pos_player(goosePlayer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		actionMouvement (goosePlayer.getPlace()); // re deplacer si la case contient un pouvoir
		//gooseGui.getMap().repaint();
		// passage du tour 
		
       try {
		new Thread();
		Thread.sleep(1000);
		chekforwin(goosePlayer);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	private void chekforwin(GoosePlayer goosePlayer2) {
		// TODO Auto-generated method stub
		if (goosePlayer2.getPlace()==(path.size()-1)){
			JOptionPane.showMessageDialog(null,"Game Over ! "+goosePlayer2.getNamePlayer()+" ‡ gagnÈ !",null,0);
		}
	}
	public void actionMouvement (int pas) {
		if(path.get(pas).getType()==PathType.Avance_3) {
			move(3);	
		}else if(path.get(pas).getType()==PathType.Avance_7) {
			move(7);
		}else if(path.get(pas).getType()==PathType.Recul_7) {
			move(-7);
		}else if(path.get(pas).getType()==PathType.Recul_3) {
			move(-3);
		}
		
	}
	
private void dessinerChemin() {
		
		for (int i=1;i<12;i++)path.add(new Path(new Coordinate(i, 2),PathType.Simple));
		for (int i=3;i<11;i++)path.add(new Path(new Coordinate(11,i),PathType.Simple));
		for (int i=10;i>=1;i--)path.add(new Path(new Coordinate(i,10),PathType.Simple));
		for (int i=9;i>=4;i--)path.add(new Path(new Coordinate(1,i),PathType.Simple ));
		for (int i=2;i<10;i++)path.add(new Path(new Coordinate(i,4),PathType.Simple));
		for (int i=5;i<9;i++) path.add(new Path(new Coordinate(9,i) ,PathType.Simple));
		for (int i=8;i>=3;i--)path.add(new Path(new Coordinate(i,8),PathType.Simple ));
		path.add(new Path(new Coordinate(3,7) ,PathType.Simple));
		for (int i=3;i<8;i++)path.add(new Path(new Coordinate(i,6),PathType.Simple ));
		
		path.get(8).setType(PathType.Avance_7);
		path.get(14).setType(PathType.Avance_3);
		path.get(20).setType(PathType.Recul_3);
		path.get(27).setType(PathType.Recul_7);
		path.get(36).setType(PathType.Recul_7);
		path.get(30).setType(PathType.Recul_3);
		path.get(2).setType(PathType.Avance_7);
		path.get(50).setType(PathType.Recul_3);
		path.get(40).setType(PathType.Recul_3);
	}
	
	
	
	
	
	
	
	public int getWidth() {
		return width;
	}

	public MapPanel getMap() {
		return map;
	}

	public ArrayList<Path> getPath() {
		return path;
	}

	public int getDÈResult() {
		return dÈResult;
	}

	public GoosePlayer getGoosePlayer() {
		return goosePlayer;
	}

	public ArrayList<GoosePlayer> getAllGoosePlayers() {
		return allGoosePlayers;
	}

	public int getPort() {
		return port;
	}

	public String getIp() {
		return ip;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setMap(MapPanel map) {
		this.map = map;
	}

	public void setPath(ArrayList<Path> path) {
		this.path = path;
	}

	public void setDÈResult(int dÈResult) {
		this.dÈResult = dÈResult;
	}

	public void setGoosePlayer(GoosePlayer goosePlayer) {
		this.goosePlayer = goosePlayer;
		
		
	}

	public void setAllGoosePlayers(ArrayList<GoosePlayer> allGoosePlayers) {
		this.allGoosePlayers = allGoosePlayers;
		
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	} 

}