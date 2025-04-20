package com.rms.service;

import com.rms.model.ApplicationEntity;
import com.rms.model.JobEntity;
import com.rms.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
   List<ApplicationEntity> findByUser(UserEntity user);
List<ApplicationEntity> findByJobId(Long jobId);
Optional<ApplicationEntity> findByUserAndJob(UserEntity user, JobEntity job); // 👈 for reapply prevention

}
