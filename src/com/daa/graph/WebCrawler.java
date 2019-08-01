package com.daa.graph;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * It is used to find all the connecting url of a Website. it can be implemented using
 * BFS.
 * 
 * for e.g. - Facebook wanted to know most important member in group. or amazon wanted to
 * find which category user frequently visited in Flipkart.
 * 
 * @f:off
 * just like bfs.
 * we will keep queue to hold urls.
 * and instead of adjacency list to hold neighbors we will parse the url content and fetch all url in the page using regex.
 * and add all those in queue.
 * To avoid infite loop we will keep list of visited url and add url to queue only if it is not present yet.
 * @f:on
 *
 */
public class WebCrawler {

	private Queue<String> urlQueue = new LinkedList<>();
	private Set<String> visitedSites = new HashSet<>();

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new WebCrawler().crawlSite("https://news.google.com/?hl=en-IN&gl=IN&ceid=IN:en");
	}

	public void crawlSite(String site) {
		urlQueue.add(site);
		visitedSites.add(site);
		while (!urlQueue.isEmpty()) {
			String url = urlQueue.poll();
			System.out.println(url);
			String rawHtml = readUrl(url);
			Pattern pattern = Pattern.compile("(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?");
			Matcher matcher = pattern.matcher(rawHtml);
			while (matcher.find()) {
				String act = matcher.group();
				if (!visitedSites.contains(act)) {
					visitedSites.add(act);
					urlQueue.add(act);
				}
			}
		}
	}

	private String readUrl(String site) {
		StringBuilder rawHtml = new StringBuilder();
		try {
			URL url = new URL(site);
			try (InputStream resource = url.openStream()) {
				new BufferedReader(new InputStreamReader(resource)).lines().forEach(rawHtml::append);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rawHtml.toString();
	}

}
