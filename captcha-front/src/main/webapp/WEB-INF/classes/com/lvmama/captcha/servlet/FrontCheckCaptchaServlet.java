package com.lvmama.captcha.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lvmama.comm.utils.MemcachedUtil;
import com.lvmama.comm.utils.ServletUtil;

/**
 * @Title: FrontCheckCaptchaServlet.java
 * @desc 页面校校验证码接口,正确不删除
 * @author wangwenming
 * @date 2016年12月7日
 * @version v1.0
 */
public class FrontCheckCaptchaServlet extends BaseCheckServlet {
	private static final Log logger = LogFactory.getLog(FrontCheckCaptchaServlet.class);
	private static final long serialVersionUID = 8236715311773048822L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		String bizCode = request.getParameter("bizCode");
		String checkCode = request.getParameter("checkCode");
		String jsoncallback = request.getParameter("jsoncallback");
		if (StringUtils.isBlank(bizCode) || StringUtils.isBlank(checkCode)) {
			print(jsoncallback, response.getWriter(), "fail");
			return;
		}
		String secureLevel = request.getParameter("secureLevel");
		String secureLevelUse = getSecureLevel(secureLevel, bizCode);
		if (StringUtils.isBlank(secureLevelUse)) {
			print(jsoncallback, response.getWriter(), "fail");
			return;
		}
		String sessionId = StringUtils.isBlank(request.getParameter("sessionId")) ? ServletUtil.getLvSessionId(request,
				response) : request.getParameter("sessionId");
		String key = sessionId + "#" + bizCode + "#" + secureLevelUse;
		String code = (String) MemcachedUtil.getInstance().get(key);
		String result = "ok";
		if (!checkCode.equals(code)) {
			result = "fail";
			MemcachedUtil.getInstance().remove(key);
		}
		print(jsoncallback, response.getWriter(), result);
		if (StringUtils.isNotBlank(secureLevel)) {
			sendMessage(bizCode, sessionId, result);
		}
		logger.info("FrontCheck key=" + key + " result=" + result);
	}

	private void print(String jsoncallback, PrintWriter printWriter, String json) throws IOException {
		if (jsoncallback == null) {
			printWriter.write("{\"result\":\"" + json + "\"}");
		} else {
			printWriter.write(jsoncallback + "({\"result\":\"" + json + "\"})");
		}
	}

}
