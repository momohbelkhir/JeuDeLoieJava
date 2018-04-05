package serveur;

import java.util.Collection;

import game.GoosePlayer;

public interface GooseProtocol {

//
	default void addPlayer(GoosePlayer player) {}
	
	default void sendListPlayr(Collection <GoosePlayer> ulist) {}
	
	

	default void change_pos_player(GoosePlayer player){}

	default void deconnectplayer(GoosePlayer player){}
	
	default void player_won(GoosePlayer player){}
}
