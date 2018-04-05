package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import game.GoosePlayer;
import view.GooseGui;

public class GooseSocket {

	private Socket socket;

	private ObjectInputStream in;

	private ObjectOutputStream out;

	private int port;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private String ipAdresse;

	private GooseGui gooseGui;
	
	private Thread inputThread; 

	public GooseSocket(int port, String ip, GooseGui gooseGui) throws UnknownHostException, IOException {

		this.gooseGui = gooseGui;
		this.port = port;
		this.ipAdresse = ip;
		socket = new Socket(ipAdresse, port);
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());

	}

	public void addPlayerToServer(GoosePlayer goosePlayer) throws IOException {

		// send instruction to add new Player
		out.writeObject("ADDCLIENT");

		out.reset();

		// send the object goosePlayer to the sever
		out.writeObject(goosePlayer);
      
		out.reset();

	}
	
	public void change_pos_player(GoosePlayer goosePlayer) throws IOException {
      System.out.println("envois du mot cle et du player vers le serveur ");
		// send instruction to change position Player
		out.writeObject("CHANGEPOS");

		out.reset();

		// send the object goosePlayer changed position to the sever
		out.writeObject(goosePlayer);

		out.reset();

	}
	
	public void player_won(GoosePlayer goosePlayer) throws IOException {
	      System.out.println("envois du mot cle et du player vers le serveur ");
			// send instruction to change position Player
			out.writeObject("WON");

			out.reset();

			// send the object goosePlayer changed position to the sever
			out.writeObject(goosePlayer);

			out.reset();

		}
	
	
	public void DeconnectPlayer(GoosePlayer goosePlayer) throws IOException {

		// send instruction to deconnect Player
		System.out.println("sent DECONNECT to serveur");
	
		out.writeObject("DECONNECT");

		out.reset();

		// send the object goosePlayer deconnected to the sever
		out.writeObject(goosePlayer);

		out.reset();
		
	}
	
	
	
	

	public void connect() {

		try {

			// ip différente pour chaque client
			socket = new Socket("localhost", 1234);
			out = new ObjectOutputStream(socket.getOutputStream());
			
			in = new ObjectInputStream(socket.getInputStream());
			gooseGui.getGoosePlayer().setConnected(true);
			System.out.println(gooseGui.getGoosePlayer().getNamePlayer()+" is Connected !");
			startInputThread();
		} catch (UnknownHostException e) {
			System.err.println("host erreur ");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Port non disponible ");
			System.exit(1);
		}
	}

	public void startInputThread() {

		inputThread = new Thread() {

			Object obj;
			
			GoosePlayer player = null;

			ArrayList<GoosePlayer> playerList = new ArrayList<>();

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			public void run() {

				while (socket.isConnected()){
					try {
                        
				String input = (String) in.readObject();

						switch (input) {

						case "REFRESHLIST":
							playerList.clear();
							boolean typeInput = true;

							while (typeInput) {
								try {

									obj = null;
							 		player = null;

									obj =in.readObject();

									if (obj instanceof GoosePlayer ) {
                                       
										player = (GoosePlayer) obj;
                             
								
			
										playerList.add(player); 
										
                                     
											
										}
									else {
										typeInput = false;
									}

								} catch (ClassNotFoundException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
								
							gooseGui.setAllGoosePlayers(playerList);
							
							
							try {
								new Thread();
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block 
								e.printStackTrace();
							}
						    gooseGui.getMap().repaint();
						    
						    break;
						case  "QUIT":
							System.out.println("je dans Quit client retour");
							playerList.clear();
							typeInput = true;

							while (typeInput) {
								try {

									obj = null;	
							 		player = null;

									obj =in.readObject();

									if (obj instanceof GoosePlayer ) {
                                       
										player = (GoosePlayer) obj;
                                      if (player.isConnected()){
								
			                          System.out.println("quit connecter-------");
										playerList.add(player);
										
                                      }
											
										}
									else {
										System.out.println("la taille apres de connection"+playerList.size());
										typeInput = false;
									}

								} catch (ClassNotFoundException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
								
							       
							
							
							
							gooseGui.setAllGoosePlayers(playerList);
							System.err.println("apres deconnection -------"+gooseGui.getAllGoosePlayers().size());
							
							try {
								new Thread();
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    gooseGui.getMap().repaint();
							
							
							
							
							break;
						default:
						}//
		
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
						
					}
					
					
				}
				
				
			}
			
		};
		
		
		
			inputThread.setPriority(Thread.MAX_PRIORITY);
			inputThread.start();//
		
		
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public GooseGui getGooseGui() {
		return gooseGui;
	}

	public void setGooseGui(GooseGui gooseGui) {
		this.gooseGui = gooseGui;
	}
	

}
