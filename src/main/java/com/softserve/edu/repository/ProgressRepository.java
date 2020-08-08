package com.softserve.edu.repository;

import com.softserve.edu.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    @Query(value = "select * from progress where trainee_id =?1 and task_id in " +
            "(select id from task where sprint_id in " +
            "(select id from sprint where marathon_id =?2))", nativeQuery = true)
    List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId);

    @Query(value = "select * from progress where trainee_id =?1 and task_id in " +
            "(select id from task where sprint_id =?2)", nativeQuery = true)
    List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId);
}
