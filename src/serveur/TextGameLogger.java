package serveur;

public class TextGameLogger implements InterfaceGameLogger{

	@Override
	public void playerConnected(String ip) {
		// TODO Auto-generated method stub
		System.out.println("Client "+ip+" connecté");
	}

	@Override
	public void playerDisconnected(String ip, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientGotName(String ip, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMessage(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}

}
