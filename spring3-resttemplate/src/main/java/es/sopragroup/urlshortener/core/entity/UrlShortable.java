package es.sopragroup.urlshortener.core.entity;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UrlShortable {
	private int id;
	private String shortUrl;
	private String longUrl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
}
