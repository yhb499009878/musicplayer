package com.airlab.musicplayer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airlab.musicplayer.model.Response;
import com.airlab.musicplayer.model.SessionInfo;
import com.airlab.musicplayer.model.musicinfo.TAdmin;
import com.airlab.musicplayer.model.musicinfo.Users;
import com.airlab.musicplayer.service.AdminService;
import com.airlab.musicplayer.utils.CookieUtils;
import com.airlab.musicplayer.utils.LocalSessionManager;
import com.airlab.musicplayer.utils.RandomUtils;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private final static String ADMIN_USER_COOKIE = "adminuser";
	private final static String NORMAL_USER_COOKIE = "normaluser";
	
	@Autowired
	private AdminService adminServiceImpl;
	
	@Autowired
	private LocalSessionManager sessionManager;
	
	/**
	 * 查询会员
	 */
	@RequestMapping(value = "/queryUsers", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String queryUsers(String username, String isvip) {
		Response rs = new Response();
		List<Users> result = adminServiceImpl.queryUsersByUsernameAndIsvip(username, isvip);
		rs.addResult("result", result);
		return rs.toString();
		
	}
	
	/**
	 * 管理员登录
	 */
	@RequestMapping(value = "/adminLogin", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String adminLogin(String username, String password, HttpServletResponse response) {
		Response rs = new Response();
		TAdmin tAdmin = adminServiceImpl.adminLogin(username, password);
		if(tAdmin != null){
			//写入session
			SessionInfo sessinoInfo = createSession(tAdmin.getId(), tAdmin.getUsername(), tAdmin.getRealname(), tAdmin.getTel(), "", tAdmin.getEmail(), "");
			CookieUtils.setCookie(response, ADMIN_USER_COOKIE, sessinoInfo.getSessionId());
			return rs.toString();
		}else{
			rs.setErrcode(110);
			rs.setErrmsg("用户名或者密码错误");
			return rs.toString();
		}
		
	}
	
	/**
	 * 普通用户登录登入
	 */
	@RequestMapping(value = "/userLogin", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String userLogin(String username, String password, HttpServletResponse response) {
		Response rs = new Response();
		Users users = adminServiceImpl.normalLogin(username, password);
		if(users != null){
			//写入session
			SessionInfo sessinoInfo = createSession(users.getId(), users.getUsername(), "", users.getTel(), users.getSex(), users.getEmail(), users.getIsvip());
			CookieUtils.setCookie(response, NORMAL_USER_COOKIE, sessinoInfo.getSessionId());
			return rs.toString();
		}else{
			rs.setErrcode(110);
			rs.setErrmsg("用户名或者密码错误！");
			return rs.toString();
		}
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userLogout", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String userLogout(HttpServletRequest request, HttpServletResponse response) {
		Response rs = new Response();
		String sessionId = CookieUtils.getCookieValue(request, NORMAL_USER_COOKIE);
		sessionManager.logoutSession(sessionId);
		CookieUtils.setCookie(response, NORMAL_USER_COOKIE, "", 0);
		return rs.toString();
	}
	
	/**
	 * 注册
	 */
	@RequestMapping(value = "/register", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String register(HttpServletResponse response, String username, String password, String tel, String sex, String email, String isVip) {
		Response rs = new Response();
		Users users = adminServiceImpl.queryUsersByUsername(username);
		if(users != null){
			rs.setErrcode(111);
			rs.setErrmsg("用户名已存在");
			return rs.toString();
		}
		
		users = adminServiceImpl.register(username, password, tel, sex, email, isVip);
		//注册成功后直接登录
		SessionInfo sessinoInfo = createSession(users.getId(), users.getUsername(), "", users.getTel(), users.getSex(), users.getEmail(), users.getIsvip());
		CookieUtils.setCookie(response, NORMAL_USER_COOKIE, sessinoInfo.getSessionId());
		return rs.toString();
	}
	
	/**
	 * 创建session
	 * @param userId
	 * @param username
	 * @param realname
	 * @param tel
	 * @param sex
	 * @param email
	 * @param isVip
	 * @return
	 */
	private SessionInfo createSession(long userId, String username, String realname, String tel, String sex, String email, String isvip){
		SessionInfo info = new SessionInfo();
		long sessionId = RandomUtils.next(-8000000, -1000);//为了不与数据库真实ID重复，采用负值
		
		while (sessionManager.getSession(String.valueOf(sessionId))!=null) {
			sessionId = RandomUtils.next(-8000000, -1000);//为了不与数据库真实ID重复，采用负值
		}
		info.setUsername(username);
		info.setSessionId(String.valueOf(sessionId));
		info.setUserId(userId);
		info.setUpdateTime(System.currentTimeMillis());
		info.setEmail(email);
		info.setIsvip(isvip);
		info.setSex(sex);
		info.setRealname(realname);
		info.setTel(tel);
		sessionManager.updateSession(info);
		return info;
	}

}
