package serveur;

import java.util.Set;
import java.util.TreeMap;

import game.GoosePlayer;

public class GooseModel {

	private static TreeMap<GoosePlayer, GooseModelEvents> listPlayer = new TreeMap<>();

	private static synchronized void notifyplayerList() {

		listPlayer.values().forEach(GooseModelEvents::refreshPlayerlist);

	}

	private static synchronized void notifyplayerList2() {

		listPlayer.values().forEach(GooseModelEvents::refrechdisconnect);

	}

	//

	public static synchronized void addplayer(GoosePlayer player, HandlePlayer client) {

		listPlayer.put(player, client);
		notifyplayerList();

	}

	public static synchronized Set<GoosePlayer> getPlayerList() {
		return listPlayer.keySet();

	}

	public static synchronized void change_pos_player(GoosePlayer playerchanged, HandlePlayer handlePlayer) {
		for (java.util.Map.Entry<GoosePlayer, GooseModelEvents> entry : listPlayer.entrySet()) {

			GoosePlayer pp = entry.getKey();
			if (pp.compareTo(playerchanged) == 1 || pp.getIdplayer() == ((playerchanged.getIdplayer()) + 1)) {

				if (pp.compareTo(playerchanged) == 1) {

					pp.setPos((playerchanged.getPos()));
					pp.setMonTour(false);
					System.out.println("New coord in pp" + pp.getPos().getX() + "  " + pp.getPos().getY());
					System.out.println("je suis dans dans model (if) est mon id est :" + (playerchanged.getIdplayer()));
					System.out.println(pp.isMonTour());
				} else {
					System.out.println(
							"je suis dans dans model (else) est mon id est :" + (playerchanged.getIdplayer() + 1));
					pp.setMonTour(true);
					System.out.println(pp.isMonTour());
				}

			}

		}
		notifyplayerList();
	}

	public static synchronized void deconnectplayer(GoosePlayer deconnectedplayer, HandlePlayer handlePlayer) {
		TreeMap<GoosePlayer, GooseModelEvents> listPlayer2 = new TreeMap<>();

		for (java.util.Map.Entry<GoosePlayer, GooseModelEvents> entry : listPlayer.entrySet()) {

			GoosePlayer pp = entry.getKey();

			if (pp.compareTo(deconnectedplayer) == 1) {

				pp.setConnected(false);

				listPlayer2.put(entry.getKey(), entry.getValue());

			}

		}
		notifyplayerList2();
		listPlayer.clear();
		listPlayer = listPlayer2;

	}

	public static synchronized void  player_won(GoosePlayer playerchanged, HandlePlayer handlePlayer) {
		TreeMap<GoosePlayer, GooseModelEvents> listPlayer2 =new TreeMap<>();
		
		for(java.util.Map.Entry<GoosePlayer, GooseModelEvents> entry : listPlayer.entrySet()) {
			
					GoosePlayer pp= entry.getKey();
			
						pp.setConnected(false);
						
				        listPlayer2.put(entry.getKey(),entry.getValue());
				        			
			}
		notifyplayerList2();
		listPlayer.clear();
		listPlayer =listPlayer2;
	
}
}
