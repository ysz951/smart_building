package com.smart.building.smart_building_air_pressure.repository;

import com.smart.building.smart_building_air_pressure.model.Control;
import com.smart.building.smart_building_air_pressure.model.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OperationRepository extends MongoRepository<Operation, String> {
    List<Operation> findAllByControl(Control control);
}
