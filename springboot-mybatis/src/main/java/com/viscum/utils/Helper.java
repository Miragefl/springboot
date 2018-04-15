package com.viscum.utils;

import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class Helper {

	public static String getCurrentTime(String format) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				format);
		Date now = new Date();
		return formatter.format(now);
	}

	/**
	 * 获取时间戳
	 *
	 * @return
	 */
	public static String getTimestampStr() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 生成指定长度的劵码
	 *
	 * @param len
	 * @return
	 */
	public static String getRandomNum(int len) {
		Random random = new Random();
		String ch = "0";
		LinkedList<String> ls = new LinkedList<String>();
		for (int i = 0; i < 10; i++) {// 0-9
			ls.add(String.valueOf(0 + i));
		}

		StringBuilder sb = new StringBuilder();
		int index;
		for (int i = 0; i < len; i++) {
			index = random.nextInt(ls.size());
			if (index > (ls.size() - 1)) {
				index = ls.size() - 1;
			}
			ch = String.valueOf(ls.get(index));
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * 生成指定长度的随机码
	 *
	 * @param len
	 * @return
	 */
	public static String getRandomChar(int len) {
		Random random = new Random();
		char ch = '0';
		LinkedList<String> ls = new LinkedList<String>();
		for (int i = 0; i < 10; i++) {// 0-9
			ls.add(String.valueOf(48 + i));
		}
		for (int i = 0; i < 26; i++) {// A-Z
			ls.add(String.valueOf(65 + i));
		}
		for (int i = 0; i < 26; i++) {// a-z
			ls.add(String.valueOf(97 + i));
		}
		StringBuilder sb = new StringBuilder();
		int index;
		for (int i = 0; i < len; i++) {
			index = random.nextInt(ls.size());
			if (index > (ls.size() - 1)) {
				index = ls.size() - 1;
			}
			ch = (char) Integer.parseInt(String.valueOf(ls.get(index)));
			sb.append(ch);
		}
		return sb.toString();
	}
}
