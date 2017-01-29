package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Location implements Serializable {
	
	private String address;
	private String latitude;
	private String longitude;
	
	public Location() {
		
	}
	
	public Location(String address, String latitude, String longitude) {
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getAddress() {
        return address;
    }
     
    public String getLatitude() {
        return latitude;
    }
 
    public String getLongitude() {
        return longitude;
    }
}
