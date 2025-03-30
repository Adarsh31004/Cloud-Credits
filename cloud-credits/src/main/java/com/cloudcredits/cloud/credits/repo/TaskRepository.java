package com.cloudcredits.cloud.credits.repo;

import com.cloudcredits.cloud.credits.model.Task;
import com.cloudcredits.cloud.credits.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByUser(Users users);
    List<Task> findByStatus(String status);


}
