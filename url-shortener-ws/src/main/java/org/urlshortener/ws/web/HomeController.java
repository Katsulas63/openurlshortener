package org.urlshortener.ws.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.document.mongodb.MongoDbFactory;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired(required = false)
	MongoDbFactory mongoDbFactory;

	@Autowired(required = false)
	MongoTemplate mongoTemplate;

	@Autowired(required = false)
	@Qualifier(value = "serviceProperties")
	Properties serviceProperties;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<String> services = new ArrayList<String>();
		if (mongoDbFactory != null) {
			services.add("MongoDB: " + mongoDbFactory.getDb().getMongo().getAddress());
		}
		
		model.addAttribute("services", services);
		model.addAttribute("serviceProperties", getServicePropertiesAsList());

		String environmentName = (System.getenv("VCAP_APPLICATION") != null) ? "Cloud" : "Local";
		model.addAttribute("environmentName", environmentName);
		return "home";
	}

	@RequestMapping("/env")
	public void env(HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("System Properties:");
		for (Map.Entry<Object, Object> property : System.getProperties()
				.entrySet()) {
			out.println(property.getKey() + ": " + property.getValue());
		}
		out.println();
		out.println("System Environment:");
		for (Map.Entry<String, String> envvar : System.getenv().entrySet()) {
			out.println(envvar.getKey() + ": " + envvar.getValue());
		}
	}

	@RequestMapping("/service-properties")
	public void services(HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		if (serviceProperties != null) {
			out.println("Cloud Service Properties:");
			// Map envMap = System.getenv();
			for (Object key : serviceProperties.keySet()) {
				out.println(key + ": " + serviceProperties.get(key));
			}
		} else {
			out.println("No Cloud Service Properties found.  Check configuration file for <cloud:service-properties/> element");
		}
		out.println(")<a href=\"/\">Return to previous page.</a>");
		out.println();
	}

	private List<String> getServicePropertiesAsList() {
		List<String> propList = new ArrayList<String>();
		if (serviceProperties != null) {
			for (Object key : serviceProperties.keySet()) {
				propList.add(key + ": " + serviceProperties.get(key));
			}
		}
		return propList;
	}

}
