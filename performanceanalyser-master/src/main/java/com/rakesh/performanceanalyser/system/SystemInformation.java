/**
 * 
 */
package com.rakesh.performanceanalyser.system;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;

/**
 * SystemInformation object contains all system information like
 * OS name, version, architecture, list of processes, ipaddress
 * and mac address.
 * @author rakesh
 *  */
public class SystemInformation {

	private static final OperatingSystemMXBean mbs = ManagementFactory.getOperatingSystemMXBean();

	/* private constructor, this should not be initialized */
	private SystemInformation() {
	}

	public static String getOS() {
		return mbs.getName();
	}

	public static String getMacAddress() {
		NetworkInterface network = null;
		byte[] mac = null;
		try {
			network = NetworkInterface.getNetworkInterfaces().nextElement();
			mac = network.getHardwareAddress();
		} catch (SocketException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		System.out.print("Current MAC address : " + sb.toString());
		return sb.toString();
	}

	public static String getIpAddress() {
		String ipAddress = null;
		try {
			ipAddress = getRawInetAddress().getHostAddress();
			System.out.println("Current IP address : " + ipAddress);
		} catch (UnknownHostException uhe) {
			// TODO Auto-generated catch block
			uhe.printStackTrace();
		}
		return ipAddress;
	}

	private static InetAddress getRawInetAddress() throws UnknownHostException {
		return InetAddress.getLocalHost();
	}

	public static double getProcessCpuLoad() throws Exception {
		return mbs.getSystemLoadAverage();
	}

	public static List<ProcessInfo> listAllProcesses() {
		return JProcesses.getProcessList();
	}
}
