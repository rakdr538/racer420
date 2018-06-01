/**
 * 
 */
package com.rakesh.performanceanalyser.filehandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author rakesh
 *
 */
public class FileHelper {
	
	private static List<File> JSON_FILES = new ArrayList<File>();
	
	// private constructor
	private FileHelper() {}
	
	public static synchronized void removeAllFiles() throws IOException {
		JSON_FILES.removeAll(JSON_FILES);
		FileUtils.deleteDirectory(new File(JsonFileDefinitations.DIR_FOR_STATS_FILES));
	}
	
	public static void addFile(File file) {
		JSON_FILES.add(file);
	}
	
	public static File createFile(String fileName) {
		File file = new File(JsonFileDefinitations.DIR_FOR_STATS_FILES + File.separator + fileName);
		addFile(file);
		return file;
	}
	
	public List<File> getListOfFiles() {
		return JSON_FILES;
	}
	
	public static synchronized boolean removeFile(File file) {
		boolean removed = false;
		if(file.exists()) {
			removed = file.delete();
		}
		return removed;
	}
}
