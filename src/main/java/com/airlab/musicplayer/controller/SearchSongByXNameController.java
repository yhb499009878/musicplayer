package com.airlab.musicplayer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("search")
public class SearchSongByXNameController {

	@RequestMapping(value="songs",method=RequestMethod.GET)
	public JSONObject getSongs(@RequestParam(value="sname")String sname){
		return null;
	}
}
