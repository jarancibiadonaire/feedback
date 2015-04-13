package cl.uchile.coupling.resources;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import cl.uchile.cos.sync.Message;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageFactory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4823224497133354544L;

	transient private ObjectMapper mapper = new ObjectMapper();


	public Message createFromJSON(String objectId,String messageType,CharSequence json) {
		if(json==null || json.length()==0)
			return null;
		JSONMessage event = new JSONMessage(objectId,messageType);
		event.setJson(json.toString());
		return event;
	}
	public Message createFromJSON(CharSequence json) {
		if(json==null)
			return null;
		JSONMessage event = new JSONMessage();
		event.setJson(json.toString());
		return event;
	}

	public Message parseJSON(CharSequence json) {
		try {
			return getMapper().readValue(json.toString(), Message.class);
		} catch (IOException e) {
			// TODO logging
			e.printStackTrace();
			return null;
		}
	}

	public void toJSON(Message event, Writer writer) {
		if (JSONMessage.class.isInstance(event)
				&& !((JSONMessage) event).isModified()) {
			try {
				writer.write(((JSONMessage) event).getJson());
			} catch (IOException e) {
				// TODO logging
				e.printStackTrace();
			}
		} else {
			this.eventToJSON(event, writer);
		}
	}

	private void eventToJSON(Message event, Writer writer) {
		try {
			getMapper().writeValue(writer, event);
		} catch (IOException e) {
			// TODO logging
			e.printStackTrace();

		}
	}
	private final ObjectMapper getMapper(){
		if(mapper==null)
			mapper=new ObjectMapper();
		return mapper;
	}

}
