package controllers;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Location;
import services.RetrieveLocationFromFlickr;
import services.RetrieveLocationFromInstagram;

import javax.ws.rs.Produces;


@Path("/")
public class APIController {
    
	@Context 
	ResourceContext resourceContext;
	
	//http://localhost:8080/challenge/instagram/photo-id/BPygQo8hsk9
	@GET
    @Path("/instagram/photo-id/{photoID}")
	@Produces(MediaType.APPLICATION_JSON)
    public Location getLocationFromInstagramPhoto(@PathParam("photoID") String photoID) {
		RetrieveLocationFromInstagram retrieveLocationFromInstagram = resourceContext.getResource(RetrieveLocationFromInstagram.class);
		Location location = null;
		try {
			location = retrieveLocationFromInstagram.getLatLonFromPhotoID(photoID);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return location;
	}
	
	//http://localhost:8080/challenge/flickr/photo-id/31971576472
	@GET
    @Path("/flickr/photo-id/{photoID}")
	@Produces(MediaType.APPLICATION_JSON)
    public Location getLocationFromFlickrPhoto(@PathParam("photoID") String photoID) {
		RetrieveLocationFromFlickr retrieveLocationFromFlickr = resourceContext.getResource(RetrieveLocationFromFlickr.class);
		Location location = null;
		try {
			location = retrieveLocationFromFlickr.getLatLonFromPhotoID(photoID);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return location;
	}
	

}


