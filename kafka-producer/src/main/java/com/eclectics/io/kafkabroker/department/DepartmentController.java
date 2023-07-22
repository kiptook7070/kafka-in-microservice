package com.eclectics.io.kafkabroker.department;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController

@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Department department) {
        try {
            return ResponseEntity.ok().body(departmentService.create(department));
        } catch (Exception e) {
            log.info("Caught Error" + e);
            return null;
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        try {
            return ResponseEntity.ok().body(departmentService.all());
        } catch (Exception e) {
            log.info("Caught Error" + e);
            return null;
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(departmentService.find(id));
        } catch (Exception e) {
            log.info("Caught Error" + e);
            return null;
        }
    }
}
