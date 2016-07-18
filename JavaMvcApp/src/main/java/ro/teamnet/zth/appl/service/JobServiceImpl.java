package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by Aimandis on 7/18/2016.
 */
public class JobServiceImpl implements JobService{
    @Override
    public List<Job> findAllJobs() {
        return new JobDao().getAllJobs();
    }

    @Override
    public Job findOneJob(String id) {
        return new JobDao().getJobById(id);
    }

    @Override
    public void deleteOneJob(String id) {

            new JobDao().deleteJob(findOneJob(id));

    }

    @Override
    public void addJob(Job job) {
        //System.out.println("a ajuns");
            new JobDao().insertJob(job);
    }

    @Override
    public void editJob(Job job) {
        new JobDao().updateJob(job);
    }
}
