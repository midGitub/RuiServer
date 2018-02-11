package com.game.framework.logger;

import ch.qos.logback.core.FileAppender;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.util.Calendar;

/**
 * 写日志类
 * @author liguorui
 *
 */
public class DailyFileAppender<E> extends FileAppender<E> {

	private static final String dataPattern = "yyyy-MM-dd";

	private String oldFileName;

	private Calendar lastTime;

	private Object rollLockObject = new Object();

	/**
	 * 父目录创建锁
	 */
	private static Object parentDirect = new Object();

	@Override
	public void start() {
		this.lastTime = Calendar.getInstance();
		this.oldFileName = fileName;
		String formatStr = DateFormatUtils.format(lastTime, dataPattern);
		this.fileName = getRealFileName(formatStr) + "." + formatStr;
		super.start();
	}

	@Override
	protected void subAppend(E event) {
		synchronized (rollLockObject) {
			rollOver();
		}
		super.subAppend(event);
	}

	private void rollOver() {
		Calendar now = Calendar.getInstance();
		if (now.get(Calendar.DAY_OF_YEAR) == lastTime.get(Calendar.DAY_OF_YEAR)) {
			return;
		}

		lastTime = now;
		lock.lock();
		try {
			//确保关闭
			closeOutputStream();
			String format = DateFormatUtils.format(lastTime,dataPattern);
			String fileName = getRealFileName(format) + "." + format;
			createMissingParentDirectories(new File(fileName));
			openFile(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	private void createMissingParentDirectories(File file) {
		File paret = file.getParentFile();
		if (paret == null) {
			return;
		}
		if (paret.exists()) {
			return;
		}
		synchronized (parentDirect) {
			if (paret.exists()) {
				return;
			}
			paret.mkdirs();
		}
	}

	private String getRealFileName(String formatStr) {
		File file = new File(oldFileName);
		String path = file.getParent();
		String name = file.getName();
		if (path != null) {
			return path + File.separator + formatStr + File.separator + name;
		} else {
			return formatStr + File.separator + name;
		}
	}
}
