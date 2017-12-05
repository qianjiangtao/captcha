package com.lvmama.captcha.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lvmama.comm.utils.MemcachedUtil;

/**
 * @Title: BackCheckCaptchaServlet.java
 * @desc 后台校校验证码接口,校验之后删除
 * @author wangwenming
 * @date 2016年12月7日
 * @version v1.0
 */
public class BackCheckCaptchaServlet extends BaseCheckServlet {
	private static final Log logger = LogFactory.getLog(BackCheckCaptchaServlet.class);
	private static final long serialVersionUID = -3843003212577427788L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String bizCode = request.getParameter("bizCode");
		String checkCode = request.getParameter("checkCode");
		String sessionId = request.getParameter("sessionId");
		String result = "fail";
		if (StringUtils.isBlank(bizCode) || StringUtils.isBlank(checkCode) || StringUtils.isBlank(sessionId)) {
			response.getWriter().write(result);
			return;
		}
		String secureLevel = request.getParameter("secureLevel");
		String secureLevelUse = getSecureLevel(secureLevel, bizCode);
		if (StringUtils.isBlank(secureLevelUse)) {
			response.getWriter().write(result);
			return;
		}
		String key = sessionId + "#" + bizCode + "#" + secureLevelUse;
		String code = (String) MemcachedUtil.getInstance().get(key);
		if (checkCode.equals(code)) {
			result = "ok";
		}
		MemcachedUtil.getInstance().remove(key);
		response.getWriter().write(result);
		if (StringUtils.isNotBlank(secureLevel)) {
			sendMessage(bizCode, sessionId, result);
		}
		logger.info("BackCheck key=" + key + " result=" + result);
	}

}
