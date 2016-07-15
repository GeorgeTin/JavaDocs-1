package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Aimandis on 7/14/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {

    @MyRequestMethod(urlPath = "/all")
    public String getAllJObs(){
        return "All Jobs";
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneJob(){
        return "One random job";
    }
}
