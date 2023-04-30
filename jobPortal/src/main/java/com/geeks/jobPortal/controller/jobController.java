package com.geeks.jobPortal.controller;

import com.geeks.jobPortal.model.job;
import com.geeks.jobPortal.model.JobType;
import com.geeks.jobPortal.service.jobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/job")
public class jobController {

    @Autowired
    private jobService jobService;

    @GetMapping
    public List<job> getAlljob() {
        return jobService.getAll();
    }

    @PostMapping
    public String savejob(@Valid @RequestBody List<job> job) {
        return jobService.savejob(job);
    }

    @PutMapping
    public String updateIfExist(@Valid @RequestBody job job) {
        return jobService.updateIfExist(job);
    }

    @DeleteMapping("/{id}")
    public String deletejobById(@PathVariable int id) {
        return jobService.deletejobById(id);
    }

    @GetMapping("/jobType/{jobType}")
    public List<job> getAllJobsByJobType(@PathVariable String jobType) {
        JobType jobTypeEnum = JobType.valueOf(jobType);
        return jobService.getAllJobsByJobType(jobTypeEnum);
    }

    @GetMapping("/title/{title}")
    public List<job> getAlljobByTitle(@PathVariable String title) {
        return jobService.findByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/latest")
    public List<job> getLatestjob() {
        return jobService.findTop10ByOrderByAppliedDateDesc();
    }

    @GetMapping("/companyName/{companyName}")
    public List<job> getAlljobByCompanyName(@PathVariable String companyName) {
        return jobService.findByCompanyNameContainingIgnoreCase(companyName);
    }


    //Custom Queries

    @PutMapping("/update/{id}/jobType/{jobType}")
    public void updatejobTypeById(@PathVariable int id, @PathVariable com.geeks.jobPortal.model.JobType jobType ) {
        jobService.updatejobTypeById(id,jobType);
    }

    @PutMapping("/update/id/{id}/salary/{salary}")
    public void updateSalary(@PathVariable int id,@PathVariable Double salary){
        jobService.updateSalary(id,salary);
    }

    @PutMapping("id/{id}/companyName/{companyName}")
    public void updateCompanyNameById(@PathVariable int id,@PathVariable String companyName){
        jobService.updateCompanyNameById(id,companyName);
    }

    @DeleteMapping("/salary/{salary}")
    public void removejobLessThanSalary(@PathVariable Double salary){
        jobService.removejobLessThanSalary(salary);
    }

}

