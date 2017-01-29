package services;

import java.io.IOException;
import java.io.StringReader;

import javax.annotation.Resource;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import model.Location;
import others.Utils;

public class RetrieveLocationFromFlickr {
	
	private static final String HOST = "https://api.flickr.com/services/rest/";
	private static final String API_KEY = "f4d63eb617554f50aea45f234ca91419";
	private static final String LOCATION_TAG = "location";
	
	@Context
	ResourceContext resourceContext;
	
	public RetrieveLocationFromFlickr() {}
	
	private String buildRequestURL(String photoID) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(HOST);
		stringBuilder.append("?method=flickr.photos.geo.getLocation");
		stringBuilder.append("&api_key=" + API_KEY);
		stringBuilder.append("&photo_id=" + photoID);
		return stringBuilder.toString();
	}
	
	public Location getLatLonFromPhotoID(String photoID) throws ParserConfigurationException, SAXException, IOException {
		
		//Obtaining the response from the instagram API
    	Client client = Client.create();
    	WebResource webResource = client.resource(buildRequestURL(photoID));
    	String xml = webResource.get(String.class);
    	
    	//Converting the response to an XML and parsing the latitude and longitude values
    	String latitude = "", longitude = "", address = "";
		Element e = findLocationElement(Utils.loadXMLFromString(xml));
		latitude = getLatitudeFromXML(e);
		longitude = getLongitudeFromXML(e);
		
		//Obtaining the address from the Google maps API 
		RetrieveAddressFromGoogle retrieveAddressFromGoogle = resourceContext.getResource(RetrieveAddressFromGoogle.class);
		address = retrieveAddressFromGoogle.getAddressFromLatLon(latitude, longitude);
		
		//Returning a Location instance
		Location location = new Location(address, latitude, longitude);
    	return location;
	}
	
	private Element findLocationElement(Document xml) {
		return (Element) xml.getElementsByTagName(LOCATION_TAG).item(0);
	}

	private String getLatitudeFromXML(Element e) {
		return e.getAttribute("latitude");
	}
	
	private String getLongitudeFromXML(Element e) {
		return e.getAttribute("longitude");
	}
	
	
}
