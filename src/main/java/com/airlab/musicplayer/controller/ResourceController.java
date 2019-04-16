package com.airlab.musicplayer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.airlab.musicplayer.model.ResourceInfo;
import com.airlab.musicplayer.model.Response;
import com.airlab.musicplayer.model.SessionInfo;
import com.airlab.musicplayer.service.ResourceInfoService;
import com.airlab.musicplayer.utils.CookieUtils;
import com.airlab.musicplayer.utils.LocalSessionManager;
import com.airlab.musicplayer.utils.StringTools;

@Controller
@RequestMapping("resource")
public class ResourceController {
	private final static String ADMIN_USER_COOKIE = "adminuser";
	private final static String NORMAL_USER_COOKIE = "normaluser";
	
	@Autowired
	private ResourceInfoService resourceInfoServiceImpl;
	
	@Autowired
	private LocalSessionManager sessionManager;
	
	@RequestMapping(value = "/queryResource", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json; charset=utf-8")
	public @ResponseBody String queryResource(String resourceId) {
		Response response = new Response();

		ResourceInfo resourceInfo = null;
		try {
			resourceInfo = resourceInfoServiceImpl.queryResourceInfo(resourceId);
		} catch (Exception e) {

			response.setErrcode(211);
			response.setErrmsg(e.getMessage());
			return response.toJson();
		}
		response.addResult("result", resourceInfo);
		return response.toJson();
	}

	@RequestMapping(value = "/uploadResource", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json; charset=utf-8")
	public @ResponseBody String uploadResource(HttpServletRequest request) {
		Response rs = new Response();
//		String sessionId = CookieUtils.getCookieValue(request, ADMIN_USER_COOKIE);
//		SessionInfo sessionInfo = sessionManager.getSession(sessionId);
//		if(sessionInfo == null){
//			rs.setErrcode(110);
//			return rs.toString();
//		}
		ResourceInfo resourceInfo = null;
		try {
			List files = ((MultipartHttpServletRequest) request).getFiles("file");
			for (int i = 0; i < files.size(); ++i) {
				MultipartFile file = (MultipartFile) files.get(i);
				String fileName = file.getOriginalFilename();
				if (!file.isEmpty()) {
					try {
						byte[] bytes = file.getBytes();
						String resourceId = StringTools.getUUID();
						resourceInfo = resourceInfoServiceImpl.addResource(0L, fileName, file.getContentType(),
								resourceId, bytes);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			rs.setErrcode(110);
			rs.setErrmsg(e.getMessage());
			return rs.toString();
		}

		rs.addResult("result", resourceInfo);
		return rs.toString();
	}
}
