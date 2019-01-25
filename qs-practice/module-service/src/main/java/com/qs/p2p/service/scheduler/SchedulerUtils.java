package com.qs.p2p.service.scheduler;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by scq on 2018-01-19 10:52:46
 */
public class SchedulerUtils {
	private final static String DEFAULT_LOCALHOST_NAME = "localhost";

	public static String getLocalHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			throw new RuntimeException("Unknown Host", e);
		}
	}

	public static String[] getLocalHostIps() {
		List<String> ips = new ArrayList<>();
		Enumeration<NetworkInterface> allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = allNetInterfaces.nextElement();
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress inetAddress = addresses.nextElement();
				if (inetAddress != null && inetAddress instanceof Inet4Address) {
					String ip = inetAddress.getHostAddress();
					if (ip != null && !ip.equals("127.0.0.1")) {
						ips.add(ip);
					}
				}
			}
		}
		return ips.toArray(new String[ips.size()]);
	}

	public static boolean isLocalHostName(String computerName) {
		return computerName.toLowerCase().indexOf(DEFAULT_LOCALHOST_NAME) != -1;
	}
}
