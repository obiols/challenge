
#Challenge

This project offers a service for finding geolocated places from images or media content uploaded on Flickr and Instagram. 

* [Jersey](https://jersey.java.net/) - REST service.
* [Gradle](https://gradle.org/) - Build tool and dependency management.
* [Gretty](https://github.com/akhikhl/gretty) - Plugin for running the webappp on jetty.


#Endpoints
There are two endpoints, one for instagram and other for flickr.
The instagram uses as parameter the shortcode of the media content. 
Recently Instagram API has added the sandbox mode, and changed the permissions to extract data from their users. So I can only extract data from my own account or users that have accepted my invite. I would suggest to use my [instagram account](https://www.instagram.com/albertobiolsm/) for testing:


###Instagram endpoint:
```
http://localhost:8080/challenge/instagram/photo-id/BPygQo8hsk9
```
Use the following shortcodes:
* https://www.instagram.com/p/ **BPzz2DvBXbs** /?taken-by=albertobiolsm
* https://www.instagram.com/p/ **BPygQo8hsk9** /?taken-by=albertobiolsm

###Flickr endpoint:
```
http://localhost:8080/challenge/flickr/photo-id/31971576472
```
Use any photo id extracted from flickr, which has lat lon values.

#APIs used
For extracting the latitude/longitude values from media content:
* [Instagram](https://www.instagram.com/developer/endpoints/media/#get_media_by_shortcode)
* [Flickr](https://www.flickr.com/services/api/flickr.photos.geo.getLocation.html)

For obtaining the address from a latitude/longitude values:
* [Google Maps API](https://developers.google.com/maps/?hl=es-419) 


##Building and running

### Building WAR file
```
./gradle war
```

### Running the service 
```
./gradle jettyRun
```
