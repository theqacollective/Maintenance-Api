package com.qa.maintenanceApi.main.testMain.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.maintenanceApi.main.repositories.MaintenanceRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintenanceRepoTest {

	@Autowired
	private MaintenanceRepo maintenanceRepo;

	@Test
	public void initializationTest() {;
		assertThat(this.maintenanceRepo).isNotNull();
	}
	
	@Ignore
	@Test
	public void noDataTest() {
		assertThat(this.maintenanceRepo.count()).isEqualTo(0);
	}

}
