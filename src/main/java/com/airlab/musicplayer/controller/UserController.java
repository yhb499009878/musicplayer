package com.airlab.musicplayer.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airlab.musicplayer.model.Response;
import com.airlab.musicplayer.model.SessionInfo;
import com.airlab.musicplayer.model.musicinfo.Users;
import com.airlab.musicplayer.service.AdminService;
import com.airlab.musicplayer.service.UserService;
import com.airlab.musicplayer.utils.CookieUtils;
import com.airlab.musicplayer.utils.LocalSessionManager;
import com.airlab.musicplayer.utils.RandomUtils;

@Controller
@RequestMapping("user")
public class UserController {
	private final static String NORMAL_USER_COOKIE = "normaluser";
	
	@Autowired
	private AdminService adminServiceImpl;
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private LocalSessionManager sessionManager;
	
	/**
	 * 注册
	 */
	@RequestMapping(value = "/register", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
//	@ResponseBody public String register(HttpServletResponse response, String username, String password, String tel, String sex, String email, String isVip) {
	@ResponseBody public String register(HttpServletResponse response, String username, String password) {
		Response rs = new Response();
		Users users = adminServiceImpl.queryUsersByUsername(username);
		if(users != null){
			rs.setErrcode(111);
			rs.setErrmsg("用户名已存在");
			return rs.toString();
		}
		users = userServiceImpl.register(username, password);
		//注册成功后直接登录
		SessionInfo sessinoInfo = createSession(users.getId(), users.getUsername(), "", users.getTel(), users.getSex(), users.getEmail(), users.getIsvip());
		CookieUtils.setCookie(response, NORMAL_USER_COOKIE, sessinoInfo.getSessionId());
		return rs.toString();
	}
	
	
	/**
	 * 获取所有的会员信息
	 */
	@RequestMapping(value = "/getUsers", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public List<Users> getUsers() {
		List<Users> users = userServiceImpl.queryAllUsers();
		return users;
	}
	
	/**
	 * 会员管理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUser",method={RequestMethod.POST,RequestMethod.GET},produces="application/json;charset=utf-8")
	@ResponseBody public String deleteUser(String id)
	{
		Response rs = new Response();
		int user = userServiceImpl.deleteUser(Integer.parseInt(id));
		if (user>0)
		{
			return rs.toString();
		}
		rs.setErrcode(112);
		rs.setErrmsg("删除信息出错");
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
