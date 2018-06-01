package com.rakesh.performanceanalyser.system;

import java.util.List;

import org.junit.Test;
import org.jutils.jprocesses.model.ProcessInfo;

public class SystemInformationTest {

	@Test
	public void testGetOS() {
		System.out.println("OS: " + SystemInformation.getOS());
	}

	@Test
	public void testGetIpAddress() {
		SystemInformation.getIpAddress();
	}
	
	@Test
	public void testGetMacAddress() {
		SystemInformation.getMacAddress();
	}
	
	@Test
	public void testGetProcessCpuLoad() throws Exception {
		System.out.println("CPU Load: " + SystemInformation.getProcessCpuLoad());
	}
	
	@Test
	public void testListAllProcesses() {
		final List<ProcessInfo> processesList = SystemInformation.listAllProcesses();
		for (final ProcessInfo processInfo : processesList) {
	        System.out.println("Process PID: " + processInfo.getPid());
	        System.out.println("Process Name: " + processInfo.getName());
	        System.out.println("Process Time: " + processInfo.getTime());
	        System.out.println("User: " + processInfo.getUser());
	        System.out.println("Virtual Memory: " + processInfo.getVirtualMemory());
	        System.out.println("Physical Memory: " + processInfo.getPhysicalMemory());
	        System.out.println("CPU usage: " + processInfo.getCpuUsage());
	        System.out.println("Start Time: " + processInfo.getStartTime());
	        System.out.println("Priority: " + processInfo.getPriority());
	        System.out.println("Full command: " + processInfo.getCommand());
	        System.out.println("------------------");
	    }
	}
}
