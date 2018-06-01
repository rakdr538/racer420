/**
 * 
 */
package com.rakesh.performanceanalyser.filehandler;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jutils.jprocesses.model.ProcessInfo;

import com.rakesh.performanceanalyser.system.SystemInformation;

/**
 * @author rakesh
 *
 */
public class JsonFileHandler {

	private static final String DATA_STRING = "data";	
	private static final File ANALYSER_FILE = new File(JsonFileDefinitations.PERFORM_ANALYSER_FILE);
	private static final String PROCESS_STRING = "process";

	// private constructor
	private JsonFileHandler() {
	}

	/**
	 * returns statistics data
	 * 
	 * @return JSONObject
	 * @throws IOException
	 */
	public static synchronized JSONObject getStatisticsData() throws IOException {
		String content = FileUtils.readFileToString(ANALYSER_FILE, JsonFileDefinitations.UTF_8_ENCODING);
		return new JSONObject(content);
	}

	private static JSONObject getStatObject() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		JSONObject stat = new JSONObject();
		stat.put(JsonFileDefinitations.TIME_STAMP, timestamp.toString());
		JSONArray processArray = new JSONArray();
		final List<ProcessInfo> processesList = SystemInformation.listAllProcesses();
		for (final ProcessInfo process : processesList) {
			JSONObject singleProcess = new JSONObject();
			singleProcess.put(JsonFileDefinitations.PROCESS_ID, process.getPid());
			singleProcess.put(JsonFileDefinitations.PROCESS_NAME, process.getName());
			singleProcess.put(JsonFileDefinitations.PROCESS_TIME, process.getTime());
			singleProcess.put(JsonFileDefinitations.PROCESS_CPU_USAGE, process.getCpuUsage());
			singleProcess.put(JsonFileDefinitations.PROCESS_VIRTUAL_MEMORY, process.getVirtualMemory());
			singleProcess.put(JsonFileDefinitations.PROCESS_PHYSICAL_MEMORY, process.getPhysicalMemory());
			processArray.put(singleProcess);
		}
		stat.put(PROCESS_STRING, processArray);
		return stat;
	}

	/**
	 * updates statistics data
	 * 
	 * @return JSONObject
	 * @throws IOException
	 */
	public static void setClientCpuStatisticsData() throws IOException {
		if (ANALYSER_FILE.createNewFile()) {
			
			final JSONObject obj = new JSONObject();
			obj.put(JsonFileDefinitations.IP_ADDRESS, SystemInformation.getIpAddress());
			obj.put(JsonFileDefinitations.OS_NAME, SystemInformation.getOS());
			final JSONArray data = new JSONArray();
			data.put(getStatObject());
			obj.put(DATA_STRING, data);
			FileUtils.writeStringToFile(ANALYSER_FILE, obj.toString(), JsonFileDefinitations.UTF_8_ENCODING);
			System.out.println("Analyser file created");
		} else {
			updateStatisticalData();
		}
	}

	private static synchronized void updateStatisticalData() throws JSONException, IOException {
		final JSONObject obj = getStatisticsData();
		final JSONArray data = getStatisticsData().getJSONArray(DATA_STRING);
		data.put(getStatObject());
		obj.put(DATA_STRING, data);
		FileUtils.writeStringToFile(ANALYSER_FILE, obj.toString(), JsonFileDefinitations.UTF_8_ENCODING);
	}

	public static JSONArray getDataArrayObject() throws JSONException, IOException {
		return getStatisticsData().getJSONArray(DATA_STRING);
	}

	public static synchronized void removeAnalyserFile() {
		if(!FileHelper.removeFile(ANALYSER_FILE)) {
			System.out.println("Could not remove 'ANALYSER_FILE', error occured");
		}		
	}
}
