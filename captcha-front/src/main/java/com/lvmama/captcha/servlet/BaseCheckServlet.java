package com.lvmama.captcha.servlet;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;

import com.lvmama.captcha.service.CaptchaRuleDubboService;
import com.lvmama.comm.spring.SpringBeanProxy;
import com.lvmama.lvshield.api.constant.EventResultConstant;
import com.lvmama.lvshield.api.util.KafKaUtils;
import com.lvmama.lvshield.api.vo.message.RecordDataMessage;

/**
 * 校验顾基础类
 * 
 * @Title: BaseCheckServlet.java
 * @desc
 * @author wangwenming
 * @date 2017年3月17日
 * @version v1.0
 */
public class BaseCheckServlet extends HttpServlet {

	private static final long serialVersionUID = -7303728405768112360L;
	private CaptchaRuleDubboService captchaRuleDubboService;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		captchaRuleDubboService = (CaptchaRuleDubboService) SpringBeanProxy.getBean("captchaRuleDubboService");
	}

	protected String getSecureLevel(String secureLevel, String bizCode) {
		if (StringUtils.isBlank(secureLevel)) {
			Map<String, String> map = captchaRuleDubboService.queryCodeAndDefaultLevel();
			if (map != null && map.get(bizCode) != null) {
				secureLevel = map.get(bizCode);
			}
		}
		return secureLevel;
	}

	protected void sendMessage(String businessCode, String sessionId, String eventResult) {
		RecordDataMessage message = new RecordDataMessage();
		message.setBusinessCode(businessCode);
		message.setBusinessType("captcha");
		message.setSessionId(sessionId);
		message.setEventResult("ok".equals(eventResult) ? EventResultConstant.SUCCESS : EventResultConstant.FAILURE);
		message.setEventTime(new Date());
		try {
			KafKaUtils.sendRecordDataMessage(message);
		} catch (Exception e) {
		}
	}
}
