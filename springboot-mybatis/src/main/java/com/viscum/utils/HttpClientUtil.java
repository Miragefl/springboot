package com.viscum.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viscum.constant.Const;
import com.viscum.exception.FailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static JSONObject callAPIPost(String url, JSONObject postData, String token) throws Exception {
		if (token != null) {
			url = destUrl(url, token);
		}
		Set<String> keySet = postData.keySet();
		Iterator<String> iterator = keySet.iterator();
		StringBuilder sb = new StringBuilder();
		String key = "";
		while (iterator.hasNext()) {
			key = iterator.next();
			sb.append("&" + key + "=" + postData.get(key));
		}
		logger.info("上送的参数:" + sb.toString());
		Map rs = callAPI(url, sb.toString(), true,true);
		if (rs == null) {
			throw new FailException("", "");
		}
		byte[] bytes = (byte[]) rs.get("result");
		String resultJson = "";
		JSONObject jsonObject = null;
		resultJson = new String(bytes, Const.CHARSET_UTF8);
		jsonObject = (JSONObject) JSON.parse(resultJson);
		logger.info("返回结果：" + jsonObject.toString());
		return jsonObject;
	}


	private static Map callAPI(String url, String postData, boolean isPost, boolean isProxy)
			throws Exception {

		logger.info("开始接口调用。。。。");

		Proxy proxy = null;

		if (isProxy) {// 使用代理
//			String wxProxyIp = WeixinStandard.getPropertiesValue("wxProxyIp"); // 代理ip
//			int wxProxyPort = Integer.parseInt(WeixinStandard
//					.getPropertiesValue("wxProxyPort"));// 代理代口3128
//			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(wxProxyIp,
//					wxProxyPort));
//			logger.info("通过代理建立连接:" + wxProxyIp + ":" + wxProxyPort);
		}

		Map returnMap = new HashMap();
		Map returnCookieMap = new HashMap();
		byte[] in2b = new byte[0];
		try {
			java.net.URL reqUrl = null;

			if (url.startsWith("https")) {
				reqUrl = new URL(null, url, new sun.net.www.protocol.https.Handler());
			} else {
				reqUrl = new URL(url);
			}
			HttpURLConnection reqConnection = null;
			// 判断是http请求还是https请求
			if (reqUrl.getProtocol().toLowerCase().equals("https")) {

				SSLContext sslContext = null;
				try {
					// 实例化SSL上下文
					sslContext = SSLContext.getInstance("SSL", "SunJSSE");// 直接用SSL或者TLS也可以sslContext=SSLContext.getInstance("TLS")
					TrustManager[] xtmArray = new TrustManager[]{new WeixinX509TrustManager()};
					// 初始化SSL上下文
					sslContext.init(null, xtmArray,
							new java.security.SecureRandom());
				} catch (Exception e) {
					throw e;
				}
				// 设置默认的SSLSocketFactory
				if (sslContext != null) {
					HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
							.getSocketFactory());
				}

			}
			// 建立URLConnection连接
			try {
				if (proxy != null)
					reqConnection = (HttpsURLConnection) reqUrl.openConnection(proxy);
				else
					reqConnection = (HttpsURLConnection) reqUrl.openConnection();
			} catch (Exception e) {
				logger.error("建立连接异常：{}", e.toString(), e);
			}
			// 设置连接及读取超时间(30秒)
			reqConnection.setConnectTimeout(30 * 1000);
			reqConnection.setReadTimeout(30 * 1000);
			if (isPost) {
				reqConnection.setRequestMethod("POST");
			} else {
				reqConnection.setRequestMethod("GET");
			}
			reqConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			reqConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0");
			reqConnection.setRequestProperty("DNT", "1");
			reqConnection.setRequestProperty("Connection", "keep-alive");
			reqConnection.setDoInput(true);
			reqConnection.setDoOutput(isPost);
			if (isPost) {
				OutputStream output = null;
				try {
					output = reqConnection.getOutputStream();
					output.write(postData.getBytes("UTF-8"));
				} catch (Exception e) {
					throw e;
				} finally {
					output.close();
				}
			}
			reqConnection.connect();
			// 通讯状态
			logger.info("调用积分接口打开连接返回代码:{}", reqConnection.getResponseCode(), ",返回信息:{}", reqConnection.getResponseMessage());
			Map map = reqConnection.getHeaderFields();
			List returnCookies = (List) map.get("Set-Cookie");

			if (returnCookies != null) {
				for (int i = 0; i < returnCookies.size(); i++) {
					String cookieStr = (String) returnCookies.get(i);
					cookieStr = cookieStr.substring(0, cookieStr.indexOf(";"));
					int index = cookieStr.indexOf("=");
					if (index > 0) {
						returnCookieMap.put(cookieStr.substring(0, index), cookieStr.substring(index + 1));
					}
				}
			}
			InputStream input = reqConnection.getInputStream();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[100];
			int rc = 0;
			while ((rc = input.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			in2b = swapStream.toByteArray();
		} catch (Exception e) {
			logger.error("调用接口异常：", e);
			throw e;
		}
		returnMap.put("result", in2b);
		return returnMap;
	}


	private static String destUrl(String url, String token) {

		logger.info("摘要前url：" + url);
		StringBuilder sb = new StringBuilder();
		sb.append(url);

		if (url.indexOf("?") == -1) {
			sb.append("?");
		} else {
			sb.append("&");
		}

		// String token = Standard.getPropertiesValue("points_token");
		String nonce = Helper.getRandomChar(8);
		String timestamp = Helper.getTimestampStr();

		ArrayList<String> list = new ArrayList<String>();
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		String signature = genDigest(list);

		sb.append("nonce=" + nonce);
		sb.append("&timestamp=" + timestamp);
		sb.append("&signature=" + signature);

		logger.info("摘要后url：" + sb.toString());
		return sb.toString();
	}

	/**
	 * 生成数据摘要
	 *
	 * @param list
	 * @return
	 */
	private static String genDigest(List<String> list) {

		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		logger.debug("传入的报文字段排序后()：->" + sb.toString());
		MessageDigest md;
		String pwd = "";
		try {
			// 生成一个sha1加密计算摘要
			md = MessageDigest.getInstance("sha1"); // 同样可以使用SHA1
			// 计算sha1函数
			md.update(sb.toString().getBytes());
			// digest()最后确定返回sha1 hash值，返回值为8为字符串。因为sha1
			// hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			// pwd = new BigInteger(1, md.digest()).toString(16);
			// //参数也可不只用16可改动,当然结果也不一样了
			pwd = byte2Hex(md.digest());
			logger.debug("传入的报文字段排序后md5(tangwei)：->" + pwd);
			return pwd;
		} catch (Exception e) {
			logger.info("数据摘要发生异常", e);
		}
		return null;
	}

	/**
	 * 字节数组转换16进制
	 *
	 * @param b
	 * @return
	 */
	private static String byte2Hex(byte[] b) {
		StringBuilder sb = new StringBuilder();
		String tmp = null;
		for (int i = 0; i < b.length; i++) {
			tmp = Integer.toHexString(b[i] & 0xff);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
		}
		return sb.toString();
	}

}
