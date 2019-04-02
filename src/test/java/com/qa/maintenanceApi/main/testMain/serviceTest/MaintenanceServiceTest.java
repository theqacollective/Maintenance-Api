package com.qa.maintenanceApi.main.testMain.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.maintenanceApi.main.Constants;
import com.qa.maintenanceApi.main.entities.Maintenance;
import com.qa.maintenanceApi.main.entities.MaintenanceBuilder;
import com.qa.maintenanceApi.main.repositories.MaintenanceRepo;
import com.qa.maintenanceApi.main.services.MaintenanceService;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintenanceServiceTest {
	@InjectMocks
	MaintenanceService maintenanceService;
	@Mock
	MaintenanceRepo maintenanceRepo;
	
	private List<Maintenance> maintenanceList;
	private Maintenance maintenance;
	private List<Maintenance> returnList;
	private Long index;

	@Before
	public void setup() {
		this.maintenanceList = new ArrayList<Maintenance>();
		this.maintenanceList.add(Constants.getDefaultBuilderMaintenance());
		this.maintenanceList.add(Constants.getConstructedMaintenance());  
		this.maintenance = new Maintenance();
		this.returnList = new ArrayList<Maintenance>();  
		index=null;
	}

	@After
	public void deconstruct() {
		this.maintenance = MaintenanceBuilder.getMaintenanceBuilder().maintenanceBuild();
		Constants.setDefaultBuilderMaintenance(this.maintenance);
		this.maintenanceList.clear();
	}

	@Test
	public void getAllTest() {
		Mockito.when(this.maintenanceRepo.findAll()).thenReturn(this.maintenanceList);
		this.returnList = this.maintenanceService.getAllMaintenance();

		assertThat(this.returnList.size()).isEqualTo(2);
		assertThat(this.returnList.get(0)).isEqualToComparingFieldByField(Constants.getDefaultBuilderMaintenance());
		assertThat(this.returnList.get(1)).isEqualToComparingFieldByField(Constants.getConstructedMaintenance());
	}
	 
	@Test
	public void createMaintenanceTest() {
		this.maintenance = Constants.getConstructedMaintenance();
		Mockito.when(this.maintenanceRepo.save((Maintenance)notNull())).thenAnswer((Answer<?>) invocation -> {
			this.maintenanceList.add(this.maintenance);
			return Constants.getNullMaintenance();
		});
		assertThat(this.maintenanceService.createMaintenance(this.maintenance)).isEqualTo(Constants.getCreationMessage());
		assertThat(this.maintenanceList.size()).isEqualTo(3);
		assertThat(this.maintenanceList.get(2)).isEqualToComparingFieldByField(Constants.getConstructedMaintenance());
	}
	
	@Test
	public void maintenanceSearchTest() {
		this.maintenance = Constants.getConstructedMaintenance();
		Mockito.when(this.maintenanceRepo.findAll()).thenReturn(this.maintenanceList);
		assertThat(maintenanceService.maintenanceSearch(this.maintenance).size()).isEqualTo(1);
		assertThat(maintenanceService.maintenanceSearch(this.maintenance).get(0)).isEqualToComparingFieldByField(this.maintenance);
	}
	
	@Test
	public void deleteMaintenanceTest() {
		Mockito.when(this.maintenanceService.deleteMaintenance((Maintenance)notNull())).thenAnswer((Answer<?>) invocation -> {
			this.maintenanceList.remove(Constants.getDefaultBuilderMaintenance());
			return Constants.getDeletionMessage();
		});
		assertThat(this.maintenanceService.deleteMaintenance(Constants.getDefaultBuilderMaintenance())).isEqualTo(Constants.getDeletionMessage());
		assertThat(this.maintenanceList.contains(Constants.getDefaultBuilderMaintenance())).isEqualTo(false);
		
	}
	
	@Test
	public void deleteAllTest() {
		Mockito.when(this.maintenanceService.deleteAllMaintenances()).thenAnswer((Answer<?>) invocation -> {
			this.maintenanceList.clear();
			return Constants.getAllDeletionMessage();
		});
		assertThat(this.maintenanceService.deleteAllMaintenances()).isEqualTo(Constants.getAllDeletionMessage());
		assertThat(this.maintenanceList.size()).isEqualTo(0);
	}
	
	@Test
	public void updateMaintenanceTest() throws CloneNotSupportedException {
		this.maintenance = Constants.getConstructedMaintenance();
		Mockito.when(this.maintenanceRepo.findById((Long)notNull())).thenReturn(Optional.ofNullable(this.maintenanceList.get(0)));
		Mockito.when(this.maintenanceRepo.saveAndFlush((Maintenance)notNull())).thenAnswer((Answer<?>) invocation -> {
			this.maintenanceList.clear();
			this.maintenanceList.add(this.maintenance);
			this.maintenanceList.add(Constants.getConstructedMaintenance());  
			return maintenance;
		});

		this.index = Long.valueOf(String.valueOf(0));
		this.maintenanceService.updateMaintenance(this.index, this.maintenance);
		assertThat(this.maintenanceList.get(0).matches(Constants.getConstructedMaintenance()));
	}
}
