package com.qa.maintenanceApi.main.entities;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.qa.maintenanceApi.main.Constants;


@Component
@Scope("singleton")
public class MaintenanceBuilder {

	private String issueType;
	private String severity;
	private String additionalDetails;
	private String roomReference;
	private String dateReported;
	private String status;
	private String notes;
	private static MaintenanceBuilder builder;

	private MaintenanceBuilder() {
	}


	public static MaintenanceBuilder getMaintenanceBuilder() {
		if (builder == null) {
			builder = new MaintenanceBuilder();
		}
		return builder;
	}

	public MaintenanceBuilder notes(String notes) {
		this.notes = notes;
		return this;
	}

	public MaintenanceBuilder issueType(String issueType) {
		this.issueType = issueType;
		return this;
	}

	public MaintenanceBuilder severity(String severity) {
		this.severity = severity;
		return this;
	}

	public MaintenanceBuilder dateReported(String dateReported) {
		this.dateReported = dateReported;
		return this;
	}

	public MaintenanceBuilder status(String status) {
		this.status = status;
		return this;
	}

	public MaintenanceBuilder additionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
		return this;
	}

	public MaintenanceBuilder roomReference(String roomReference) {
		this.roomReference = roomReference;
		return this;
	}

	public static Maintenance maintenanceBuild() {
		Maintenance maintenance = new Maintenance(
				Optional.ofNullable(builder.issueType).orElse(Constants.getNaString()),
				Optional.ofNullable(builder.severity).orElse(Constants.getNaString()),
				Optional.ofNullable(builder.additionalDetails).orElse(Constants.getNaString()),
				Optional.ofNullable(builder.roomReference).orElse(Constants.getNaString()),
				Optional.ofNullable(builder.dateReported).orElse(Constants.getNaString()),
				Optional.ofNullable(builder.status).orElse(Constants.getNaString()),
				Optional.ofNullable(builder.notes).orElse(Constants.getNaString()));
		builder = new MaintenanceBuilder();
		return maintenance;
	}
}
