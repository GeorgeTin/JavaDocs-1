package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by Aimandis on 7/18/2016.
 */
public interface JobService {
    public List<Job> findAllJobs();
    public Job findOneJob(String id);
    public void deleteOneJob(String id);
    public void addJob(Job emp);
    public void editJob(Job emp);
}
