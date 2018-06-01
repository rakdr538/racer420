package com.rakesh.performanceanalyser;

import java.util.Scanner;

/**
 * PerformanceAnalyser
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		startPerformanceAnalyser();
	}

	private static void startPerformanceAnalyser() throws Exception {
		final Scanner in = new Scanner(System.in);
		System.out.println("Please enter the duration in seconds for which you want to run scan:");
		final Integer inDuration = in.nextInt();
		in.close();
		// start thread to collect data on the client machine
		ClientLoadDataCollectorThread clientLoadDataCollectorThread = new ClientLoadDataCollectorThread(inDuration,
				ClientLoadDataCollectorThread.class.getName());
		clientLoadDataCollectorThread.start();

		// start parallel thread to interact with FileFromYou application
		PerformanceAnalyserInterfaceThread performanceAnalyserInterfaceThread = new PerformanceAnalyserInterfaceThread(
				PerformanceAnalyserInterfaceThread.class.getName());
		performanceAnalyserInterfaceThread.start();
	}
}
