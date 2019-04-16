package com.airlab.musicplayer.service;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.Bulletin;

public interface BulletinService {
	public List<Bulletin> queryBulletinByContent(String content);

	public Bulletin insertBulletin(String content);

	public void deleteBulletin(Long id);

	public void updateBulletin(Long id, String content);

}
