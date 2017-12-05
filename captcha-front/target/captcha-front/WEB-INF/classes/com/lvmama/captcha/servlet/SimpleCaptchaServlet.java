package com.lvmama.captcha.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lvmama.captcha.dto.CaptchaRuleDTO;
import com.lvmama.captcha.service.CaptchaRuleDubboService;
import com.lvmama.captcha.vo.CaptchaVo;
import com.lvmama.comm.spring.SpringBeanProxy;
import com.lvmama.comm.utils.MemcachedUtil;
import com.lvmama.comm.utils.ServletUtil;

/**
 * @Title: SimpleCaptchaServlet.java
 * @desc 验证码接口
 * @author wangwenming
 * @date 2016年12月7日
 * @version v1.0
 */
public class SimpleCaptchaServlet extends BaseServlet {
	private static final Log logger = LogFactory.getLog(SimpleCaptchaServlet.class);
	private static final long serialVersionUID = 1018503340278948085L;
	private CaptchaRuleDubboService captchaRuleDubboService;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		captchaRuleDubboService = (CaptchaRuleDubboService) SpringBeanProxy.getBean("captchaRuleDubboService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bizCode = request.getParameter("bizCode");
		setHead(response);
		if (StringUtils.isBlank(bizCode)) {
			return;
		}
		Map<String, List<String>> bizCodeMap = captchaRuleDubboService.queryCodeAndLevels();
		if (bizCodeMap == null || !bizCodeMap.keySet().contains(bizCode)) {
			return;
		}

		String secureLevel = request.getParameter("secureLevel");
		if (StringUtils.isBlank(secureLevel)) {
			secureLevel = bizCodeMap.get(bizCode).get(0);
		} else if (!bizCodeMap.get(bizCode).contains(secureLevel)) {
			return;
		}
	
		CaptchaRuleDTO captchaRule = captchaRuleDubboService.queryCaptchaRuleByCodeAndLevel(bizCode, secureLevel);
		CaptchaVo vo = getCaptcha(captchaRule);
		if (vo != null && vo.getBi() != null) {
			String sessionId = StringUtils.isBlank(request.getParameter("sessionId")) ? ServletUtil.getLvSessionId(
					request, response) : request.getParameter("sessionId");
			String key = "sessionId" + "#" + bizCode + "#" + secureLevel;
			MemcachedUtil.getInstance().set(key, captchaRule.getExpireTime(), vo.getAnswer());
			ImageIO.write(vo.getBi(), "JPEG", response.getOutputStream());
			logger.info("get Captcha " + key);
		}
	}

}
