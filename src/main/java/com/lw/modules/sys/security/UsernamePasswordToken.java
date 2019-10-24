/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lw.modules.sys.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户和密码（包含验证码）令牌类
 * @author ThinkGem
 * @version 2013-5-19
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;
	private boolean mobileLogin;
	private String vailPwd; //验证密码复杂度标记  0复杂  1
	
	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha,
								 boolean mobileLogin) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.mobileLogin = mobileLogin;
		String pwdString = String.valueOf(password);
		boolean result = vaildPassWord(pwdString);
		if(result){
			this.vailPwd = "0";
		}else{
			this.vailPwd = "1";
		}

	}
	private boolean vaildPassWord(String passWord){
		/**
		 * 长度必须要大于8位小于20位
		 * 必须要数字，字母，特殊符号组成
		 */
		String zmRegex = ".*[a-zA-Z]+.*";
		String numRegex = ".*\\d+.*";
		String fhRegex = ".*[`~!@#$^&*()=|{}':;',\\\\\\\\\\\\\\\\.<>/?~]+.*";
		Pattern zmpattern = Pattern.compile(zmRegex);
		Pattern numpattern = Pattern.compile(numRegex);
		Pattern fhpattern = Pattern.compile(fhRegex);
		Matcher zmMatcher = zmpattern.matcher(passWord);
		Matcher mumMatcher = numpattern.matcher(passWord);
		Matcher fhMatcher = fhpattern.matcher(passWord);
		if(passWord.length() <  8 || passWord.length() > 20){
			return false;
		}else if(!zmMatcher.matches() || !mumMatcher.matches() || !fhMatcher.matches()){
			return false;
		}else{
			return true;
		}
	}

	public String getVailPwd()
	{
		return vailPwd;
	}

	public void setVailPwd(String vailPwd)
	{
		this.vailPwd = vailPwd;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}
	
}