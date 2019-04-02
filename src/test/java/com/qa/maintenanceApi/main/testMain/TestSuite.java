
package com.qa.maintenanceApi.main.testMain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.maintenanceApi.main.testMain.controllerTest.MaintenanceControllerTest;
import com.qa.maintenanceApi.main.testMain.entitiesTest.MaintenanceBuilderTest;
import com.qa.maintenanceApi.main.testMain.entitiesTest.MaintenanceTest;
import com.qa.maintenanceApi.main.testMain.repositoryTest.MaintenanceRepoTest;
import com.qa.maintenanceApi.main.testMain.serviceTest.MaintenanceServiceTest;


@RunWith(Suite.class)

@SuiteClasses({MaintenanceTest.class, MaintenanceServiceTest.class,
	 MaintenanceBuilderTest.class,  MaintenanceRepoTest.class, ApplicationMainTest.class,  MaintenanceControllerTest.class })

@SpringBootTest
public class TestSuite {

}
