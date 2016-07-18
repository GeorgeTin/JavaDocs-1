package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyCreateParam;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobService;
import ro.teamnet.zth.appl.service.JobServiceImpl;

import java.util.List;

/**
 * Created by Aimandis on 7/14/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {

    private final JobService jobService = new JobServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs(){
        return jobService.findAllJobs();
    }

    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(name="id")String id){
        return jobService.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public Job deleteOneJob(@MyRequestParam(name="id")String id){
        //System.out.println(id);
        Job job = jobService.findOneJob(id);
        jobService.deleteOneJob(id);
        return job;
    }

    @MyRequestMethod(urlPath = "/create", methodType = "POST")
    public void addJob(@MyCreateParam(name="job")Job job){
        //System.out.println(job.getJobTitle());
        jobService.addJob(job);
    }

    @MyRequestMethod(urlPath = "/update", methodType = "POST")
    public void updateJob(@MyCreateParam(name="job")Job job){
        //System.out.println(job.getJobTitle());
        jobService.editJob(job);
    }

}
