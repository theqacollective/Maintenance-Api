package com.qa.maintenanceApi.main.testMain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.maintenanceApi.main.Constants;
import com.qa.maintenanceApi.main.MaintenanceApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationMainTest {

	private MaintenanceApiApplication app;

	@Test
	public void MaintenanceApiApplication() {
		this.app = new MaintenanceApiApplication();
		String[] args = new String[] { Constants.getHelloWorld() };
		this.app.main(args);
		assertThat(this.app).isNotNull();
	}
}
