package com.lvmama.captcha.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {

	protected Logger log = Logger.getLogger(this.getClass());

	public static final Integer PAGE_SIZE = 10;

	/**
	 * json对象返回
	 * 
	 * @param response
	 * @param bean
	 */
	protected void returnJsonObject(HttpServletResponse response, Object bean) {
		String jsonStr = JSONObject.toJSONString(bean);
		response.setCharacterEncoding("UTF-8");
		PrintWriter pWriter = null;
		try {
			pWriter = response.getWriter();
			pWriter.write(jsonStr);
		} catch (IOException e) {
			log.error("returnJsonObject:", e);
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
	}

	/**
	 * 返回json对象，包含成功success标志，和操作信息message
	 * 
	 * @param response
	 * @param sucess
	 * @param message
	 */
	protected void returnJsonObject(HttpServletResponse response, boolean sucess, String message) {
		JSONObject bean = new JSONObject();
		bean.put("success", sucess);
		bean.put("message", message);

		returnJsonObject(response, bean);
	}


	/**
	 * 回显查询条件
	 * @param code
	 * @param canSetLevel
	 * @return
	 */
	protected void echoQueryData(ModelAndView mav, String code, String businessCode, String codeDesc, String canSetLevel, String secureLevel){
		if(code!=null) {
			mav.addObject(code,businessCode);
			mav.addObject("codeDesc", codeDesc);
		}
		mav.addObject(canSetLevel, secureLevel);
		if(secureLevel !=null) {
			String[] str = secureLevel.split(",");
			for (int i = 0; i < str.length; i++) {
				if ("primary".equals(str[i])) {
					mav.addObject("primary", str[i]);
				}else if ("middle".equals(str[i])) {
					mav.addObject("middle", str[i]);
				}else if ("senior".equals(str[i])) {
					mav.addObject("senior", str[i]);
				}
			}
		}
	}

}