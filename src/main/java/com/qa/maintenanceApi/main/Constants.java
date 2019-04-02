package com.qa.maintenanceApi.main;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.maintenanceApi.main.entities.Maintenance;
import com.qa.maintenanceApi.main.entities.MaintenanceBuilder;

public class Constants {

	private static final String CHARSET = "utf8";
	private static final Maintenance NULL_MAINTENANCE = new Maintenance();
	private static String naString = "N/A";
	private static Maintenance DEFAULT_BUILDER_MAINTENANCE = MaintenanceBuilder.getMaintenanceBuilder()
			.maintenanceBuild();

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName(Constants.getCharset()));

	private static final String CREATION_MESSAGE = "New entry added";
	private static final String DELETION_MESSAGE = "Entry(ies) deleted";
	private static final String ALL_DELETION_MESSAGE = "All entries deleted";
	private static final String UPDATE_MESSSAGE = "Entry updated";

	public static final String CREATE_URL = "/createMaintenance";
	public static final String GET_ALL_URL = "/getAllMaintenance";
	public static final String SEARCH_URL = "/maintenanceSearch";
	public static final String DELETE_ALL_URL = "/deleteAllMaintenance";
	public static final String DELETE_URL = "/deleteMaintenance";
	public static final String UPDATE_URL = "/updateMaintenance/{id}";

	private static final String TEST_ISSUE_TYPE = "Test1";
	private static final String TEST_SEVERITY = "Test2";
	private static final String TEST_ADDITIONAL_DETAILS = "Test3";
	private static final String TEST_ROOM_REFERENCE = "Test4";
	private static final String TEST_DATE_REPORTED = "Test5";
	private static final String TEST_STATUS = "Test6";
	private static final String TEST_NOTES = "Test7";
	
	private static final String ISSUE_TYPE = "issueType";
	private static final String SEVERITY = "severity";
	private static final String ROOM_REFERENCE = "roomReference";
	private static final String DATE_REPORTED = "dateReported";
	private static final String STATUS = "status";
	

	private static final Maintenance CONSTRUCTED_MAINTENANCE = new Maintenance(TEST_ISSUE_TYPE, TEST_SEVERITY,
			TEST_ADDITIONAL_DETAILS, TEST_ROOM_REFERENCE, TEST_DATE_REPORTED, TEST_STATUS, TEST_NOTES);

	private static final String HELLO_WORLD = "Hello world";

	public static String getCharset() {
		return CHARSET;
	}

	public static String getTestIssueType() {
		return TEST_ISSUE_TYPE;
	}

	public static String getTestSeverity() {
		return TEST_SEVERITY;
	}

	public static String getTestAdditionalDetails() {
		return TEST_ADDITIONAL_DETAILS;
	}

	public static String getTestRoomReference() {
		return TEST_ROOM_REFERENCE;
	}

	public static String getTestDateReported() {
		return TEST_DATE_REPORTED;
	}

	public static String getTestStatus() {
		return TEST_STATUS;
	}

	public static String getTestNotes() {
		return TEST_NOTES;
	}

	public static Maintenance getNullTenant() {
		return NULL_MAINTENANCE;
	}

	public static Maintenance getDefaultBuilderMaintenance() {
		return DEFAULT_BUILDER_MAINTENANCE;
	}
	
	public static void setDefaultBuilderMaintenance(Maintenance maintenance) {
		DEFAULT_BUILDER_MAINTENANCE = maintenance;
	}

	public static ObjectMapper getObjectMapper() {
		return OBJECT_MAPPER;
	}

	public static String getIssueType() {
		return ISSUE_TYPE;
	}

	public static String getSeverity() {
		return SEVERITY;
	}

	public static String getRoomReference() {
		return ROOM_REFERENCE;
	}

	public static String getDateReported() {
		return DATE_REPORTED;
	}

	public static String getStatus() {
		return STATUS;
	}

	public static MediaType getApplicationJsonUtf8() {
		return APPLICATION_JSON_UTF8;
	}

	public static String getCreateUrl() {
		return CREATE_URL;
	}

	public static String getGetAllUrl() {
		return GET_ALL_URL;
	}

	public static String getSearchUrl() {
		return SEARCH_URL;
	}

	public static String getDeleteAllUrl() {
		return DELETE_ALL_URL;
	}

	public static String getDeleteUrl() {
		return DELETE_URL;
	}

	public static String getUpdateUrl() {
		return UPDATE_URL;
	}

	public static String getNaString() {
		return naString;
	}

	public static String getCreationMessage() {
		return CREATION_MESSAGE;
	}

	public static String getDeletionMessage() {
		return DELETION_MESSAGE;
	}

	public static String getAllDeletionMessage() {
		return ALL_DELETION_MESSAGE;
	}

	public static String getUpdateMesssage() {
		return UPDATE_MESSSAGE;
	}

	public static String getHelloWorld() {
		return HELLO_WORLD;
	}

	public static Maintenance getNullMaintenance() {
		return NULL_MAINTENANCE;
	}

	public static Maintenance getConstructedMaintenance() {
		return CONSTRUCTED_MAINTENANCE;
	}
}
