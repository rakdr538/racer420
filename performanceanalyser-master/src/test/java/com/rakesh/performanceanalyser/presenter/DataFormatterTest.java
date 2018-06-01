package com.rakesh.performanceanalyser.presenter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.rakesh.performanceanalyser.filehandler.JsonFileDefinitations;

public class DataFormatterTest {

	@Test
	public void testGetCpuUsage() throws IOException {
		File test = new File(JsonFileDefinitations.PERFORM_ANALYSER_FILE);
		if (test.exists()) {
			String content = FileUtils.readFileToString(test, JsonFileDefinitations.UTF_8_ENCODING);
			JSONObject analyser = new JSONObject(content);
			JSONArray data = analyser.getJSONArray("data");
			DataFormatter dataFormatter = new DataFormatter(data);
			dataFormatter.getCpuUsage();
		}
	}

	@Test
	public void testGetTotalCpuUsagePerDuration() throws IOException {
		File test = new File(JsonFileDefinitations.PERFORM_ANALYSER_FILE);
		if (test.exists()) {
			String content = FileUtils.readFileToString(test, JsonFileDefinitations.UTF_8_ENCODING);
			JSONObject analyser = new JSONObject(content);
			JSONArray data = analyser.getJSONArray("data");
			DataFormatter dataFormatter = new DataFormatter(data);
			dataFormatter.getTotalCpuUsagePerDuration();
		}
	}
	
	@Test
	public void testGetTotalNoOfProcessesPerDuration() throws IOException {
		File test = new File(JsonFileDefinitations.PERFORM_ANALYSER_FILE);
		if (test.exists()) {
			String content = FileUtils.readFileToString(test, JsonFileDefinitations.UTF_8_ENCODING);
			JSONObject analyser = new JSONObject(content);
			JSONArray data = analyser.getJSONArray("data");
			DataFormatter dataFormatter = new DataFormatter(data);
			dataFormatter.getTotalNoOfProcessesPerDuration();
		}
	}
}
