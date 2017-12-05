package com.lvmama.captcha.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Captcha 业务异常类
 */
public class CaptchaException extends RuntimeException {

	public CaptchaException(String message) {
		super(message);
	}

	public CaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getErrorInfo() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		this.printStackTrace(pw);
		return "\r\n" + sw.toString() + "\r\n";
	}
}
