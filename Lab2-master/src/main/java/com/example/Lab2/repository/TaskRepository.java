package com.example.Lab2.repository;


import com.example.Lab2.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProjectId(Long id);
    Task findTaskByProjectIdAndId(Long projectId, Long id);
    @Modifying
    @Transactional
    @Query(value = "delete from task where project_id = :projectId and id = :id", nativeQuery = true)
    void deleteByProjectIdAndId(Long projectId, Long id);
    @Modifying
    @Transactional
    @Query(value = "delete from Task as t where t.project.id = :projectId and completed = true")
    void deleteCompletedTaskByProjectId(Long projectId);

    @Modifying
    @Transactional
    @Query(value = "update task set name=:new_name, description=:new_description, end_date=:new_end_date, completed=:new_completed " +
            "where id=:taskID and project_id=:projectID", nativeQuery = true)
    Integer updateTask(@Param("projectID") long projectID,
                    @Param("taskID") long taskID,
                    @Param("new_name") String new_name,
                    @Param("new_description") String new_description,
                    @Param("new_end_date") LocalDate new_end_date,
                    @Param("new_completed") boolean new_completed);
}
