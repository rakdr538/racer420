/**
 * 
 */
package com.rakesh.performanceanalyser;

import java.util.Scanner;

import com.rakesh.performanceanalyser.filehandler.FileHelper;
import com.rakesh.performanceanalyser.filehandler.JsonFileHandler;
import com.rakesh.performanceanalyser.presenter.DataFormatter;

/**
 * @author rakesh
 *
 */
public class PerformanceAnalyserInterfaceThread implements Runnable{

	private Thread t;
	private String threadName;
	
	public PerformanceAnalyserInterfaceThread(String threadName) {
		this.threadName = threadName;
	}
	
	@Override
	public void run() {
		boolean loopBreaker = true;
		Scanner in = new Scanner(System.in);
		try {
			String menuOption;
			while (loopBreaker) {
				System.out.println("Menu (\"Type 'exit' to exit!\"):\n" 
						+ "a:Get CPU Usage\n"
						+ "b:Get Total Cpu Usage Per Timestamp\n"
						+ "c:Get Total No Of Processes Per Timestamp\n"
						+ "d:Remove all files created by Perfromance Analyser\n");
				menuOption = in.nextLine();
				if(!menuOption.isEmpty() && !menuOption.equals("exit")) {
					getNeededOutput(menuOption);
					
					menuOption = in.nextLine();
				} else {
					loopBreaker = false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			in.close();
		}
	}

	public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }

	private static void getNeededOutput(String menuOption) throws Exception {
		DataFormatter dataFormatter = new DataFormatter(JsonFileHandler.getDataArrayObject());
		if (menuOption.equals("a")) {
			dataFormatter.getCpuUsage();
		} else if (menuOption.equals("b")) {
			dataFormatter.getTotalCpuUsagePerDuration();
		} else if (menuOption.equals("c")) {
			dataFormatter.getTotalNoOfProcessesPerDuration();
		} else if (menuOption.equals("d")) {
			FileHelper.removeAllFiles();
			JsonFileHandler.removeAnalyserFile();
		}
	}
}
