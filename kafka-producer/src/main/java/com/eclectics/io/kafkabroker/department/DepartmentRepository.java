package com.eclectics.io.kafkabroker.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "SELECT id AS Id, departmentCode AS DepartmentCode FROM department ORDER BY departmentCode DESC LIMIT 1", nativeQuery = true)
    Optional<getDepartmentData> findDepartment();

     interface getDepartmentData {
        Long getId();

        String getDepartmentCode();
    }
}
