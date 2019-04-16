package com.airlab.musicplayer.utils;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.SessionInfo;

@Lazy(false)
@Component("sessionManager")
public class LocalSessionManager {
	public static Logger log = Logger.getLogger(LocalSessionManager.class);

	/**
	 * session超时时间，默认60分钟
	 */
	private TimeLimitedLRUCache<String, SessionInfo> sessionCache = new TimeLimitedLRUCache<>(2048, 3600000);

	@PostConstruct
	private void init() {
		// do something
	}

	public SessionInfo getSession(String sessionId) {
		log.info("LocalSessionManager getSession sessionId = " + sessionId);
		SessionInfo session = sessionCache.get(sessionId);
		if (session != null) {
			this.updateSession(session);
		}
		return session;
	}

	public boolean updateSession(SessionInfo session) {
		log.info("LocalSessionManager updateSession");
		sessionCache.refresh(session.getSessionId(), session);
		return true;
	}

	public void logoutSession(String sessionId) {
		log.info("LocalSessionManager logoutSession sessionId = " + sessionId);
		sessionCache.remove(sessionId);
	}

}
