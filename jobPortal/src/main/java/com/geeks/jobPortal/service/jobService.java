package com.geeks.jobPortal.service;

import com.geeks.jobPortal.model.job;
import com.geeks.jobPortal.controller.jobController;
import com.geeks.jobPortal.model.JobType;
import com.geeks.jobPortal.repo.jobDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class jobService {
    @Autowired
    jobDao jobDao;

    public List<job> getAll() {
        return (List<job>) jobDao.findAll();
    }

    public String savejob(List<job> job) {
        jobDao.saveAll(job);
        return "Added successfully";
    }

    public String updateIfExist(job job) {
        if(jobDao.existsById(job.getId())){
            jobDao.save(job);
            return "Updated successfully";
        }
        return "No such job with this jobId to update";
    }

    public String deletejobById(int id) {
        if(jobDao.existsById(id)){
            jobDao.deleteById(id);
            return "Deleted Successfully";
        }return "NO job to delete with this job id";
    }

    public List<job> getAlljobByjobType(JobType jobTypeEnum) {
        return jobDao.findByjobType(jobTypeEnum);
    }


    public List<job> findByTitleContainingIgnoreCase(String title) {
        return jobDao.findByTitleContainingIgnoreCase(title);
    }

    public List<job> findTop10ByOrderByAppliedDateDesc() {
        return jobDao.findTop10ByOrderByAppliedDateDesc();
    }

    public List<job> findByCompanyNameContainingIgnoreCase(String companyName) {
        return jobDao.findByCompanyNameContainingIgnoreCase(companyName);
    }

    @Transactional
    public void updatejobTypeById(int id, JobType jobTypeEnum) {
        String jobType= jobTypeEnum.name();
        jobDao.updatejobTypeById(id,jobType);
    }

    @Transactional
    public void updateSalary(int id, Double salary) {
        jobDao.updateSalary(id,salary);
    }

    @Transactional
    public void updateCompanyNameById(int id, String companyName) {
        jobDao.updateCompanyNameById(id,companyName);
    }

    @Transactional
    public void removejobLessThanSalary(Double salary) {
        jobDao.removejobsLessThanSalary(salary);
    }
}
