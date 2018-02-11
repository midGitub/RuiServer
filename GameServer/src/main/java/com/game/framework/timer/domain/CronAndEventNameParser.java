package com.game.framework.timer.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liguorui
 * @date 2016年01月6日 上午11:59:19
 */
public class CronAndEventNameParser {

	/**
	 * @param cronFile
	 * @return
	 * @throws IOException
	 */
	public static List<CronAndEventName> loadFromEventFile(InputStream cronFile) {
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(cronFile);
			BufferedReader bufReader = new BufferedReader(inputStreamReader);
			String line = null;
			List<CronAndEventName> cronAndEventNames = new LinkedList<CronAndEventName>();
			while ((line = bufReader.readLine()) != null) {
				line = line.trim();
				if ("".equals(line)) {
					continue;
				}
				int lastIndexOf = line.lastIndexOf(" ");
				String corn = line.substring(0, lastIndexOf);
				String eventName = line.substring(lastIndexOf + 1, line.length());
				cronAndEventNames.add(new CronAndEventName(corn, eventName));
			}
			return cronAndEventNames;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
