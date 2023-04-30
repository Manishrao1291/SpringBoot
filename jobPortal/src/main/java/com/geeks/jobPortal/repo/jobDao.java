package com.geeks.jobPortal.repo;

import com.geeks.jobPortal.model.job;
import com.geeks.jobPortal.model.JobType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface jobDao extends CrudRepository<job,Integer> {

    List<job> findByjobType(JobType jobType);

    List<job> findByTitleContainingIgnoreCase(String title);

    List<job> findTop10ByOrderByAppliedDateDesc();

    List<job> findByCompanyNameContainingIgnoreCase(String companyName);

    @Modifying
    @Query(value = "UPDATE job  SET job_Type = :jobType WHERE id = :id",nativeQuery = true)
    void updatejobTypeById(int id,  String jobType);


    @Modifying
    @Query(value = "update job set Salary=:salary where id=:id",nativeQuery = true)
    void updateSalary(int id, Double salary);

    @Modifying
    @Query(value = "update job set Company_Name=:companyName where id=:id",nativeQuery = true)
    void updateCompanyNameById(int id, String companyName);

    @Modifying
    @Query(value = "Delete from job where Salary <= :salary",nativeQuery = true)
    void removejobsLessThanSalary(Double salary);


}
