package com.qa.maintenanceApi.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.maintenanceApi.main.entities.Maintenance;

@Repository
public interface MaintenanceRepo extends JpaRepository<Maintenance, Long> {
}
