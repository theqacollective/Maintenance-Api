package com.qa.maintenanceApi.main.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qa.maintenanceApi.main.entities.Maintenance;

@Repository
public interface MaintenanceRepo extends MongoRepository<Maintenance, String> {
}
