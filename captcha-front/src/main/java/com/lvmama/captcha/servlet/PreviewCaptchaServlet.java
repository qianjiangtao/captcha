package com.lvmama.captcha.servlet;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lvmama.captcha.constant.CaptchaConstant;
import com.lvmama.captcha.dto.CaptchaRuleDTO;
import com.lvmama.captcha.vo.CaptchaVo;
import com.lvmama.comm.utils.MemcachedUtil;

/**
 * @Title: PreviewCaptchaServlet.java
 * @desc 预览接口
 * @author wangwenming
 * @date 2016年12月7日
 * @version v1.0
 */
public class PreviewCaptchaServlet extends BaseServlet {

	private static final long serialVersionUID = 2824535133351588882L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bizCode = request.getParameter("bizCode");
		String secureLevel = request.getParameter("secureLevel");
		setHead(response);
		String key = CaptchaConstant.PREVIEW_PREFIX + bizCode + "_" + secureLevel;
		CaptchaRuleDTO captchaRule = (CaptchaRuleDTO) MemcachedUtil.getInstance().get(key);
		CaptchaVo vo = getCaptcha(captchaRule);
		if (vo != null && vo.getBi() != null) {
			ImageIO.write(vo.getBi(), "JPEG", response.getOutputStream());
		}
	}

}
