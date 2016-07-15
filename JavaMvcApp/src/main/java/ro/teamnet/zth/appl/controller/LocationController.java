package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Aimandis on 7/14/2016.
 */

@MyController(urlPath = "/locations")
public class LocationController {

    @MyRequestMethod(urlPath = "/all")
    public String getAllLocations(){
        return "All locations";
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneLocation(){
        return "One random location";
    }
}
