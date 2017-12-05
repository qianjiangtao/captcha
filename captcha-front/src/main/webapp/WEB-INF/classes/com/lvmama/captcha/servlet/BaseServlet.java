package com.lvmama.captcha.servlet;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.gimpy.FishEyeGimpyRenderer;
import nl.captcha.gimpy.GimpyRenderer;
import nl.captcha.gimpy.RippleGimpyRenderer;
import nl.captcha.gimpy.StretchGimpyRenderer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.noise.NoiseProducer;
import nl.captcha.text.producer.LvTextProducer;
import nl.captcha.text.renderer.LvWordRenderer;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import com.lvmama.captcha.constant.CaptchaConstant.CharType;
import com.lvmama.captcha.constant.CaptchaConstant.IsRotate;
import com.lvmama.captcha.dto.CaptchaRuleDTO;
import com.lvmama.captcha.dto.FontDTO;
import com.lvmama.captcha.dto.NoiseDTO;
import com.lvmama.captcha.vo.CaptchaVo;

/**
 * 
 * @Title: BaseServlet.java
 * @desc 验证码基类
 * @author wangwenming
 * @date 2016年12月7日
 * @version v1.0
 */
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = -4382569306296147241L;

	private Properties props = new Properties();

	private Producer kaptchaProducer = null;
	private GimpyRenderer fishEye = null;
	private GimpyRenderer dropShadow = null;
	private GimpyRenderer stretch = null;
	private GimpyRenderer ripple = null;

	protected void setHead(HttpServletResponse response) {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
	}

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		ImageIO.setUseCache(false);
		Enumeration<?> initParams = conf.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			String key = (String) initParams.nextElement();
			String value = conf.getInitParameter(key);
			this.props.put(key, value);
		}
		Config config = new Config(this.props);
		this.kaptchaProducer = config.getProducerImpl();
		fishEye = new FishEyeGimpyRenderer(Color.PINK, Color.PINK);
		dropShadow = new DropShadowGimpyRenderer();
		stretch = new StretchGimpyRenderer();
		ripple = new RippleGimpyRenderer();
	}

	protected CaptchaVo getCaptcha(CaptchaRuleDTO captchaRule) throws IOException {
		if (captchaRule != null) {
			return CharType.Arithmetic.getValue().equals(captchaRule.getCharType()) ? getArithmeticCaptcha()
					: getCharCaptcha(captchaRule);
		}
		return null;
	}

	private CaptchaVo getCharCaptcha(CaptchaRuleDTO captchaRule) {
		List<NoiseProducer> noiseList = null;
		if (captchaRule.getNoises() != null) {
			noiseList = new ArrayList<NoiseProducer>();
			for (NoiseDTO noise : captchaRule.getNoises()) {
				noiseList.add(new CurvedLineNoiseProducer(new Color(noise.getNoiseColor()), noise.getNoiseWidth()));
			}
		}
		List<Color> colors = new ArrayList<Color>();
		if (captchaRule.getCharColors() != null) {
			for (Integer color : captchaRule.getCharColors()) {
				colors.add(new Color(color));
			}
		}
		List<Font> fonts = new ArrayList<Font>();
		if (captchaRule.getFonts() != null) {
			for (FontDTO font : captchaRule.getFonts()) {
				fonts.add(new Font(font.getName(), font.getStyle(), font.getSize()));
			}
		}
		List<GimpyRenderer> gimpyList = new ArrayList<GimpyRenderer>();
		if (captchaRule.getFishEye() == 1) {
			gimpyList.add(fishEye);
		}
		if (captchaRule.getShadow() == 1) {
			gimpyList.add(dropShadow);// 阴影
		}
		if (captchaRule.getStretch() == 1) {
			gimpyList.add(stretch);// 伸展
		}
		if (captchaRule.getRipple() == 1) {
			gimpyList.add(ripple);// 波浪
		}

		Captcha captcha = new Captcha.Builder(captchaRule.getImgWidth(), captchaRule.getImgHeight())
				.addText(
						new LvTextProducer(captchaRule.getEnglishCharLength(), captchaRule.getChineseCharLength(),
								captchaRule.getNumbersCharLength()),
						new LvWordRenderer(colors, fonts, IsRotate.Y.getValue().equals(captchaRule.getRotate()) ? true
								: false, captchaRule.getOverlapPercent()))
				.gimp(gimpyList)
				.addNoise(noiseList)
				.addBorder()
				.addBackground(
						new GradiatedBackgroundProducer(new Color(captchaRule.getBgPreColor()), new Color(captchaRule
								.getBgBackColor()))).build();
		CaptchaVo vo = new CaptchaVo();
		vo.setBi(captcha.getImage());
		vo.setAnswer(captcha.getAnswer());
		return vo;
	}

	private CaptchaVo getArithmeticCaptcha() {
		String capText = this.kaptchaProducer.createText();
		String capImageValue = capText;
		Double rand = Math.ceil(Math.random() * 10);
		if (rand > 2) {
			String str1 = capText.substring(0, 1);
			String str2 = capText.substring(2, 3);
			Integer result = Integer.valueOf(str1) + Integer.valueOf(str2);
			capText = result.toString();
			capImageValue = str1 + "+" + str2 + "=?";
		}
		CaptchaVo vo = new CaptchaVo();
		vo.setBi(this.kaptchaProducer.createImage(capImageValue));
		vo.setAnswer(capText);
		return vo;
	}
}
