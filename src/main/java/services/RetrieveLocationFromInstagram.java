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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import model.Location;

public class RetrieveLocationFromInstagram {
	
	private static final String HOST = "https://api.instagram.com/v1/media/";
	private static final String API_KEY = "4524690966.4b15a02.fa71868e41354b539b1171e097df4267";
	
	@Context
	ResourceContext resourceContext;
	
	public RetrieveLocationFromInstagram() {}
	
	private String buildRequestURL(String photoID) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(HOST);
		stringBuilder.append("shortcode/" + photoID);
		stringBuilder.append("?access_token=" + API_KEY);
		return stringBuilder.toString();
	}
	
	public Location getLatLonFromPhotoID(String photoID) throws ParserConfigurationException, SAXException, IOException {
		
		//Obtaining the response from the instagram API
    	Client client = Client.create();
    	WebResource webResource = client.resource(buildRequestURL(photoID));
    	String xml = webResource.get(String.class);
    	
    	//Converting the response to a JSON object and parsing the latitude and longitude values
    	JsonElement jelement = new JsonParser().parse(xml);
	    JsonObject  jobject = jelement.getAsJsonObject();
	    jobject = jobject.getAsJsonObject("data").getAsJsonObject("location");
	    String latitude = jobject.get("latitude").toString();
	    String longitude = jobject.get("longitude").toString();
	    
	    //Obtaining the address from the Google maps API 
	    RetrieveAddressFromGoogle retrieveAddressFromGoogle = resourceContext.getResource(RetrieveAddressFromGoogle.class);
	    String address = retrieveAddressFromGoogle.getAddressFromLatLon(latitude, longitude);

	    //Returning a Location instance
	    Location location = new Location(address, latitude, longitude);
    	return location;
	}
	
}
