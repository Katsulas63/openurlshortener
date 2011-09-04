package es.sopragroup.services.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import es.sopragroup.core.entity.UrlShortable;

public abstract class AbstractShortableUrlWS {

	protected TreeMap<Integer, UrlShortable> urlMap = new TreeMap<Integer, UrlShortable>();

	public AbstractShortableUrlWS() {
		// hardcode a single URL into the database for demonstration purposes
		UrlShortable URL = new UrlShortable();
		URL.setShortUrl("http://short");
		URL.setLongUrl("http://www.google.es");
		addUrl(URL);
	}

	protected List<UrlShortable> getUrls() {
		List<UrlShortable> URLs = new ArrayList<UrlShortable>();
		URLs.addAll(urlMap.values());
		return URLs;
	}

	protected UrlShortable getUrl(int cId) {
		return urlMap.get(cId);
	}

	protected void addUrl(UrlShortable url) {
		int id = urlMap.size();
		url.setId(id);
		urlMap.put(id, url);
	}
	 
}