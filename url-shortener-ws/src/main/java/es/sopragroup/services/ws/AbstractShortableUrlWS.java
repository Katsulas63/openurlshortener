package es.sopragroup.services.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import es.sopragroup.core.entity.UrlShortable;

public abstract class AbstractShortableUrlWS {

	protected TreeMap<Integer, UrlShortable> urlMap = new TreeMap<Integer, UrlShortable>();
	
	public AbstractShortableUrlWS() {
	}

	protected List<UrlShortable> getUrls() {
		List<UrlShortable> urls = new ArrayList<UrlShortable>();
		urls.addAll(urlMap.values());
		return urls;
	}

	
	
}