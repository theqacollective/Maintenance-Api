package com.qa.maintenanceApi.main.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qa.maintenanceApi.main.entities.Maintenance;
import com.qa.maintenanceApi.main.repositories.MaintenanceRepo;
import com.qa.maintenanceApi.main.Constants;

@Service
public class MaintenanceService {
	
	private MaintenanceRepo maintenanceRepo;

	public MaintenanceService(MaintenanceRepo maintenanceRepo) {
		this.maintenanceRepo = maintenanceRepo; 
	}
	
	public List<Maintenance> getAllMaintenance() {
		return this.maintenanceRepo.findAll();
	}
	
	public List<Maintenance> maintenanceSearch(Maintenance maintenance){
		return this.getAllMaintenance().stream().filter(x->x.matches(maintenance)).collect(Collectors.toList());
	}
	
	public String createMaintenance(Maintenance maintenance) {
		this.maintenanceRepo.save(maintenance);
		return Constants.getCreationMessage();
	}
	
	public String deleteMaintenance(Maintenance maintenance) {
		this.maintenanceRepo.delete(maintenance);
		return Constants.getDeletionMessage();
	}
	
	public String deleteAllMaintenances() {
		this.maintenanceRepo.deleteAll();
		return Constants.getAllDeletionMessage();
	}
	
	public String updateMaintenance(Long id, Maintenance updateMaintenance) {
		Maintenance maintenanceToUpdate = this.maintenanceRepo.findById(id).orElse(new Maintenance());
		maintenanceToUpdate.setIssueType(Optional.ofNullable(updateMaintenance.getIssueType()).orElse(Optional.ofNullable(maintenanceToUpdate.getIssueType()).orElse(Constants.getNaString())));
		maintenanceToUpdate.setSeverity(Optional.ofNullable(updateMaintenance.getSeverity()).orElse(Optional.ofNullable(maintenanceToUpdate.getSeverity()).orElse(Constants.getNaString())));
		maintenanceToUpdate.setAdditionalDetails(Optional.ofNullable(updateMaintenance.getAdditionalDetails()).orElse(Optional.ofNullable(maintenanceToUpdate.getAdditionalDetails()).orElse(Constants.getNaString())));
		maintenanceToUpdate.setStatus(Optional.ofNullable(updateMaintenance.getStatus()).orElse(Optional.ofNullable(maintenanceToUpdate.getStatus()).orElse(Constants.getNaString())));
		maintenanceToUpdate.setDateReported(Optional.ofNullable(updateMaintenance.getDateReported()).orElse(Optional.ofNullable(maintenanceToUpdate.getDateReported()).orElse(Constants.getNaString())));
		maintenanceToUpdate.setStatus(Optional.ofNullable(updateMaintenance.getStatus()).orElse(Optional.ofNullable(maintenanceToUpdate.getStatus()).orElse(Constants.getNaString())));
		maintenanceToUpdate.setNotes(Optional.ofNullable(updateMaintenance.getNotes()).orElse(Optional.ofNullable(maintenanceToUpdate.getNotes()).orElse(Constants.getNaString())));
		this.maintenanceRepo.saveAndFlush(maintenanceToUpdate);
		return Constants.getUpdateMesssage();
	}
}
