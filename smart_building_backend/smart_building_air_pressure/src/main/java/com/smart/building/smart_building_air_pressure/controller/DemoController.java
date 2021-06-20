package com.smart.building.smart_building_air_pressure.controller;

import com.smart.building.smart_building_air_pressure.model.Control;
import com.smart.building.smart_building_air_pressure.model.Operation;
import com.smart.building.smart_building_air_pressure.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    OperationRepository operationRepository;

    @GetMapping
    public String welcome() {
        return "Hello";
    }

    @GetMapping("/other")
    public String other() {
        return "other";
    }

    @PostMapping
    public String post() {
        return "Post";
    }

    @PostMapping("/save")
    public Operation createOpt(@RequestBody @Valid Operation operation) {
        operation.setControl(Control.Close);
        operationRepository.save(operation);
        return operation;
    }

    @GetMapping("/control")
    public List<Operation> findByControl(@RequestParam("control") Control control) {
        return operationRepository.findAllByControl(control);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOperation(@PathVariable(value = "id") String id) {
        operationRepository.deleteById(id);
        return ResponseEntity.ok("Delete");
    }

    @PutMapping("/update")
    public ResponseEntity<?> deleteOperation(@RequestBody @Valid Operation operation) {
        operationRepository.save(operation);
        return ResponseEntity.ok("Update");
    }

    @GetMapping("/all")
    public List<Operation> getAllOpt() {
        return operationRepository.findAll();
    }
}
