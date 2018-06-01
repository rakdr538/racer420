/**
 * 
 */
package com.rakesh.performanceanalyser.filehandler;

import java.io.File;

/**
 * @author rakesh
 *
 */
public class JsonFileDefinitations {

	// private constructor
	private JsonFileDefinitations() {}
	
	public static final String PERFORM_ANALYSER_FILE = System.getProperty("user.home") 
			+ File.separator + "Documents" 
			+ File.separator + "performanceAnalyser.json";
	
	public static final String DIR_FOR_STATS_FILES = System.getProperty("user.home") 
			+ File.separator + "Documents" 
			+ File.separator + "performanceAnalyser";
	
	public static final String UTF_8_ENCODING = "utf-8";
	
	// JSON definitations
	public static final String IP_ADDRESS = "ipAddress";
	public static final String TIME_STAMP = "timeStamp";
	public static final String OS_NAME = "os";
	public static final String PROCESS_ID = "pid";
	public static final String PROCESS_NAME = "name";
	public static final String PROCESS_TIME = "time";
	public static final String PROCESS_CPU_USAGE = "cpuUsage";
	public static final String PROCESS_VIRTUAL_MEMORY = "virtualMemory";
	public static final String PROCESS_PHYSICAL_MEMORY = "physicalMemory";
}
