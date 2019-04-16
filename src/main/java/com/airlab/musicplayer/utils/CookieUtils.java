package com.airlab.musicplayer.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	/**
	 * 设置cookie有效期，根据需要自定义[本系统设置为30天]
	 */
	private final static int COOKIE_MAX_AGE = 1000 * 60 * 60 * 24 * 30;

	/**
	 *
	 * @desc 删除指定Cookie
	 * @param response
	 * @param cookie
	 */
	public static void removeCookie(HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 *
	 * @desc 删除指定Cookie
	 * @param response
	 * @param cookie
	 * @param domain
	 */
	public static void removeCookie(HttpServletResponse response, Cookie cookie, String domain) {
		if (cookie != null) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			cookie.setDomain(domain);
			response.addCookie(cookie);
		}
	}

	/**
	 *
	 * @desc 根据Cookie名称得到Cookie的值，没有返回Null
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 *
	 * @desc 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
	 * @param request
	 * @param name
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0)
			return null;
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (!cookies[i].getName().equals(name))
				continue;
			cookie = cookies[i];
			if (request.getServerName().equals(cookie.getDomain()))
				break;
		}

		return cookie;
	}

	/**
	 *
	 * @desc 添加一条新的Cookie信息，默认有效时间为一个月
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, COOKIE_MAX_AGE);
	}

	/**
	 *
	 * @desc 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		if (value == null)
			value = "";
		Cookie cookie = new Cookie(name, value);
		if (maxAge != 0) {
			cookie.setMaxAge(maxAge);
		} else {
			cookie.setMaxAge(COOKIE_MAX_AGE);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
