package com.qa.maintenanceApi.main.testMain.entitiesTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.qa.maintenanceApi.main.Constants;
import com.qa.maintenanceApi.main.entities.MaintenanceBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintenanceBuilderTest {
	
	@Before
	public void getBuilder() {
		assertThat(MaintenanceBuilder.getMaintenanceBuilder()).isNotNull();
	}

	@After
	public void resetBuilder() {
		assertThat(MaintenanceBuilder.getMaintenanceBuilder()).isNotNull();
	}

	@Test
	public void blankBuild() {
		assertThat(MaintenanceBuilder.maintenanceBuild().matches(Constants.getDefaultBuilderMaintenance())).isEqualTo(true);
	}

	@Test
	public void setterBuild() {
		 MaintenanceBuilder.getMaintenanceBuilder().issueType(Constants.getTestIssueType()).severity(Constants.getTestSeverity())
				.additionalDetails(Constants.getTestAdditionalDetails()).dateReported(Constants.getTestDateReported())
				.notes(Constants.getTestNotes()).roomReference(Constants.getTestRoomReference())
				.status(Constants.getTestStatus());
		
		assertThat(MaintenanceBuilder.maintenanceBuild())
		.isEqualToComparingFieldByField(Constants.getConstructedMaintenance());
	}
}
