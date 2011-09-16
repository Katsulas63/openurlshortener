package org.urlshortener.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.urlshortener.core.service.IUrlShortableManager;

/**
 * 
 * @author cyague
 *
 */
@Component
public abstract class AbstractUrlWS {
	
	/**
	 * managers<br>
	 * ========
	 */
	
	@Autowired
	protected IUrlShortableManager manager;

	/**
	 * getter & setters<br>
	 * ================
	 */
	
	/**
	 * @return the manager
	 */
	public IUrlShortableManager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(IUrlShortableManager manager) {
		this.manager = manager;
	}
	 
}