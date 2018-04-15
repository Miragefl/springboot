package com.viscum.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.viscum.constant.Const;
import com.viscum.exception.FailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AliPayUtil {

	private static Logger logger = LoggerFactory.getLogger(AliPayUtil.class);

	/**
	 * 校验支付宝返回签名
	 *
	 * @param signContent  待解签内容
	 * @param aliPublicKey 支付宝公钥
	 * @return
	 * @throws FailException
	 */
	public static boolean checkSign(Map<String, String> signContent, String aliPublicKey) throws FailException {
		if (!signContent.containsKey("sign")) {
			throw new FailException(Const.RET_FAIL, "缺少签名");
		}
		String sign = signContent.get("sign").toString();
		logger.info("支付宝传来的签名：{}", sign);
		logger.info("待验证返回签名内容：{}", signContent);
		try {
			boolean signFlag = AlipaySignature.rsaCheckV1(signContent, aliPublicKey, Const.CHARSET_UTF8, signContent.get("sign_type"));
			logger.info("签名是否正确：{}", signFlag);
			if (!signFlag) {
				throw new FailException(Const.RET_FAIL, "签名不正确");
			}
			return signFlag;
		} catch (AlipayApiException e) {
			logger.error("验签出错", e);
			throw new FailException(Const.RET_FAIL, e.getErrMsg());
		}
	}

	/**
	 *
	 * @param biz_content
	 * @param method
	 * @param appId
	 * @param signType
	 * @param privateKey
	 * @return
	 * @throws FailException
	 */
	public static String sign(String biz_content, String method, String appId, String signType, String privateKey) throws FailException {
		// =========== 生成签名开始 ===========
		try {
			Map<String, String> signMap = new HashMap<>();
			// 获取支付宝分配给开发者的应用ID
			signMap.put("app_id", appId);
			// 业务参数
			signMap.put("biz_content", biz_content);
			// 编码格式
			signMap.put("charset", Const.CHARSET_UTF8);
			// 请求方法
			signMap.put("method", method);
			// 加密类型 rsa2
			signMap.put("sign_type", signType);
			// 请求时间
			signMap.put("timestamp", Helper.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			// 接口版本
			signMap.put("version", "1.0");
			signMap.put("format", Const.FORMAT_JSON);
			String signContent = AlipaySignature.getSignContent(signMap);
			logger.info("排序后的生成签名字段：{}", signContent);
			String rsaSign = AlipaySignature.rsaSign(signContent, privateKey, Const.CHARSET_UTF8, signType);
			logger.info("生成签名：{}", rsaSign);

//			boolean flag = AlipaySignature.rsa256CheckContent(signContent, rsaSign, publicKey, Const.CHARSET_UTF8);
//			logger.info("生成签名是否正确：{}" , flag);
			return rsaSign;
		} catch (AlipayApiException e) {
			throw new FailException(Const.RET_FAIL, e.getErrMsg());
		}
	}

}
