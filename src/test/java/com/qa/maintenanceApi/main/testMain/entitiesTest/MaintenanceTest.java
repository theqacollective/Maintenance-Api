package com.qa.maintenanceApi.main.testMain.entitiesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.maintenanceApi.main.Constants;
import com.qa.maintenanceApi.main.entities.Maintenance;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintenanceTest {

	
	private Maintenance maintenance;
	
	@Test
	public void superConstructorTest() {
		this.maintenance = new Maintenance();
		assertThat(this.maintenance).isNotNull();
	}

	@Test
	public void fullConstructorTest() {
		this.maintenance = new Maintenance(Constants.getNaString(), Constants.getNaString(), Constants.getNaString(),
				Constants.getNaString(), Constants.getNaString(), Constants.getNaString(), Constants.getNaString());
		assertThat(this.maintenance.matches(Constants.getDefaultBuilderMaintenance())).isEqualTo(true);
	}

	@Test
	public void getterTest() {
		assertNull(Constants.getNullMaintenance().getIssueType());
		assertNull(Constants.getNullMaintenance().getSeverity());
		assertNull(Constants.getNullMaintenance().getAdditionalDetails());
		assertNull(Constants.getNullMaintenance().getRoomReference());
		assertNull(Constants.getNullMaintenance().getDateReported());
	 	assertNull(Constants.getNullMaintenance().getStatus());
		assertNull(Constants.getNullMaintenance().getNotes());
		assertThat(Constants.getNullMaintenance().getId()).isEqualTo(0);
	}

	@Test
	public void setterTest() {
		this.maintenance = Constants.getNullMaintenance();
		this.maintenance.setIssueType(Constants.getNaString());
		this.maintenance.setSeverity(Constants.getNaString());
		this.maintenance.setRoomReference(Constants.getNaString());
		this.maintenance.setDateReported(Constants.getNaString());
		this.maintenance.setStatus(Constants.getNaString());
		this.maintenance.setRoomReference(Constants.getNaString());
		this.maintenance.setNotes(Constants.getNaString());
		assertThat(this.maintenance.matches(Constants.getDefaultBuilderMaintenance())).isEqualTo(true);
	}
}
