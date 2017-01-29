package controllers;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class MyService extends ResourceConfig {

    public MyService() {
        packages(this.getClass().getPackage().getName());
    }

}
