package com.lvmama.captcha.constant;

/**
 * 
 * @author wangwenming
 * 
 * @date 2016年12月6日
 */
public class CaptchaConstant {

	// 预览缓存的key前缀+BUSINESSCODE_securelelve
	public static final String PREVIEW_PREFIX = "captcha_preview_";

	// 全部业务code key
	public static final String ALL_BUSINESSCODE = "captcha_all_businesscode";

	// 全部业务code level
	public static final String ALL_CODE_LEVELS = "captcha_all_code_levels";

	// 全部业务code default level
	public static final String ALL_CODE_DEFAULT_LEVEL = "captcha_all_code_default_level";

	// 业务类型对象key + code
	public static final String BUSINESS_TYPE_PREFIX = "captcha_businesstype_";

	// 业务类型对象key +BUSINESSCODE_securelelve
	public static final String CAPTCHARULE_PREFIX = "captcha_captcharule_";

	// 30天
	public static final int LONG_CACHE_TIME = 30 * 24 * 3600;

	// 5分钟
	public static final int SHORT_CACHE_TIME = 300;

	public static enum CharType {
		Arithmetic("1"), Char("0");

		private String value;

		private CharType(String value) {
			this.value = value;
		}

		public String toString() {
			return this.name();
		}

		public String getValue() {
			return this.value;
		}
	}

	/*
	 * 是否旋转
	 */
	public enum IsRotate {
		Y("1"), N("0");
		private String value;

		private IsRotate(String value) {
			this.value = value;
		}

		public String toString() {
			return this.name();
		}

		public String getValue() {
			return this.value;
		}
	}

	/*
	 * 是否使用风控
	 */
	public enum IsUseRisk {
		Y("1"), N("0");
		private String value;

		private IsUseRisk(String value) {
			this.value = value;
		}

		public String toString() {
			return this.name();
		}

		public String getValue() {
			return this.value;
		}
	}

	public static void main(String[] args) {
		System.out.println(CharType.Arithmetic.getValue());
	}
}
