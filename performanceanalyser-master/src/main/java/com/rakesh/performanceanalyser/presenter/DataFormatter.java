/**
 * 
 */
package com.rakesh.performanceanalyser.presenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.rakesh.performanceanalyser.filehandler.FileHelper;
import com.rakesh.performanceanalyser.filehandler.JsonFileDefinitations;

/**
 * DataFormatter will provide all data necessary for GUI view
 * {
 *  "type": "line",
 *  title: {
 *          text: 'My First Chart' // Adds a title to your chart
 *         },
 *  "series": [
 *  		{ "values": [3, 4, 11, 5, 19, 7]	}, 
 *  		{ "values": [9, 19, 15, 25, 12, 14] }, 
 *  		{ "values": [15, 29, 19, 21, 25, 26] }
 *  ]
 * }
 * @author rakesh
 *
 */
public class DataFormatter {
	
	private JSONArray data = null;
	private static final String TYPE_STRING = "type";
	private static final String SERIES_STRING = "series";
	private static final String VALUES_STRING = "values";
	private static final File ROOT_FOLDER = new File(JsonFileDefinitations.DIR_FOR_STATS_FILES);
	
	public DataFormatter(JSONArray inJSONArray) {
		data = inJSONArray;
		if(ROOT_FOLDER.exists()) {
			if (ROOT_FOLDER.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Directory already exists!");
            }				
		}
	}
	
	public void getCpuUsage() throws IOException {
		String timestamp = null;
		final File cpuUsageFile = FileHelper.createFile("cpu_usage.json");
		List<Double> items = new ArrayList<Double>();
		for(int i = 0; i < data.length(); i++) {
			JSONObject subObj = (JSONObject) data.get(i);
			timestamp = subObj.getString(JsonFileDefinitations.TIME_STAMP);
			JSONArray process = subObj.getJSONArray("process");
			process.forEach(subProcess-> {
				final String str = ((JSONObject) subProcess).get(JsonFileDefinitations.PROCESS_CPU_USAGE).toString();
				double usage = Double.parseDouble(str);
				items.add(usage);
			});
		}
		FileUtils.writeStringToFile(cpuUsageFile, getJsonString(items,
				"CPU Usage per Process at " + timestamp, "bar"), 
				JsonFileDefinitations.UTF_8_ENCODING);
	}
	
	private String getJsonString(List<?> items, String titleText, String chartType) {
		JSONObject obj = new JSONObject();
		obj.put(TYPE_STRING, chartType);
		JSONArray series = new JSONArray();
		JSONObject values = new JSONObject();
		values.put(VALUES_STRING, new JSONArray(items));
		series.put(values);
		JSONObject title = new JSONObject();
		title.put("text", titleText);
		obj.put("title", title);
		obj.put(SERIES_STRING, series);
		System.out.println(obj.toString());
		return obj.toString();
	}

	public void getTotalCpuUsagePerDuration() throws IOException {
		final File totalCpuUsagePerDurationFile = FileHelper.createFile("totalCpuUsagePerDuration.json");
		List<Double> items = new ArrayList<Double>();
		for(int i = 0; i < data.length(); i++) {
			JSONObject subObj = (JSONObject) data.get(i);
			JSONArray processArray = subObj.getJSONArray("process");
			Double totalCpuUsagePerDuration = 0.0;
			for(int j=0; j<processArray.length();j++) {
				JSONObject process = processArray.getJSONObject(j);
				final String str = process.getString(JsonFileDefinitations.PROCESS_CPU_USAGE);
				totalCpuUsagePerDuration = totalCpuUsagePerDuration.doubleValue() + Double.parseDouble(str);				
			}
			items.add(totalCpuUsagePerDuration);
		}
		FileUtils.writeStringToFile(totalCpuUsagePerDurationFile, getJsonString(items,
				"Total CPU Usage per timestamp ", "line"), 
				JsonFileDefinitations.UTF_8_ENCODING);
	}
	
	public void getTotalNoOfProcessesPerDuration() throws IOException {
		final File totalCpuUsagePerDurationFile = FileHelper.createFile("totalNoOfProcessesPerDuration.json");
		List<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < data.length(); i++) {
			JSONObject subObj = (JSONObject) data.get(i);
			JSONArray processArray = subObj.getJSONArray("process");
			items.add(processArray.length());
		}
		FileUtils.writeStringToFile(totalCpuUsagePerDurationFile, getJsonString(items,
				"Total no of Processes per timestamp ", "line"), 
				JsonFileDefinitations.UTF_8_ENCODING);
	}
	
	
}
