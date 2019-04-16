package com.airlab.musicplayer.service;

import com.alibaba.fastjson.JSONObject;

public interface SearchService {

	JSONObject findSongsByXName(String xName);
}
