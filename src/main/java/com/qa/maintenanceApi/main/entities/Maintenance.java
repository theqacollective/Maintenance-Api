package com.qa.maintenanceApi.main.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.qa.maintenanceApi.main.Constants;

@Entity
public class Maintenance {

	@Id
	@GeneratedValue
	private long id;
	private String issueType;
	private String severity;
	private String additionalDetails;
	private String roomReference;
	private String dateReported;
	private String status;
	private String notes;

	public Maintenance(String issueType, String severity, String additionalDetails, String roomReference,
			String dateReported, String status, String notes) {
		
		this.issueType = issueType;
		this.severity = severity;
		this.additionalDetails = additionalDetails;
		this.roomReference = roomReference;
		this.dateReported = dateReported;
		this.status = status;
		this.notes = notes;
	}

	public Maintenance() {

	}

	public String getStatus() {
		return status;
	}

	public String getNotes() {
		return notes;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public long getId() {
		return id;
	}


	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public String getRoomReference() {
		return roomReference;
	}

	public void setRoomReference(String roomReference) {
		this.roomReference = roomReference;
	}

	public boolean matches(Maintenance maintenance) {
		// This is a project standard.
		Boolean roomCheck = this.getRoomReference().contentEquals(maintenance.getRoomReference());
		Boolean roomNull = maintenance.getRoomReference().contentEquals(Constants.getNaString());
		Boolean issueCheck = this.getIssueType().contentEquals(maintenance.getIssueType());
		Boolean issueNull = maintenance.getIssueType().contentEquals(Constants.getNaString());
		Boolean statusCheck = this.getStatus().contentEquals(maintenance.getStatus());
		Boolean statusNull = maintenance.getStatus().contentEquals(Constants.getNaString());
		Boolean severityCheck = this.getSeverity().contentEquals(maintenance.getSeverity());
		Boolean severityNull = maintenance.getSeverity().contentEquals(Constants.getNaString());
		Boolean dateCheck = this.getDateReported().contentEquals(maintenance.getDateReported());
		Boolean dateNull = maintenance.getDateReported().contentEquals(Constants.getNaString());

		roomCheck = roomCheck || roomNull;
		issueCheck = issueCheck || issueNull;
		statusCheck = statusCheck || statusNull;
		dateCheck = dateCheck || dateNull;
		severityCheck = severityCheck || severityNull;
		return (roomCheck && issueCheck && statusCheck && dateCheck && severityCheck);
	}

	public String getDateReported() {
		return dateReported;
	}

	public void setDateReported(String dateReported) {
		this.dateReported = dateReported;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
