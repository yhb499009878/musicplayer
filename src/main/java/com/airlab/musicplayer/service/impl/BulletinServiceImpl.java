package com.airlab.musicplayer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlab.musicplayer.model.musicinfo.Bulletin;
import com.airlab.musicplayer.mysql.api.IBulletinDao;
import com.airlab.musicplayer.service.BulletinService;

@Service
public class BulletinServiceImpl implements BulletinService {
	
	@Autowired
	private IBulletinDao bulletinDao;
	
	@Override
	public List<Bulletin> queryBulletinByContent(String content) {
		return bulletinDao.queryBulletinByContent(content);
	}

	@Override
	public Bulletin insertBulletin(String content) {
		Bulletin bulletin = new Bulletin();
		bulletin.setContent(content);
		bulletin = bulletinDao.insert(bulletin);
		return bulletin;
	}

	@Override
	public void deleteBulletin(Long id) {
		bulletinDao.deleteById(id);
	}

	@Override
	public void updateBulletin(Long id, String content) {
		Bulletin bulletin = new Bulletin();
		bulletin.setId(id);
		bulletin.setContent(content);
		bulletinDao.updateById(bulletin);
	}

}
