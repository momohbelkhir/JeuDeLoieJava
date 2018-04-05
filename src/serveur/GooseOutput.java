package serveur;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;

import game.GoosePlayer;

public class GooseOutput {
	 
	//
	ObjectOutputStream out;

	public GooseOutput(OutputStream out) throws IOException {

		this.out = new ObjectOutputStream(out);

	}

	public void sendPlayerList(Collection<GoosePlayer> plist) {

		try {

			out.writeObject("REFRESHLIST");
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		plist.forEach(t -> {

			try {
				System.out.println("coordinate output"+t.getPos().getX()+" "+t.getPos().getY());
				
				out.writeObject(t);
				out.reset();
				

			} catch (IOException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		try {
			out.writeObject(".");

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized void sendQuit(Collection<GoosePlayer> plist) {
		try {

			out.writeObject("QUIT");
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		plist.forEach(t -> {

			try {
				System.out.println("coordinate output"+t.getPos().getX()+" "+t.getPos().getY());
				
				out.writeObject(t);
				out.reset();
				

			} catch (IOException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		try {
			out.writeObject(".");

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
