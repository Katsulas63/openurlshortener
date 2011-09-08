package org.urlshortener.core.dao.mongodb;

import static org.springframework.data.document.mongodb.query.Criteria.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.document.mongodb.CollectionCallback;
import org.springframework.data.document.mongodb.MongoDbFactory;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Component;
import org.urlshortener.core.dao.IUrlShortableDAO;
import org.urlshortener.core.entity.UrlShortable;

import com.mongodb.DBCollection;
import com.mongodb.MongoException;


@Component("urlShortableDAO")
public class UrlShortableDAOImpl implements IUrlShortableDAO {
	
	@SuppressWarnings("unused")
	@Autowired(required=true)
	private MongoDbFactory mongoDbFactory;

	@Autowired(required=true)
	private MongoTemplate mongoTemplate;
	
	public UrlShortable saveUrl(UrlShortable url) {
		mongoTemplate.save(url);
		
		return url;
	}

	protected List<UrlShortable> getUrls(String fieldName, String value) {
		final Criteria criteria = where(fieldName).is(value);
		final Query query = new Query(criteria);
		List<UrlShortable> urls = mongoTemplate.find(query, UrlShortable.class);
		return urls;
	}

	/* (non-Javadoc)
	 * @see es.sopragroup.core.dao.mongodb.IUrlShortableDAO#getCountUrls()
	 */
	@Override
	public Long getCountUrls() {
		Long result = 0L;

		result = mongoTemplate.execute(UrlShortable.class,
				new CollectionCallback<Long>() {
					@Override
					public Long doInCollection(DBCollection collection)
							throws MongoException, DataAccessException {
						return collection.count();
					}
				});

		return result;
	}

	protected UrlShortable getUrl(String fieldName, String value) {
		UrlShortable result = null;
		List<UrlShortable> urls = getUrls(fieldName, value);

		if (urls != null && urls.size() == 1) {
			result = urls.get(0);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see es.sopragroup.core.dao.mongodb.IUrlShortableDAO#getUrlByShortUrl(java.lang.String)
	 */
	@Override
	public UrlShortable getUrlByShortUrl(String shortUrl) {
		final UrlShortable result = getUrl("shortUrl", shortUrl);
		return result;
	}

	/* (non-Javadoc)
	 * @see es.sopragroup.core.dao.mongodb.IUrlShortableDAO#getUrlByLongUrl(java.lang.String)
	 */
	@Override
	public UrlShortable getUrlByLongUrl(String longUrl) {
		final UrlShortable result = getUrl("longUrl", longUrl);
		return result;
	}

	
	/**
	 * getter & setters<br>
	 * ================
	 */
	

	/**
	 * @param mongoDbFactory the mongoDbFactory to set
	 */
	public void setMongoDbFactory(MongoDbFactory mongoDbFactory) {
		this.mongoDbFactory = mongoDbFactory;
	}

	/**
	 * @param mongoTemplate the mongoTemplate to set
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
