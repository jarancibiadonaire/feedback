package cl.uchile.coupling.web.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.uchile.cos.session.Client;
import cl.uchile.cos.session.SessionManager;
import cl.uchile.cos.sync.Message;
import cl.uchile.cos.sync.adapters.Adapter;
import cl.uchile.coupling.messaging.JSONBufferAdapter;
import cl.uchile.coupling.resources.MessageFactory;
@Service("couplingService")
public class CouplingServiceImpl implements CouplingService{
	
	@Autowired
	private MessageFactory messageFactory;
	@Autowired
	private SessionManager sessionManager;
	private static final Logger logger = LoggerFactory
			.getLogger(CouplingServiceImpl.class);
	@Override
	public void couple(String clientId, String ssid,String objectId){
		this.couple(clientId,ssid, objectId,null,null);
	}
	@Override
	public void couple(String clientId, String ssid,String objectId,Boolean initMessage){
		this.couple(clientId,ssid, objectId,null,initMessage);
	}
	@Override
	public void couple(String clientId,String ssid,String objectId,String initState){
		this.couple(clientId,ssid, objectId,initState,null);
	}
	@Override
	public void couple(String clientId,String ssid,String objectId,String initState,Boolean initMessage){
		Client client = sessionManager.getClientById(Long.parseLong(clientId));
		Adapter adapter = client.getAdapter(ssid);
		Message message = messageFactory.createFromJSON(initState);
		if (adapter != null) {
			if (message != null) {
				adapter.couple(objectId, message);
				logger.debug("cliente " + client.getId()
						+ " a acoplado el objeto " + objectId
						+ " en la sesion " + ssid + " con estado inicial "
						+ initState);
			} else {
				if(initMessage==null)
					adapter.couple(objectId);
				else
					adapter.couple(objectId,initMessage);
				logger.debug("cliente " + client.getId()
						+ " a acoplado el objeto " + objectId
						+ " en la sesion " + ssid);
			}
		} else
			logger.warn("cliente " + client.getId()
					+ " no se ha unido a la sesion " + ssid);
	}
	@Override
	public void decouple(String clientId, String ssid,String objectId){
		Client client = sessionManager.getClientById(Long.parseLong(clientId));
		Adapter adapter = client.getAdapter(ssid);
		if (adapter != null) {
			adapter.decouple(objectId);
			logger.debug("cliente " + client.getId()
					+ " ha desacoplado el objeto " + objectId
					+ " en la sesion " + ssid);
		} else
			logger.debug("cliente " + client.getId()
					+ " no se ha unido a la sesion " + ssid);
	}
	@Override
	public void write(String clientId,String ssid,String objectId,String messageType,String message){
		Client client = sessionManager.getClientById(Long.parseLong(clientId));
		if(client==null)
			return;
		Adapter adapter = client.getAdapter(ssid);
		if (adapter != null) {
			adapter.sendToServer(this.messageFactory.createFromJSON(objectId,messageType,message));
			logger.debug(message);
		} else
			logger.debug("cliente " + client.getId()
					+ " no se ha unido a la sesion " + ssid);
	}
	@Override
	public String read(String clientId,String ssid){
		Client client = sessionManager.getClientById(Long.parseLong(clientId));
		if(client==null)
			return "";
		JSONBufferAdapter adapter = (JSONBufferAdapter) client.getAdapter(ssid);
		if(adapter==null){
			logger.debug("cliente " + client.getId()
					+ " no se ha unido a la sesion " + ssid);
			return "";
		}
		return adapter.getMessages();
	}

}
