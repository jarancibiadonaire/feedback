package cl.uchile.coupling.resources;

import java.io.Serializable;

public class ResourceMonitor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6349325083784249325L;
	private Integer conectedUsers=1;
	//private transient Runtime runtime;

	public void addUser() {
		this.conectedUsers++;
	}

	public void removeUser() {
		this.conectedUsers--;
	}

	public long getBytesPerUser() {
		System.out.println("CONECTED USERS: "+this.conectedUsers);
		return 1024*1024;
		/*
		if (runtime == null)
			runtime=Runtime.getRuntime();

		return runtime.maxMemory() / (2L * conectedUsers);
		*/

	}

}
