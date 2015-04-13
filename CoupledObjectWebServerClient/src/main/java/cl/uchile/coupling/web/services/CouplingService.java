package cl.uchile.coupling.web.services;

public interface CouplingService {

	public void couple(String clientId,String ssid, String objectId);

	public void couple(String clientId,String ssid, String objectId, String initState);

	public void decouple(String clientId,String ssid, String objectId);

	public void write(String clientId,String ssid, String objectId,String messageType, String message);
	public String read(String clientId,String ssid);

	void couple(String clientId, String ssid, String objectId,
			String initState, Boolean initMessage);

	void couple(String clientId, String ssid, String objectId,
			Boolean initMessage);

}
