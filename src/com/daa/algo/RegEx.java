package com.daa.algo;

import java.util.regex.Pattern;

public class RegEx {
	public static void main(String[] args) {

	}

	public String validIPAddress(String ip) {
		String ipv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
		String ipv6 = "([0-9a-fA-F]{1,4})";
		Pattern pipv4 = Pattern.compile("^(" + ipv4 + "\\.){3}" + ipv4 + "$");
		Pattern pipv6 = Pattern.compile("^(" + ipv6 + "\\:){7}" + ipv6 + "$");
		if (pipv4.matcher(ip).matches()) {
			return "IPv4";
		} else if (pipv6.matcher(ip).matches()) {
			return "IPv6";
		}
		return "Neither";
	}
}
