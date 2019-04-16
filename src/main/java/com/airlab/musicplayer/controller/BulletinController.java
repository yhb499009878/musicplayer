package com.airlab.musicplayer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airlab.musicplayer.model.Response;
import com.airlab.musicplayer.model.musicinfo.Bulletin;
import com.airlab.musicplayer.service.BulletinService;

@Controller
@RequestMapping("bulletin")
public class BulletinController {
	@Autowired
	private BulletinService bulletinServiceImpl;
	
	@RequestMapping(value = "/queryBulletinByContent", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String queryBulletinByContent(String content) {
		Response rs = new Response();
		List<Bulletin> result = bulletinServiceImpl.queryBulletinByContent(content);
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/insertBulletin", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String insertBulletin(String content) {
		Response rs = new Response();
		Bulletin result = bulletinServiceImpl.insertBulletin(content);
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/updateBulletin", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String updateBulletin(Long id, String content) {
		Response rs = new Response();
		bulletinServiceImpl.updateBulletin(id, content);
		return rs.toString();
	}
	
	@RequestMapping(value = "/deleteBulletin", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String deleteBulletin(Long id) {
		Response rs = new Response();
		bulletinServiceImpl.deleteBulletin(id);
		return rs.toString();
	}
}
