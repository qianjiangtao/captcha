package com.lvmama.captcha.intercept;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.lvmama.captcha.constant.CaptchaConstant;
import com.lvmama.comm.utils.MemcachedUtil;

/**
 * @Title: CaptchaServiceIntercept.java
 * @desc 缓存拦截器
 * @author wangwenming
 * @date 2016年12月7日
 * @version v1.0
 */

public class CaptchaServiceIntercept {
	private static final Log logger = LogFactory.getLog(CaptchaServiceIntercept.class);
	private static final int cacheTime = 2592000;

	public Object queryAllBusinessCode(JoinPoint joinPoint) throws Exception {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		Object target = joinPoint.getTarget();
		Object obj = null;
		try {
			String key = CaptchaConstant.ALL_BUSINESSCODE;
			obj = MemcachedUtil.getInstance().get(key);
			if (obj != null) {
				return obj;
			}
			obj = method.invoke(target, args);
			if (obj != null) {
				MemcachedUtil.getInstance().set(key, cacheTime, obj);
			}
		} catch (Exception e) {
			logger.error("queryAllBusinessCode error", e);
		}
		return obj;
	}

	public Object queryBusinessTypeByCode(JoinPoint joinPoint) throws Exception {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		Object target = joinPoint.getTarget();
		String businessCode = (String) args[0];
		String key = CaptchaConstant.BUSINESS_TYPE_PREFIX + businessCode;
		Object obj = null;
		try {
			obj = MemcachedUtil.getInstance().get(key);
			if (obj != null) {
				return obj;
			}
			obj = method.invoke(target, args);
			if (obj != null) {
				MemcachedUtil.getInstance().set(key, cacheTime, obj);
			}
		} catch (Exception e) {
			logger.error("queryBusinessTypeByCode error key=" + key, e);
		}
		return obj;
	}

	public Object queryCaptchaRuleByCodeAndLevel(JoinPoint joinPoint) throws Exception {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		Object target = joinPoint.getTarget();
		String businessCode = (String) args[0];
		String secureLevel = (String) args[1];
		String key = CaptchaConstant.CAPTCHARULE_PREFIX + businessCode + "_" + secureLevel;
		Object obj = null;
		try {
			obj = MemcachedUtil.getInstance().get(key);
			if (obj != null) {
				return obj;
			}
			obj = method.invoke(target, args);
			if (obj != null) {
				MemcachedUtil.getInstance().set(key, cacheTime, obj);
			}
		} catch (Exception e) {
			logger.error("queryCaptchaRuleByCodeAndLevel error key=" + key, e);;
		}
		return obj;
	}
	public Object queryCodeAndLevels(JoinPoint joinPoint) throws Exception {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		Object target = joinPoint.getTarget();
		Object obj = null;
		try {
			String key = CaptchaConstant.ALL_CODE_LEVELS;
			obj = MemcachedUtil.getInstance().get(key);
			if (obj != null) {
				return obj;
			}
			obj = method.invoke(target, args);
			if (obj != null) {
				MemcachedUtil.getInstance().set(key, cacheTime, obj);
			}
		} catch (Exception e) {
			logger.error("queryCodeAndLevels error", e);
		}
		return obj;
	}
	public Object queryCodeAndDefaultLevel(JoinPoint joinPoint) throws Exception {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		Object target = joinPoint.getTarget();
		Object obj = null;
		try {
			String key = CaptchaConstant.ALL_CODE_DEFAULT_LEVEL;
			obj = MemcachedUtil.getInstance().get(key);
			if (obj != null) {
				return obj;
			}
			obj = method.invoke(target, args);
			if (obj != null) {
				MemcachedUtil.getInstance().set(key, cacheTime, obj);
			}
		} catch (Exception e) {
			logger.error("queryCodeAndDefaultLevel error", e);
		}
		return obj;
	}
}
