package com.example.Lab2.repository;

import com.example.Lab2.dto.UncomplitedDTO;
import com.example.Lab2.entity.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "select * from project where " +
            "name ilike '%' || :str || '%' or description ilike '%' || :str || '%'", nativeQuery = true)
    List<Project> findBySearch(@Param("str") String str);

    @Query(value = "select p.id, count(t.id) from project as p " +
            "left join task as t on p.id = t.project_id and t.completed = false group by p.id",nativeQuery = true)
    List<UncomplitedDTO> findUncompletedTasks();

    @Modifying
    @Transactional
    @Query(value = "update project set name = :new_name, description = :new_description, start_date = :new_start_date, end_date = :new_end_date where id = :projectID", nativeQuery = true)
    Integer updateProject(long projectID,
                          String new_name,
                          String new_description,
                          LocalDate new_start_date,
                          LocalDate new_end_date);
}