package serveur;

public interface InterfaceGameLogger {
	public void playerConnected(String ip);
	public void playerDisconnected(String ip, String name);
	public void clientGotName(String ip, String name);
	public void printMessage(String msg);
	
	
}
