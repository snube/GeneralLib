package com.snubee.baselibrary.utils;

import android.util.Log;

/**
 * 此类封装系统Log方法，通过debugEnable变量来控制是否输出调试信息
 * 
 * @author alonso lee
 * 
 */
public class ALLog {

	private static final String TAG = "ALLogUtils";

	/**
	 * debug or not
	 */
	private static boolean debug = false;

	private static ALLog instance = null;

	private static String getFileName() {

		StackTraceElement[] sts = Thread.currentThread().getStackTrace();

		if (sts == null) {
			return null;
		}

		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}

			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}

			if (st.getClassName().equals(instance.getClass().getName())) {
				continue;
			}

			return st.getFileName().replace(".java", "");
		}

		return null;

	}

	private static String getFunctionName() {
		if (instance == null) {
			instance = new ALLog();
		}

		StackTraceElement[] sts = Thread.currentThread().getStackTrace();

		if (sts == null) {
			return null;
		}

		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}

			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}

			if (st.getClassName().equals(instance.getClass().getName())) {
				continue;
			}

			return /*st.getFileName() + */"" + st.getMethodName() + ": line "
					+ st.getLineNumber();
		}

		return null;
	}

	private static String createMessage(String msg) {
		String functionName = getFunctionName();
		String message = (functionName == null ? msg : ("[ " + functionName
				+ " - " + msg + " ]"));
		return message;
	}

	/**
	 * log.i
	 */
	public static void i(String msg) {
		if (debug) {
			String message = createMessage(msg);
			String tag = getFileName();
			if (tag != null) {
				Log.i(tag, message);
			} else {
				Log.i(TAG, message);
			}
		}
	}

	/**
	 * log.v
	 */
	public static void v(String msg) {
		if (debug) {
			String message = createMessage(msg);
			String tag = getFileName();
			if (tag != null) {
				Log.v(tag, message);
			} else {
				Log.v(TAG, message);
			}
		}
	}

	/**
	 * log.d
	 */
	public static void d(String msg) {
		if (debug) {
			String message = createMessage(msg);
			String tag = getFileName();
			if (tag != null) {
				Log.d(tag, message);
			} else {
				Log.d(TAG, message);
			}
		}
	}

	/**
	 * log.e
	 */
	public static void e(String msg) {
		if (debug) {
			String message = createMessage(msg);
			String tag = getFileName();
			if (tag != null) {
				Log.e(tag, message);
			} else {
				Log.e(TAG, message);
			}
		}
	}

	/**
	 * log.error
	 */
	public static void error(Exception e) {
		if (debug) {
			StringBuffer sb = new StringBuffer();
			String name = getFunctionName();
			StackTraceElement[] sts = e.getStackTrace();

			if (name != null) {
				sb.append(name + " - " + e + "\r\n");
			} else {
				sb.append(e + "\r\n");
			}
			if (sts != null && sts.length > 0) {
				for (StackTraceElement st : sts) {
					if (st != null) {
						sb.append("[ " + st.getFileName() + ":"
								+ st.getLineNumber() + " ]\r\n");
					}
				}
			}
			e(sb.toString());
		}
	}

	/**
	 * log.d
	 */
	public static void w(String msg) {
		if (debug) {
			String message = createMessage(msg);
			String tag = getFileName();
			if (tag != null) {
				Log.w(tag, message);
			} else {
				Log.w(TAG, message);
			}
		}
	}

	/**
	 * set debug
	 */
	public static void setDebug(boolean d) {
		debug = d;
	}

}
