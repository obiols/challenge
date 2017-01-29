package services;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import others.Utils;

public class RetrieveAddressFromGoogle {
	
	private static final String HOST = "https://maps.googleapis.com/maps/api/geocode/xml";
	private static final String API_KEY = "AIzaSyAbtkqYFVsYciyRk8dV7IIuazXe0E-llIk";
	
	public RetrieveAddressFromGoogle() {}
	
	private String buildRequestURL(String latitude, String longitude) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(HOST);
		stringBuilder.append("?latlng="+ latitude +","+ longitude);
		stringBuilder.append("&sensor=true");
		stringBuilder.append("&key=" + API_KEY);
		return stringBuilder.toString();
	}
	
	public String getAddressFromLatLon(String latitude, String longitude) throws ParserConfigurationException, SAXException, IOException {
    	Client client = Client.create();
    	WebResource webResource = client.resource(buildRequestURL(latitude, longitude));
    	String xml = webResource.get(String.class);
    	Document document = Utils.loadXMLFromString(xml);
		String address = document.getElementsByTagName("formatted_address").item(0).getTextContent();
		return address;
	}
}
