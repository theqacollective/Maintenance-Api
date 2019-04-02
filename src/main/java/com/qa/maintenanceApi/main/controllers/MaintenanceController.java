package com.qa.maintenanceApi.main.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.maintenanceApi.main.Constants;
import com.qa.maintenanceApi.main.entities.Maintenance;
import com.qa.maintenanceApi.main.entities.MaintenanceBuilder;
import com.qa.maintenanceApi.main.services.MaintenanceService;

@RestController
@CrossOrigin
public class MaintenanceController {

	private MaintenanceService maintenanceService;

	public MaintenanceController(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	@PostMapping(Constants.CREATE_URL)
	public String createMaintenance(@RequestBody Maintenance maintenance) {
		return this.maintenanceService.createMaintenance(maintenance);
	}

	@GetMapping(Constants.GET_ALL_URL)
	public List<Maintenance> getAllMaintenances() {
		return this.maintenanceService.getAllMaintenance();
	}

	@GetMapping(Constants.SEARCH_URL)
	public List<Maintenance> maintenanceSearch(String issueType, String severity, String roomReference,
			String dateReported, String status) {

		MaintenanceBuilder.getMaintenanceBuilder().issueType(issueType).severity(severity).roomReference(roomReference)
				.dateReported(dateReported);

		Maintenance maintenance = MaintenanceBuilder.maintenanceBuild();
		return this.maintenanceService.maintenanceSearch(maintenance);
	}

	@DeleteMapping(Constants.DELETE_ALL_URL)
	public String deleteAllMaintenances() {
		return this.maintenanceService.deleteAllMaintenances();
	}

	@DeleteMapping(Constants.DELETE_URL)
	public String deleteMaintenance(String issueType, String severity, String roomReference,
			String dateReported, String status) {

		List<Maintenance> maintenances = this.maintenanceSearch(issueType, severity,roomReference,
				dateReported, status);

		for (int i = 0; i < maintenances.size(); i++) {
			this.maintenanceService.deleteMaintenance(maintenances.get(i));
		}
		return Constants.getDeletionMessage();
	}

	@PutMapping(Constants.UPDATE_URL)
	public String updateMaintenance(@PathVariable("id") long id, @RequestBody Maintenance maintenanceUpdate) {
		return this.maintenanceService.updateMaintenance(id, maintenanceUpdate);
	}

}
