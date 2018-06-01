/**
 * 
 */
package com.rakesh.performanceanalyser;

import java.io.IOException;

import com.rakesh.performanceanalyser.filehandler.JsonFileHandler;

/**
 * @author rakesh
 *
 */
public class ClientLoadDataCollectorThread implements Runnable{
	
	private Integer regularIntervalToCollectData;
	private Thread t;
	private String threadName;

	public ClientLoadDataCollectorThread(Integer regularIntervalToCollectData, String threadName) {
		System.out.println("Regular Interval To Collect Data is " + regularIntervalToCollectData + " seconds");
		this.regularIntervalToCollectData = regularIntervalToCollectData;
		this.threadName = threadName;
	}
	
	@Override
	public void run() {
		System.out.println("Thread " + threadName +" running");
		try {
			while (true) {
				JsonFileHandler.setClientCpuStatisticsData();
				Thread.sleep(regularIntervalToCollectData.intValue() * 1000);
				System.out.println("Collecting Client CPU Statistics Data...");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		
	}

	public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
}
