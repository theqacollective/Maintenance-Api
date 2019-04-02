package com.qa.maintenanceApi.main.testMain.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qa.maintenanceApi.main.Constants;
import com.qa.maintenanceApi.main.controllers.MaintenanceController;
import com.qa.maintenanceApi.main.entities.Maintenance;
import com.qa.maintenanceApi.main.entities.MaintenanceBuilder;
import com.qa.maintenanceApi.main.services.MaintenanceService;

@RunWith(SpringRunner.class)
@WebMvcTest(MaintenanceController.class)
@AutoConfigureMockMvc
public class MaintenanceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MaintenanceService service;
	@MockBean
	private MaintenanceBuilder builder;
	@MockBean
	RestTemplateBuilder rtb;

	private Maintenance controllerTestMaintenance;
	private String postContent;
	private String postContent2;
	private ObjectWriter ow;
	private List<Maintenance> mockedMaintenances;
	private MvcResult result;
	private String content;
	private TypeReference<List<Maintenance>> mapType;
	private List<Maintenance> list;
	private Long id;

	@Before
	public void setUp() throws JsonProcessingException {
		this.controllerTestMaintenance = Constants.getConstructedMaintenance();
		Constants.OBJECT_MAPPER.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		this.ow = Constants.OBJECT_MAPPER.writer().withDefaultPrettyPrinter();
		this.postContent = this.ow.writeValueAsString(this.controllerTestMaintenance);
		this.postContent2 = this.ow.writeValueAsString(Constants.getDefaultBuilderMaintenance());
		this.mockedMaintenances = new ArrayList<Maintenance>();
	}

	@Test
	public void testMaintenanceCreation() throws Exception {
		System.out.println(postContent);
		Mockito.when(this.service.createMaintenance((Maintenance) notNull()))
				.thenReturn(Constants.getCreationMessage());
		this.result = this.mockMvc
				.perform(post(Constants.CREATE_URL).contentType(Constants.APPLICATION_JSON_UTF8).content(this.postContent)).andReturn();
		System.out.println(postContent);
		assertThat(result.getResponse().getContentAsString()).contains(Constants.getCreationMessage());
	}

	@Test
	public void testGetAllMaintenances() throws Exception {
		this.mockedMaintenances.add(Constants.getConstructedMaintenance());
		when(this.service.getAllMaintenance()).thenReturn(this.mockedMaintenances);
		assertThat(this.mockMvc.perform(get(Constants.GET_ALL_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString(Constants.getTestIssueType()))));
	}

	@Test
	public void testMaintenanceSearch() throws Exception {
		this.mockedMaintenances.add(this.controllerTestMaintenance);
		this.mockedMaintenances.add(Constants.getDefaultBuilderMaintenance());

		Mockito.when(this.service.maintenanceSearch((Maintenance) notNull())).thenReturn(this.mockedMaintenances
				.stream().filter(x -> x.matches(this.controllerTestMaintenance)).collect(Collectors.toList()));
		this.result = this.mockMvc
				.perform(get(Constants.SEARCH_URL)
						.param(Constants.getIssueType(), Constants.getTestIssueType())
						.param(Constants.getSeverity(),Constants.getTestSeverity())
						.param(Constants.getRoomReference(),Constants.getTestRoomReference())
						.param(Constants.getDateReported(),Constants.getTestDateReported())
						.param(Constants.getStatus(),Constants.getTestStatus())
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		this.content = this.result.getResponse().getContentAsString();
		this.mapType = new TypeReference<List<Maintenance>>() {
		};
		this.list = Constants.OBJECT_MAPPER.readValue(this.content, this.mapType);
		assertThat(this.list.stream().filter(x -> x.matches(this.controllerTestMaintenance))
				.collect(Collectors.toList()).get(0).matches(this.controllerTestMaintenance)).isEqualTo(true);
	}

	@Test
	public void testDeleteAll() throws Exception {
		this.mockedMaintenances.add(this.controllerTestMaintenance);
		this.mockedMaintenances.add(Constants.getDefaultBuilderMaintenance());
		Mockito.when(service.deleteAllMaintenances()).thenAnswer((Answer<?>) invocation -> {
			this.mockedMaintenances.clear();
			return Constants.getAllDeletionMessage();
		});
		this.mockMvc.perform(MockMvcRequestBuilders.delete(Constants.DELETE_ALL_URL)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertThat(this.mockedMaintenances.size()).isEqualTo(0);
	}

	@Test
	public void testDeleteMaintenance() throws Exception {
		this.mockedMaintenances = new ArrayList<Maintenance>();
		this.mockedMaintenances.add(this.controllerTestMaintenance);
		this.mockedMaintenances.add(Constants.getDefaultBuilderMaintenance());

		Mockito.when(this.service.maintenanceSearch((Maintenance) notNull())).thenReturn(this.mockedMaintenances);
		Mockito.when(this.service.deleteMaintenance((Maintenance) notNull())).thenAnswer((Answer<?>) invocation -> {
			this.mockedMaintenances.remove(this.controllerTestMaintenance);
			return Constants.getDeletionMessage();
		});
		this.mockMvc.perform(MockMvcRequestBuilders.delete(Constants.DELETE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(this.postContent)).andExpect(status().isOk());
		assertThat(this.mockedMaintenances.size()).isEqualTo(1);
	}

	@Test
	public void testUpdateMaintenance() throws Exception {
		this.id = controllerTestMaintenance.getId();
		Mockito.when(this.service.updateMaintenance((Long) notNull(), (Maintenance) notNull()))
				.thenAnswer((Answer<?>) invocation -> {
					this.controllerTestMaintenance = Constants.getDefaultBuilderMaintenance();
					this.controllerTestMaintenance.setId(id);
					return Constants.getUpdateMesssage();
				});
		this.mockMvc.perform(MockMvcRequestBuilders.put(Constants.UPDATE_URL, this.id)
				.contentType(MediaType.APPLICATION_JSON).content(this.postContent2)).andExpect(status().isOk());
		assertThat(this.controllerTestMaintenance.getIssueType()).isEqualTo(Constants.getNaString());
		assertThat(this.controllerTestMaintenance.getId()).isEqualTo(id);
	}
}
