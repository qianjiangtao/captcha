package com.lvmama.captcha.vo;

import java.awt.image.BufferedImage;

/**
 * 
 * @Title: CaptchaVo.java
 * @desc 验证码对象
 * @author wangwenming
 * @date 2016年12月7日
 * @version v1.0
 */
public class CaptchaVo {
	private BufferedImage bi;
	private String answer;

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
